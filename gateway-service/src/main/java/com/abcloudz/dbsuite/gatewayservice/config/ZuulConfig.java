package com.abcloudz.dbsuite.gatewayservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.cglib.proxy.*;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.web.ZuulController;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

@Configuration
@RequiredArgsConstructor
public class ZuulConfig {

    private static final String ERROR_PATH = "/error";
    private static final String METHOD = "lookupHandler";

    private final RouteLocator routeLocator;
    private final ZuulController zuulController;
    private final ErrorController errorController;

    @Bean
    public ZuulPostProcessor zuulPostProcessor() {
        return new ZuulPostProcessor(routeLocator, zuulController, errorController);
    }

    private enum LookupHandlerCallbackFilter implements CallbackFilter {
        INSTANCE;

        @Override
        public int accept(Method method) {
            return METHOD.equals(method.getName()) ? 0 : 1;
        }
    }

    private enum LookupHandlerMethodInterceptor implements MethodInterceptor {
        INSTANCE;

        @Override
        public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            return ERROR_PATH.equals(args[0]) ? null : methodProxy.invokeSuper(target, args);
        }
    }

    private static final class ZuulPostProcessor implements BeanPostProcessor {

        private final RouteLocator routeLocator;
        private final ZuulController zuulController;
        private final boolean hasErrorController;

        ZuulPostProcessor(RouteLocator routeLocator, ZuulController zuulController, ErrorController errorController) {
            this.routeLocator = routeLocator;
            this.zuulController = zuulController;
            this.hasErrorController = (errorController != null);
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            if (hasErrorController && (bean instanceof ZuulHandlerMapping)) {
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(ZuulHandlerMapping.class);
                enhancer.setCallbackFilter(LookupHandlerCallbackFilter.INSTANCE);
                enhancer.setCallbacks(new Callback[] {LookupHandlerMethodInterceptor.INSTANCE, NoOp.INSTANCE});
                Constructor<?> constructor = ZuulHandlerMapping.class.getConstructors()[0];
                return enhancer.create(constructor.getParameterTypes(), new Object[] {routeLocator, zuulController});
            }
            return bean;
        }
    }
}
