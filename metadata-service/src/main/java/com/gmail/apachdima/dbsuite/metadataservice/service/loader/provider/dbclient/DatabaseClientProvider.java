package com.gmail.apachdima.dbsuite.metadataservice.service.loader.provider.dbclient;

import com.gmail.apachdima.dbsuite.metadataservice.common.message.Error;
import com.gmail.apachdima.dbsuite.metadataservice.dto.connection.ConnectionResponseDTO;
import com.gmail.apachdima.dbsuite.metadataservice.dto.loader.DatabaseClient;
import com.gmail.apachdima.dbsuite.metadataservice.dto.loader.LoadContext;
import com.gmail.apachdima.dbsuite.metadataservice.model.category.VendorType;
import com.gmail.apachdima.dbsuite.metadataservice.service.loader.provider.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.text.MessageFormat;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class DatabaseClientProvider implements Provider<com.gmail.apachdima.dbsuite.metadataservice.dto.loader.DatabaseClient<?>, LoadContext> {

    private final ApplicationContext applicationContext;
    private final DefaultListableBeanFactory beanFactory;
    private final MessageSource messageSource;

    private static final String RDBMS_URL = "jdbc:{0}://{1}:{2}/{3}?verifyServerCertificate={4}&useSSL={5}&requireSSL={6}";

    @Override
    public com.gmail.apachdima.dbsuite.metadataservice.dto.loader.DatabaseClient<?> provide(LoadContext context, Locale locale) {
        try {
            return applicationContext.getBean(context.getConnection().getConnectionGuid(), com.gmail.apachdima.dbsuite.metadataservice.dto.loader.DatabaseClient.class);
        } catch (NoSuchBeanDefinitionException e) {
            return createNewDatabaseClient(context, locale);
        }
    }

    private com.gmail.apachdima.dbsuite.metadataservice.dto.loader.DatabaseClient<?> createNewDatabaseClient(LoadContext context, Locale locale) {
        com.gmail.apachdima.dbsuite.metadataservice.dto.loader.DatabaseClient<?> databaseClient;
        ConnectionResponseDTO connection = context.getConnection();
        switch (VendorType.getType(connection.getVendor().getType())) {
            case POSTGRESQL:
            case MYSQL:
                databaseClient = createRDBMSClient(connection);
                initBean(databaseClient, connection.getConnectionGuid());
                return databaseClient;
            default:
                throw new UnsupportedOperationException(
                    messageSource.getMessage(Error.DATABASE_CLIENT_UNSUPPORTED.getKey(), new Object[]{connection.getConnectionName()}, locale));
        }
    }

    private com.gmail.apachdima.dbsuite.metadataservice.dto.loader.DatabaseClient<?> createRDBMSClient(ConnectionResponseDTO connection) {
        DataSource dataSource = DataSourceBuilder.create()
            .driverClassName(connection.getVendor().getDriver())
            .url(buildJdbcUrl(connection))
            .username(connection.getUsername())
            .password(connection.getPassword())
            .build();
        return new com.gmail.apachdima.dbsuite.metadataservice.dto.loader.DatabaseClient<>(new JdbcTemplate(dataSource));
    }

    private String buildJdbcUrl(ConnectionResponseDTO connection) {
        return MessageFormat.format(
            RDBMS_URL,
            connection.getVendor().getJdbcName(),
            connection.getHost(),
            connection.getPort(),
            connection.getDatabase(),
            connection.getVerifyServerCertificate(),
            connection.getUseSSL(),
            connection.getRequireSSL());
    }

    private void initBean(DatabaseClient<?> databaseClient, String beanName) {
        beanFactory.initializeBean(databaseClient, beanName);
        beanFactory.registerSingleton(beanName, databaseClient);
    }
}
