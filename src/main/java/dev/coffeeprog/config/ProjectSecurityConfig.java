package dev.coffeeprog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// before version 5.7 this would have been an override on "extends WebSecurityConfigurerAdapter"
// configure method rather than a bean like this...

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        /*
        http.formLogin();
        http.httpBasic();
        return (SecurityFilterChain)http.build();
         */

        /**
         *      /myAccount - secured
         *      /myBalance - secured
         *      /myLoans - secured
         *      /myCards - secured
         *      /notices - not secured
         *      /contact - not secured
         */

        http.authorizeHttpRequests( (auth)->auth
                .antMatchers("/user/exists").hasRole("ADMIN")
                .antMatchers("/myAccount","/myBalance","/myLoans","/myCards").authenticated()
                .antMatchers("/notices","/contact").permitAll()
        ).httpBasic(Customizer.withDefaults());
        return http.build();
    }

    /*
    // my approach (from spring docs basically), notice needs password encoder
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails coffeeprog = users
                .username("coffeeprog")
                .password("password123")
                .roles("READ")
                .build();
        UserDetails admin = users
                .username("admin")
                .password("password")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(coffeeprog, admin);
    }
     */

    // appraoch which requires PasswordEncoder Bean
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
        UserDetails admin = User.withUsername("admin").password("12345").authorities("admin").build();
        UserDetails user = User.withUsername("coffeeprog").password("password123").authorities("read").build();
        UserDetails bob = User.withUsername("bob").password("bob123").authorities("read").build();
        userDetailsService.createUser(admin);
        userDetailsService.createUser(user);
        userDetailsService.createUser(bob);
        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
