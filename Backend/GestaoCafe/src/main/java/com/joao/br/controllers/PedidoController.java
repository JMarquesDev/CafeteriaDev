package com.joao.br.controllers;

import com.joao.br.entities.Pedido;
import com.joao.br.services.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@Tag(name = "Pedidos", description = "Gerenciamento de pedidos da cafeteria")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @PostMapping("/{clienteId}")
    @Operation(summary = "Registrar um novo pedido para um cliente específico")
    public ResponseEntity<Pedido> cadastrar(@PathVariable Long clienteId, @Valid @RequestBody Pedido pedido) {
        return ResponseEntity.ok(service.salvarPedido(pedido, clienteId));
    }

    @GetMapping
    @Operation(summary = "Listar todos os pedidos")
    public List<Pedido> listar() {
        return service.listarTodos();
    }

    @GetMapping("/buscar-por-cliente")
    @Operation(summary = "Buscar pedidos pelo nome do cliente")
    public List<Pedido> buscarPorCliente(@RequestParam String nome) {
        return service.buscarPedidosPorCliente(nome);
    }
}