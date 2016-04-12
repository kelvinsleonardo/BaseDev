package br.com.kelvinsantiago.br.com.kelvinsantiago.util;

import br.com.kelvinsantiago.modelo.entidades.IEntidade;
import br.com.kelvinsantiago.modelo.servicos.IService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * Created by labtime on 11/04/16.
 */
public class Paginacao {

    public static final int ITENS_POR_PAGINA = 2;

    public static void montarMapa(Map<String, Object> mapa, IService service, String nomeDaLista, int pagina){

        Pageable paginacao = new PageRequest((pagina - 1),ITENS_POR_PAGINA);

        Page<IEntidade> page = service.findAll(paginacao);

        mapa.put(nomeDaLista, page.getContent());
        mapa.put("numRegistros", page.getTotalElements());
        mapa.put("temProximaPagina", page.hasNext());
        mapa.put("numPaginas",page.getTotalPages());

    }
}
