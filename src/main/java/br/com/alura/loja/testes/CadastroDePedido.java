package br.com.alura.loja.testes;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;
import br.com.alura.loja.vo.RelatorioDeVendasVO;

public class CadastroDePedido {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		List<Produto> produtos = new ProdutoDao(em).buscarPorNome("Xiaomi");
		Produto produto = produtos.get(0);

		List<Cliente> clientes = new ClienteDao(em).buscarPorNome("Sélh");
		Cliente cliente = clientes.get(0);
		
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, pedido, produto));
		
		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido);
		
		System.out.println("Total vendido: " + pedidoDao.valorTotalVendido());
		
		List<RelatorioDeVendasVO> relatorio = pedidoDao.relatorioDeVendas();
		
		for (RelatorioDeVendasVO itemRel : relatorio) {
			System.out.println(itemRel);
		}
		
		em.getTransaction().commit();
		em.close();
	}

}
