/*
 * This software is the confidential and proprietary information of
 * eharmony.com and may not be used, reproduced, modified, distributed,
 * publicly displayed or otherwise disclosed without the express written
 * consent of eharmony.com.
 *
 * This software is a work of authorship by eharmony.com and protected by
 * the copyright laws of the United States and foreign jurisdictions.
 *
 * Copyright 2000-2017 eharmony.com, Inc. All rights reserved.
 *
 */
package ops.inventory.config;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.internal.scanning.PackageNamesScanner;
import org.springframework.context.annotation.Configuration;

import ops.inventory.rest.GenericExceptionMapper;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        PackageNamesScanner resourceFinder = new PackageNamesScanner(new String[] { "ops.inventory"}, true);
		registerFinder(resourceFinder);
        register(JacksonFeature.class);
        register(MultiPartFeature.class);
        register(GenericExceptionMapper.class);
    }
    
}
