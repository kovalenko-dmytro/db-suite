package com.abcloudz.dbsuite.loaderservice.service.impl;

import com.abcloudz.dbsuite.loaderservice.service.MetadataCategoryService;
import com.abcloudz.dbsuite.loaderservice.util.mapper.MetadataCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MetadataCategoryServiceImpl implements MetadataCategoryService {

    private final MessageSource messageSource;
    private final MetadataCategoryMapper metadataCategoryMapper;

}
