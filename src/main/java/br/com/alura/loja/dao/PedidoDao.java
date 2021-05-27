package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.vo.RelatorioDeVendasVO;

public class PedidoDao {
	
	private EntityManager em;

	public PedidoDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido);
	}

	public void atualizar(Pedido pedido) {
		this.em.merge(pedido);
	}

	public void remover(Pedido pedido) {
		pedido = em.merge(pedido);
		this.em.remove(pedido);
	}
	
	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return em.createQuery(jpql, BigDecimal.class).getSingleResult();
	}
	
	/*
	 * Tipos possíveis na lista:
	 * - Object[]: cada linha é um array de Object, cada posição é uma coluna
	 * - classe VO (construtor com os campos na ordem) com SELECT NEW
	 */
	public List<RelatorioDeVendasVO> relatorioDeVendas() {
		String jpql = 
				  "SELECT NEW br.com.alura.loja.vo.RelatorioDeVendasVO( " +
			      "     prod.nome, SUM(it.quantidade), MAX(ped.data) " + 
			      ") " +
	              "FROM Pedido ped " +
			      "JOIN ped.produtos it " + // JOIN pelo atributo!  
	              "JOIN it.produto prod " +
			      "GROUP BY prod.nome " +
	              "ORDER BY SUM(it.quantidade) DESC";
		return em.createQuery(jpql, RelatorioDeVendasVO.class).getResultList();
	}

	/*
	 * Query planejada: Cliente é LAZY, gerando N+1, então busco com JOIN FETCH
	 */
	public List<Pedido> buscarComCliente() {
		String jpql = "SELECT p FROM Pedido p JOIN FETCH p.cliente";
		return em.createQuery(jpql, Pedido.class).getResultList();
	}

}
