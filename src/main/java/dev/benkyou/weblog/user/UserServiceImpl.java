package dev.benkyou.weblog.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    UserRepository userRepository;

    private Map<String, String> tokenMap = new ConcurrentHashMap<>();

    @Override
    public void createUser(User user) {
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        new User().setAccount(user.getAccount())
                .setPassword(encodedPassword)
                .setName(user.getName())
                .persist();
    }

    @Override
    public boolean verifyUser(String account, String password) {
        User user = findUserByAccount(account);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public User findUserByAccount(String account) {
        return userRepository.find("account", account).firstResult();
    }

    @Override
    public String generateToken(String account) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String token = passwordEncoder.encode(account);
        tokenMap.put(token, account);
        return token;
    }

    @Override
    public boolean checkToken(String token) {
        return tokenMap.containsKey(token);
    }

    @Override
    public String getUserByToken(String token) {
        return tokenMap.get(token);
    }

    @Override
    public void deleteToken(String token) {
        tokenMap.remove(token);
    }

}
