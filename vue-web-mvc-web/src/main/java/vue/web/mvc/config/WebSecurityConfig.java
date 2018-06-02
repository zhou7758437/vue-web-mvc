package vue.web.mvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * Author     : zh_zhou
 * Create at  : 2017/12/20 15:35
 * Description:
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private MyAuthenticationProvider provider;//自定义验证

    @Autowired
    SecurityContextRepository securityContextRepository;

    @Bean
    SecurityContextRepository getSecurityContextRepository(){
        return new HttpSessionSecurityContextRepository();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String logoutUrl="/spring/user/logout";
        RequestMatcher logoutRequestMatcher = new OrRequestMatcher(
                new AntPathRequestMatcher(logoutUrl, "GET"),
                new AntPathRequestMatcher(logoutUrl, "POST"),
                new AntPathRequestMatcher(logoutUrl, "PUT"),
                new AntPathRequestMatcher(logoutUrl, "DELETE")
        );

        http
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/open.html")
                .permitAll()
                .and()
                .csrf()
                .disable()
                .logout()
                .logoutUrl(logoutUrl)
                .logoutSuccessUrl("/open.html")
                .permitAll();
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        .and()
                .securityContext();
        http.setSharedObject(SecurityContextRepository.class,securityContextRepository);
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/public/**",
                "/**/open.html",
                "/default/**",
                "/api/**",
                "/**/favicon.ico");

    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .and()
                .authenticationProvider(provider)
                .userDetailsService(userDetailsService);
    }
}