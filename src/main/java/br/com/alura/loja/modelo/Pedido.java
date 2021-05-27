package br.com.alura.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate data = LocalDate.now();
	
	@Column(name = "valor_total")
	private BigDecimal valorTotal = new BigDecimal(0);
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;
	
	/*
	 * RELACIONAMENTO BIDIRECIONAL
	 * 
	 * A JPA não sabe que é o mesmo relacionamento que o "@ManyToOne pedido" no item. Por padrão,
	 * ela irá criar uma outra tabela de join para este!
	 * 
	 * O atributo mappedBy indica que já está mapeado do outro lado, no atributo "pedido".
	 * 
	 * O cascade não é somente para atualizar e remover, também para na hora do "persist" já aplicar nos
	 * itens (sem isso somente o pedido é salvo).
	 */
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> produtos = new ArrayList<>();
	
	public Pedido() {
	}

	public Pedido(Cliente cliente) {
		this.cliente = cliente;
	}
	
	/*
	 * BOA PRÁTICA
	 * 
	 * Criar um método para adicionar item, garantindo que os dois lados do relacionamento bidirecional
	 * se conheçam.
	 */
	public void adicionarItem(ItemPedido item) {
		item.setPedido(this);
		produtos.add(item);
		valorTotal = valorTotal.add(item.getValor());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public LocalDate getData() {
		return data;
	}


	public void setData(LocalDate data) {
		this.data = data;
	}


	public BigDecimal getValorTotal() {
		return valorTotal;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedido> getItens() {
		return produtos;
	}


	
}
