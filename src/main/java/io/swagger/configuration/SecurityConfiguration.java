package io.swagger.configuration;

import io.swagger.Service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl service;

    public SecurityConfiguration(UserDetailsServiceImpl service) {
        this.service = service;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/Users").hasAuthority("EMPLOYEE")
                .antMatchers(HttpMethod.GET, "/Users/{userid}").permitAll()
                .antMatchers(HttpMethod.PUT, "/Users/**").hasAuthority("EMPLOYEE")
                .antMatchers(HttpMethod.POST, "/Users/**").hasAuthority("EMPLOYEE")
                .antMatchers(HttpMethod.GET, "/Transactions/**").permitAll()
                //.antMatchers(HttpMethod.POST, "/Transactions/**").hasAuthority("EMPLOYEE")
                .antMatchers(HttpMethod.GET, "/Accounts/**").permitAll()
                .antMatchers(HttpMethod.POST, "/Accounts/**").hasAuthority("EMPLOYEE")
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/Login")
                .defaultSuccessUrl("/users.html", true)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/Logout")
                .deleteCookies("JSESSIONID")
                .permitAll();
    }

      /*
        In order for this to work, go to https://localhost:8443/api/login
       */

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}