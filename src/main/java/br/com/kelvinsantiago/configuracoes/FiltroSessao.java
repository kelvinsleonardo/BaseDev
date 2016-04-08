package br.com.kelvinsantiago.configuracoes;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by labtime on 08/04/16.
 */
public class FiltroSessao implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent session) {
        // Definindo tempo de sessão para 30 minutos.
        session.getSession().setMaxInactiveInterval(30 * 60);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent session) {

    }
}
