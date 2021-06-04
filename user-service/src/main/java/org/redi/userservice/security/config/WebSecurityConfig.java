package org.redi.userservice.security.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super.configure(http);
        //TODO: change to form based authentication
        http
                .csrf().disable() // to send post request without getting rejected..
                .authorizeRequests()
                    .antMatchers("/api/v*/registration/**") // permit
                    .permitAll()
                .anyRequest()
                .authenticated().and()
                .formLogin();

    }
}
