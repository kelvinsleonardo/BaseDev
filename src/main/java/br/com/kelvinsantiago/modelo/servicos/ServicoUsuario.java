package br.com.kelvinsantiago.modelo.servicos;

import br.com.kelvinsantiago.modelo.entidades.Usuario;
import br.com.kelvinsantiago.modelo.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kelvin on 07/04/16.
 */

@Service
public class ServicoUsuario{

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public void salvar(Usuario usuario) {
        usuarioRepositorio.save(usuario);
    }

    public Iterable<Usuario> findAll() {
       Pageable paginacao = new PageRequest(0,1);
       return usuarioRepositorio.findAll(paginacao);
    }

    public Usuario findByLogin(String login){
        return usuarioRepositorio.findOneByLogin(login);
    }
}

