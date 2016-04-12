package br.com.kelvinsantiago.controladores;

import br.com.kelvinsantiago.br.com.kelvinsantiago.util.Paginacao;
import br.com.kelvinsantiago.modelo.entidades.Usuario;
import br.com.kelvinsantiago.modelo.servicos.ServicoUsuario;
import javolution.util.FastMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.transport.ObjectTable;

import java.util.List;
import java.util.Map;

/**
 * Created by kelvin on 07/04/16.
 */

@RestController
@RequestMapping(value = "/usuarios",  consumes = "application/json; charset=UTF-8")
public class UsuarioController {

    @Autowired
    private ServicoUsuario servicoUsuario;

    @RequestMapping(value = "/buscar/{pagina}", method= RequestMethod.GET)
    public Map<String, Object> buscar(@PathVariable int pagina){

        Map<String, Object> mapa = new FastMap<>();

        Paginacao.montarMapa(mapa, servicoUsuario, "usuarios", pagina);

        return mapa;

    }

    @RequestMapping(value = "/{login}", method = RequestMethod.GET)
    public Map<String, Object> buscarUsuario(@PathVariable String login){

        Map<String, Object> mapa = new FastMap<>();
        Usuario usuario = servicoUsuario.findByLogin(login);
        mapa.put("usuario",usuario);

        return mapa;
    }

}
