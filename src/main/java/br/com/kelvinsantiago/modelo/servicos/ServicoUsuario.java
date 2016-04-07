package br.com.kelvinsantiago.modelo.servicos;

import br.com.kelvinsantiago.modelo.entidades.Usuario;
import br.com.kelvinsantiago.modelo.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kelvin on 07/04/16.
 */

@Service
public class ServicoUsuario {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public void salvar(Usuario usuario) {
        usuarioRepositorio.save(usuario);
    }

    public Iterable<Usuario> buscarTodos() {
        return usuarioRepositorio.findAll();
    }

}

