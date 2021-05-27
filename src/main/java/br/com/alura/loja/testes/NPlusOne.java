package br.com.alura.loja.testes;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.util.JPAUtil;

public class NPlusOne {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.getEntityManager();

		// Query planejada: Cliente é LAZY, gerando N+1, então busco com JOIN FETCH
		List<Pedido> lista = new PedidoDao(em).buscarComCliente();  
		
		for (Pedido pedido: lista) {
			System.out.println(pedido.getCliente().getNome());
		}
		
		em.close();
		
		// Também, aqui fazer o getCliente() geraria um LazyInitializationException
	}

}
