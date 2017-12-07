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

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;

@SpringBootApplication
@EnableMetrics
@ComponentScan(basePackages = { "ops.inventory" })
@EnableAutoConfiguration(exclude = { ErrorMvcAutoConfiguration.class })
public class OpsInventoryServiceApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(OpsInventoryServiceApplication.class);
	}

	public static void main(String[] args) {
		new OpsInventoryServiceApplication()
				.configure(new SpringApplicationBuilder(OpsInventoryServiceApplication.class)).run(args);
	}


}