package com.joao.br.dtos;

import java.time.LocalDateTime;

public record PedidoDTO(
    Long id, 
    String descricao, 
    Double valorTotal, 
    LocalDateTime dataPedido, 
    Long clienteId 
) {}