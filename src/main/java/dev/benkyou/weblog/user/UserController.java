package dev.benkyou.weblog.user;

import dev.benkyou.weblog.Rsp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/signup")
    public Rsp signup(User user) {
        if (userService.findUserByAccount(user.getAccount()) != null) {
            return Rsp.fail("account already exists");
        }
        userService.createUser(user);
        return Rsp.ok();
    }

    @POST
    @Path("/login")
    public Rsp login(User user) {
        if (userService.verifyUser(user.getAccount(), user.getPassword())) {
            String token = userService.generateToken(user.getAccount());
            return Rsp.ok(token);
        } else {
            return Rsp.fail("wrong account or password");
        }
    }

    @POST
    @Path("/logout")
    public Rsp logout(@HeaderParam("Authorization") String token) {
        userService.deleteToken(token);
        return Rsp.ok();
    }

}
