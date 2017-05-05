package com.toquery.cleverweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @author ToQuery
 * @version 1.0
 */

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Resource
//    private ISysUserService sysUserService;

//    @Resource
//    private CleverWebAnthencationProder provider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().fullyAuthenticated();
        http.httpBasic();

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        http.cors().configurationSource(source);
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> inMemoryUserDetailsManagerConfigurer = auth.inMemoryAuthentication();
        inMemoryUserDetailsManagerConfigurer.withUser("admin").password("admin").roles("USER");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
        web.ignoring().antMatchers("/webjars/**");
        web.ignoring().antMatchers("/img/**");
    }

   /* *//**
     * spring session
     *//*
    @Bean
    RememberMeServices rememberMeServices() {
        SpringSessionRememberMeServices rememberMeServices = new SpringSessionRememberMeServices();
        // optionally customize
        rememberMeServices.setAlwaysRemember(true);
        return rememberMeServices;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Component
    class CleverWebAnthencationProder implements AuthenticationProvider {

        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            return null;
        }

        @Override
        public boolean supports(Class<?> aClass) {
            return false;
        }
    }


    *//**
     * 用户或者管理员登录日志
     *//*
    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            *//**
     * Called when a user has been successfully authenticated.
     *
     * @param request        the request which caused the successful authentication
     * @param response       the response
     * @param authentication the <tt>Authentication</tt> object which was created during
     *//*
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

            }
        };
    }*/
}
