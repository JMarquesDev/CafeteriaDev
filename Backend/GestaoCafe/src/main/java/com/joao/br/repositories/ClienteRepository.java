package com.joao.br.repositories;

import com.joao.br.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Query Method para buscar por nome
    List<Cliente> findByNomeContainingIgnoreCase(String nome);
}
