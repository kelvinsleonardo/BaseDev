package br.com.kelvinsantiago.modelo.servicos;

import br.com.kelvinsantiago.modelo.entidades.Usuario;
import br.com.kelvinsantiago.modelo.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kelvin on 07/04/16.
 */

@Service
public class ServicoUsuario implements IService<Usuario>{

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public Page<Usuario> findAll(Pageable paginacao) {
        return usuarioRepositorio.findAll(paginacao);
    }

    public Usuario findByLogin(String login){
        return usuarioRepositorio.findOneByLogin(login);
    }

    @Override
    public void salvar(Usuario usuario) {
        usuarioRepositorio.save(usuario);
    }

    @Override
    public void remover(long id) {

    }

    @Override
    public Usuario get(long id) {
        return null;
    }


}

