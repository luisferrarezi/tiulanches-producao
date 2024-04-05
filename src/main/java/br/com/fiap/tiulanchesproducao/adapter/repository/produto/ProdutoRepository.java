package br.com.fiap.tiulanchesproducao.adapter.repository.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.tiulanchesproducao.core.entitie.produto.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
}
