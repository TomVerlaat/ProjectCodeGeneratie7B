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
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl service;

    public SecurityConfiguration(UserDetailsServiceImpl service) {
        this.service = service;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/Users").hasAuthority("EMPLOYEE")
                .antMatchers(HttpMethod.GET, "/Users/{userid}").permitAll()
                .antMatchers(HttpMethod.GET, "/Users/loggedInUser").permitAll()
                .antMatchers(HttpMethod.PUT, "/Users/deactivate/{userid}").hasAuthority("EMPLOYEE")
                .antMatchers(HttpMethod.PUT, "/Users/update/{userid}").permitAll()
                .antMatchers(HttpMethod.PUT, "/Users/updateLoggedInUser/{userid}").permitAll()
                .antMatchers(HttpMethod.POST, "/Users/**").hasAuthority("EMPLOYEE")
                .antMatchers(HttpMethod.GET, "/Transactions").hasAuthority("EMPLOYEE")
                .antMatchers(HttpMethod.GET, "/Transactions/**").permitAll()
                .antMatchers(HttpMethod.POST, "/Transactions/**").permitAll()
                .antMatchers(HttpMethod.GET, "/Accounts/**").permitAll()
                .antMatchers(HttpMethod.GET, "/Accounts").hasAuthority("EMPLOYEE")
                .antMatchers(HttpMethod.PUT, "/Accounts/**").hasAuthority("EMPLOYEE")
                .antMatchers(HttpMethod.POST, "/Accounts/**").hasAuthority("EMPLOYEE")
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/Login")
                .defaultSuccessUrl("/home.html", true)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/Logout")
                .deleteCookies("JSESSIONID")
                .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}