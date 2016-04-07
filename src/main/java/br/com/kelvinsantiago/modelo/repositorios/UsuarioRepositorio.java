package br.com.kelvinsantiago.modelo.repositorios;

import br.com.kelvinsantiago.modelo.entidades.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends CrudRepository<Usuario, Long> {

	public Usuario findOneByLoginUnico(String login);

}
