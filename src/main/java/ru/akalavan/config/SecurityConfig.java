package ru.akalavan.config;

import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.akalavan.jwt.JWTAuthenticationFilter;
import ru.akalavan.jwt.JWTLoginFilter;
import ru.akalavan.security.SecurityUserDetailsManager;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(SecurityBeanConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final SecurityUserDetailsManager userDetailManager;

    public SecurityConfig(SecurityUserDetailsManager userDetailManager) {
        this.userDetailManager = userDetailManager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/car").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailManager);
    }
}
