package com.joao.br.services;

import com.joao.br.entities.Cliente;
import com.joao.br.entities.Pedido;
import com.joao.br.repositories.ClienteRepository;
import com.joao.br.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	public Pedido salvarPedido(Pedido pedido, Long clienteId) {
		// Não é permitido cadastrar pedido sem cliente
		Cliente cliente = clienteRepository.findById(clienteId)
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

		// O valor do pedido deve ser maior que zero
		if (pedido.getValorTotal() == null || pedido.getValorTotal() <= 0) {
			throw new RuntimeException("O valor do pedido deve ser maior que zero!");
		}

		pedido.setCliente(cliente);
		return pedidoRepository.save(pedido);
	}

	public List<Pedido> listarTodos() {
		return pedidoRepository.findAll();
	}

	public List<Pedido> buscarPedidosPorCliente(String nome) {
		return pedidoRepository.findByClienteNomeContainingIgnoreCase(nome);
	}
}