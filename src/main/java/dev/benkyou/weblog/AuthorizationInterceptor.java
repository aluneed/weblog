package dev.benkyou.weblog;

import dev.benkyou.weblog.user.UserService;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthorizationInterceptor implements ContainerRequestFilter {

    @Inject
    UserService userService;

    @Override
    public void filter(ContainerRequestContext context) {
        String uriPath = context.getUriInfo().getPath();
        String requestMethod = context.getMethod();
        //todo
        if (uriPath.startsWith("/user") || requestMethod.equals("GET")) {
            return;
        }
        String token = context.getHeaderString("Authorization");
        if (token != null && !token.isEmpty()) {
            if (userService.checkToken(token)) {
                return;
            }
        }
        throw new RuntimeException();
    }

}
