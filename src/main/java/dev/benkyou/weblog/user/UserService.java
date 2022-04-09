package dev.benkyou.weblog.user;

public interface UserService {

    void createUser(User user);

    boolean verifyUser(String account, String password);

    User findUserByAccount(String account);

    String generateToken(String account);

    boolean checkToken(String token);

    String getUserByToken(String token);

    void deleteToken(String token);

}
