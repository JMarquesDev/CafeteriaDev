package com.joao.br.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O nome é obrigatório")
	private String nome;

	@Email(message = "E-mail inválido")
	@NotBlank(message = "O e-mail é obrigatório")
	private String email;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	@JsonIgnore // Evita que o JSON entre em loop infinito entre Cliente e Pedido
	private List<Pedido> pedidos;

	// construtor vazio (Obrigatório para o Hibernate/JPA)
	public Cliente() {
	}

	// constutor com parametros
	public Cliente(Long id, String nome, String email, List<Pedido> pedidos) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.pedidos = pedidos;
	}

	// Getters e Setters
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public List<Pedido> getPedidos() { return pedidos; }
	public void setPedidos(List<Pedido> pedidos) { this.pedidos = pedidos; }
}