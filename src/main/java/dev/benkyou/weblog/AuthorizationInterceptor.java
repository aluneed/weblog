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
        //todo
        if(uriPath.equals("/content/list") ||
                uriPath.equals("/content/getContent") ||
                uriPath.equals("/user/login") ||
                uriPath.equals("/user/signup")) {
            return;
        }
        String token = context.getHeaderString("Authorization");
        if (token != null && !token.isEmpty()) {
            if (userService.checkToken(token)){
                return;
            }
        }
        throw new RuntimeException();
    }

}
