package br.com.kelvinsantiago.configuracoes;

import br.com.kelvinsantiago.modelo.servicos.ServicoAutenticacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class ConfiguracaoSeguranca extends WebSecurityConfigurerAdapter {

    @Autowired
    private ServicoAutenticacao servicoAutenticacao;

    /*
     * Responsável por validar autenticação
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(servicoAutenticacao).passwordEncoder(encoder());
    }

    /*
     * Responsável por filtrar as requisições de login.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

            // Libera URLs abaixo somente para aquele role, e libera tudo.
            http.authorizeRequests()
                .antMatchers("/", "/login", "/web/**", "/static/**")
                .permitAll()
                .antMatchers("/**")
                .hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/autenticar")
                    .defaultSuccessUrl("/index.html")
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

        http.sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry());

    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SessionRegistryImpl sessionRegistry() {
        SessionRegistryImpl sessionRegistry = new SessionRegistryImpl();

        return sessionRegistry;
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senha = "admin";
        System.out.println(senha + " - " + encoder.encode("admin"));
    }

}
