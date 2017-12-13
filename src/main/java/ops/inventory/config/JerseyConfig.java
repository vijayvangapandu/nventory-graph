
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
