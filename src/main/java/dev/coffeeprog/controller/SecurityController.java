package dev.coffeeprog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class SecurityController {
    private UserDetailsManager userDetailsManager;

    @Autowired
    public SecurityController(UserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }

    @RequestMapping("exists/{username}")
    public boolean userExists(@PathVariable("username") String username) {
        return userDetailsManager.userExists(username);
    }

    @RequestMapping("add/{username}")
    public String add(@PathVariable("username") String username) {
        if (!userDetailsManager.userExists(username)) {
            var user = User.withUsername(username).password("changepassword").roles("read").build();
            userDetailsManager.createUser(user);
            return "added";
        }
        return "error/already exists";
    }
}
