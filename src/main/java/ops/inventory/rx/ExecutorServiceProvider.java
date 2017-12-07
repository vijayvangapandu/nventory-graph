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
package ops.inventory.rx;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExecutorServiceProvider {

    private static final Logger log = LoggerFactory.getLogger(ExecutorServiceProvider.class);
    
    private final ExecutorService taskExecutor;
    
    private static final int DEFAULT_POOL_SIZE = 200;
    
    @Inject
    public ExecutorServiceProvider(@Value("${executor.pool.size.multipier:30}") int poolSizeMultiplier) {
        final int poolSize = Runtime.getRuntime().availableProcessors() * poolSizeMultiplier;
        final int derivedPoolSize = poolSize < DEFAULT_POOL_SIZE ? DEFAULT_POOL_SIZE : poolSize;
        taskExecutor = Executors.newWorkStealingPool(derivedPoolSize);
        log.info("initialized ConfigurationFeaturesResource with TASK_EXECUTOR with pool size {}", derivedPoolSize);
    }

    public ExecutorService getTaskExecutor() {
        return taskExecutor;
    }
    
}
