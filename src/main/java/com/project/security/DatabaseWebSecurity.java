package com.project.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username,password,status from user where username=?").

				authoritiesByUsernameQuery(
						"select u.username, p.profiles from userprofile up " + "inner join user u on u.id = up.idUser "
								+ "inner join profiles p on p.id = up.idProfile " + "where u.username = ?");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/bootstrap/**", "/images/**", "/tinymce/**", "/logos/**").permitAll()
				.antMatchers("/signup", "/search", "/vacancies/view/**", "/", "/bcrypt/**").permitAll()

				.antMatchers("/apply/{id}/**", "/saveRequest/**").hasAuthority("USUARIO")

				.antMatchers("/indexRequest/**").hasAnyAuthority("SUPERVISOR", "ADMINISTRADOR","USUARIO")
				.antMatchers("/vacancies/**").hasAnyAuthority("SUPERVISOR", "ADMINISTRADOR")
				.antMatchers("/categories/**").hasAnyAuthority("SUPERVISOR", "ADMINISTRADOR")
				.antMatchers("/user/index/**").hasAnyAuthority("ADMINISTRADOR")

				.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout()
				.permitAll();
		;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
