package com.app.conf;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class AppWebSecurityConfig  extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private Environment env;
	

    private static final String SQL_LOGIN
            = "select USER_NAME as username, USER_PASSWORD as password, ACTIVE as enabled "
            + "from SC_USERS where USER_NAME = ?";

    private static final String SQL_PERMISSION
            = "select u.USER_NAME as username, r.ROLE_NAME as authority "
            + "from SC_USERS u join SC_USER_ROLES ur on u.USER_ID = ur.USER_ID "
            + "join SC_ROLES r on ur.ROLE_ID = r.ROLE_ID "
            + "where u.USER_NAME = ?";
    
    @Autowired
    private DataSource ds;
    
	
	 
	  @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth
	                .jdbcAuthentication()
	                .dataSource(ds)
	                .usersByUsernameQuery(SQL_LOGIN)
	                .authoritiesByUsernameQuery(SQL_PERMISSION);
	    }


	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	                .authorizeRequests()
	                .antMatchers("/assets/**").permitAll()
	                .antMatchers("/demo/**").permitAll()
	                .antMatchers("/docs/**").permitAll()
	                .antMatchers("/dashboard**").hasAnyRole("ADMIN")
	                 .antMatchers("/halo").hasAnyRole("ADMIN", "STAFF")
	                .antMatchers("/peserta/form").hasRole("ADMIN")
	                .antMatchers("/peserta/list").hasRole("STAFF")
	                .anyRequest().authenticated()
	                .and()
	                .formLogin()
	                .loginPage("/login").permitAll()
	                .defaultSuccessUrl(env.getProperty("login.default.index"))
	                .and()
	                .logout()
	                .and()
	                .addFilterAfter(new CsrfAttributeToCookieFilter(), CsrfFilter.class)
	                .csrf().csrfTokenRepository(csrfTokenRepository());
	    }

	    private CsrfTokenRepository csrfTokenRepository() {
	        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
	        repository.setHeaderName("X-XSRF-TOKEN");
	        return repository;
	    }
	    
	    @SuppressWarnings("deprecation")
	    @Bean
	    public static NoOpPasswordEncoder passwordEncoder() {
	    return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	    }
}
