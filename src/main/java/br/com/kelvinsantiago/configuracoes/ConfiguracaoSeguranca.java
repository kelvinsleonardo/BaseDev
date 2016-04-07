package br.com.kelvinsantiago.configuracoes;

import br.com.kelvinsantiago.modelo.servicos.ServicoAutenticacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class ConfiguracaoSeguranca extends WebSecurityConfigurerAdapter {

	@Autowired  private ServicoAutenticacao servicoAutenticacao;

    /*
     * Responsável por validar autenticação
     */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(servicoAutenticacao)
			.passwordEncoder(encoder());
	}

    /*
     * Responsável por filtrar as requisições de login
     */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
            // Libera URLs abaixo somente para aquele role, e libera tudo.
			.authorizeRequests()
				.antMatchers("/usuarios/**","/pizzas/**", "/ingredientes/**").hasRole("ADMIN")
				.anyRequest().permitAll()
		.and()
			.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/autenticar")
				.defaultSuccessUrl("/paginainicial")
				.failureUrl("/login?semacesso=true")
				.usernameParameter("usuario")
				.passwordParameter("senha")
				
		.and()
				.logout()
					.logoutUrl("/sair")
					.logoutSuccessUrl("/login?saiu=true")
        .and()
            .csrf().disable() // Desabilitando Cross Site Request Forgery
            .headers().disable(); // Desabilitando cabeçalho

	}
	
	@Bean
	public BCryptPasswordEncoder encoder(){
		return new BCryptPasswordEncoder();
	}
	
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senha = "admin";
		System.out.println(senha + " - "+ encoder.encode("admin"));
	}
	
}
