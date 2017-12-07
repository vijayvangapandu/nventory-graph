/*
 * This software is the confidential and proprietary information of
 * eharmony.com and may not be used, reproduced, modified, distributed,
 * publicly displayed or otherwise disclosed without the express written
 * consent of eharmony.com.
 *
 * This software is a work of authorship by eharmony.com and protected by
 * the copyright laws of the United States and foreign jurisdictions.
 *
 * Copyright 2000-2016 eharmony.com, Inc. All rights reserved.
 *
 */
package ops.inventory.rest;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.jvnet.hk2.annotations.Service;
import org.springframework.boot.actuate.endpoint.HealthEndpoint;
import org.springframework.boot.actuate.health.Health;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Leverages Spring Actuator for service health.
 */
@Singleton
@Service
@Path("/ecv")
public class ActuatorEcvResource {

    private static final String STR_SERVER_UP = "SERVER UP";
    private static final String STR_SERVER_DOWN = "SERVER DOWN";
    
    @Inject HealthEndpoint healthEndpoint;
    
    /**
     * Returns a simple string result for server status that is consistent with current eHarmony usage.
     * @return "SERVER UP" or "SERVER DOWN" and appropriate return code
     */
    @GET
    public Response ecv() {
        Health health = healthEndpoint.invoke();
        org.springframework.boot.actuate.health.Status status = health.getStatus();
        
        if (org.springframework.boot.actuate.health.Status.UP.equals(status)) {
            return Response.status(Status.OK)
                           .entity(STR_SERVER_UP)
                           .build();
        }
        else {
            return Response.status(Status.SERVICE_UNAVAILABLE)
                           .entity(STR_SERVER_DOWN)
                           .build();
        }
    }
    
}
