package com.anshu.springboot.musicDemo.config;

import javax.servlet.http.HttpServletResponse;

import com.anshu.springboot.musicDemo.middleware.CsrfTokenFilter;
import com.anshu.springboot.musicDemo.middleware.JwtTokenFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }

    @Bean
    public CsrfTokenFilter csrfTokenFilter() {
        return new CsrfTokenFilter();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().formLogin().disable().logout().disable().anonymous().disable().rememberMe().disable()
        .authorizeRequests()
        .antMatchers("/celeb/**")
        .authenticated();
        http.addFilterAfter(csrfTokenFilter(), CorsFilter.class)
        .addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling()
        .authenticationEntryPoint(
                        (request, response, ex) -> {
                            System.out.println("Unauthorized request - {}" + ex.getMessage());
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
                        }
                )
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addExposedHeader("Authorization");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
