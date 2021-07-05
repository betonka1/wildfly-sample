package explaineverything.wildfly.rest;

import explaineverything.wildfly.rest.service.GroupService;
import explaineverything.wildfly.rest.service.UserService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/rest")
public class RestGateway extends Application {

    public Set<Class<?>> getClasses() {
        return new HashSet<>(Arrays.asList(UserService.class, GroupService.class));
    }

}