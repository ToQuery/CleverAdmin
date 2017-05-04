package com.toquery.cleverweb.config;

import com.toquery.cleverweb.service.ISysUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ToQuery
 * @version 1.0
 * @date 17-4-25.
 */

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private ISysUserService sysUserService;

    @Resource
    private CleverWebAnthencationProder provider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //允许访问静态资源
        http.authorizeRequests()
//                .antMatchers("/test/**","/upload", "/css/**", "/js/**", "/images/**",
//                        "/resources/**", "/lib/**", "/skin/**", "/template/**")
                .antMatchers("/**")
                .permitAll();
        //所有的访问都需要权限验证
        http.authorizeRequests().anyRequest().authenticated();
        http.rememberMe()
                .rememberMeServices(rememberMeServices());
        //访问失败页url
        http.formLogin().failureUrl("/login?error").
                //登录信息保存
                        successHandler(loginSuccessHandler()).
                //访问成功页url
                        defaultSuccessUrl("/login")
                //默认访问页
                .loginPage("/login")
                .permitAll().and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                //注销失败跳转到登录页面
                .logoutSuccessUrl("/login").permitAll()
        ;

        // 允许iframe 嵌套
        http.headers().frameOptions().disable();
        //关闭csrf 防止循环定向
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
        web.ignoring().antMatchers("/webjars/**");
        web.ignoring().antMatchers("/img/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //采用自定义验证
        auth.authenticationProvider(provider);
        //需要采用加密
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * spring session
     * @return
     */
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


    /**
     * 用户或者管理员登录日志
     */
    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return new AuthenticationSuccessHandler(){
            /**
             * Called when a user has been successfully authenticated.
             *
             * @param request        the request which caused the successful authentication
             * @param response       the response
             * @param authentication the <tt>Authentication</tt> object which was created during
             */
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

            }
        };
    }
}
