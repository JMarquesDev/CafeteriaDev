package com.joao.br.repositories;

import com.joao.br.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    
    // O JPA faz o "Join" entre as tabelas automaticamente pelo nome do método
    List<Pedido> findByClienteNomeContainingIgnoreCase(String nomeCliente);
}