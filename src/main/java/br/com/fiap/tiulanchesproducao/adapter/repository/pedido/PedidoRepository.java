package br.com.fiap.tiulanchesproducao.adapter.repository.pedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.tiulanchesproducao.core.entitie.pedido.Pedido;
import br.com.fiap.tiulanchesproducao.core.enums.StatusPedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	@Query(value = "SELECT pe.* " + 
                   "  FROM pedidos pe " +
                   " WHERE pe.status = :#{#status?.ordinal()} ", nativeQuery = true)
	List<Pedido> findByStatus(@Param("status") StatusPedido status);
}
