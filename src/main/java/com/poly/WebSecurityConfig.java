package com.poly;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.dto.JwtResponse;
import com.poly.filter.JwtFilter;

@Configuration
@EnableWebSecurity
@Order(1)
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {    	
    	httpSecurity.cors().and().csrf().disable();
    	
    	httpSecurity.authorizeRequests()
        .antMatchers("/api/login").permitAll()
        .antMatchers("/api/**").hasAnyRole("ADMIN");

		httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		httpSecurity.exceptionHandling().accessDeniedHandler((rq, rp, e) -> {
			JwtResponse<String> responseDTO = new JwtResponse<>();
			responseDTO.setStatus(-401);
			responseDTO.setMessage("Bạn không có quyền làm điều này!");
			rp.setHeader("content-type", "application/json;charset=UTF-8");
			rp.getWriter().write(new ObjectMapper().writeValueAsString(responseDTO));
		});
    	
    	httpSecurity.httpBasic();
		httpSecurity
        .authorizeRequests()
          	.antMatchers("/cart/**").authenticated()
          	.antMatchers("/product/**").permitAll()
			.antMatchers("/admin/**").hasAnyRole("ADMIN")
			.and()
		.formLogin()
			.loginPage("/security/login/form")
			.loginProcessingUrl("/security/login")
			.defaultSuccessUrl("/security/login/success", false)
			.failureUrl("/security/login/error")
			.usernameParameter("email")
			.passwordParameter("password")
			.and()
		.rememberMe()
			.tokenValiditySeconds(86400)
			.and()
		.exceptionHandling()
			.accessDeniedPage("/security/unauthoried")
			.and()

		.logout()
			.logoutUrl("/security/logoff")
			.logoutSuccessUrl("/security/logoff/sucsess");
		
		
    }
}
