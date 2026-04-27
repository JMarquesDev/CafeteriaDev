package com.joao.br.controllers;

import com.joao.br.entities.Cliente;
import com.joao.br.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "Gerenciamento de clientes da cafeteria")
@CrossOrigin(origins = "*") // Permite que o Front-end acesse a API
public class ClienteController {

	@Autowired
	private ClienteService service;

	@PostMapping
	@Operation(summary = "Cadastrar um novo cliente")
	public ResponseEntity<Cliente> cadastrar(@Valid @RequestBody Cliente cliente) {
		return ResponseEntity.ok(service.salvar(cliente));
	}

	@GetMapping
	@Operation(summary = "Listar todos os clientes")
	public List<Cliente> listar() {
		return service.listarTodos();
	}

	@GetMapping("/buscar")
	@Operation(summary = "Buscar cliente por nome")
	public List<Cliente> buscarPorNome(@RequestParam String nome) {
		return service.buscarPorNome(nome);
	}
}