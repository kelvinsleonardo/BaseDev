package br.com.kelvinsantiago.modelo.servicos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by labtime on 12/04/16.
 */
public interface IService<T> {

    public void salvar (T entidade );

    public void remover(long id);

    public T get(long id);

    public Page<T> findAll(Pageable paginacao);
}
