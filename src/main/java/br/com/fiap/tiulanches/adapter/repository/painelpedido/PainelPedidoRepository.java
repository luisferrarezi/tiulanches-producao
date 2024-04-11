package br.com.fiap.tiulanches.adapter.repository.painelpedido;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.tiulanches.core.entitie.pedido.Pedido;

public interface PainelPedidoRepository extends JpaRepository <Pedido, Long>{
	
	@Query(value = "SELECT pe.* " + 
	               "  FROM pedidos pe " +	               
	               " WHERE pe.status IN (:#{#recebido}, :#{#preparacao}, :#{#pronto}) " + 
	               " ORDER BY pe.status DESC, pe.id_pedido ", nativeQuery = true)
	List<Pedido> consultaPedidos(@Param("recebido") int recebido,
    							 @Param("preparacao") int preparacao,
								 @Param("pronto") int pronto);
}
