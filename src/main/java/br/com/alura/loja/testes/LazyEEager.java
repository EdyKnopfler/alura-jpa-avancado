package br.com.alura.loja.testes;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.util.JPAUtil;

public class LazyEEager {
	
	public static void main(String[] args) {
		EntityManager em = JPAUtil.getEntityManager();
		
		/*
		 * Por padrão:
		 * - relacionamento *ToOne: carrega automaticamente (é feito o join).
		 * - relacionamento *ToMany: aguarda o acesso.
		 * Boa prática: colocar o FetchType.LAZY nos *ToOne
		 * IMPACTO (próximo vídeo): N+1?
		 */
		Pedido pedido = em.find(Pedido.class, 1L);  // Não carrega o Cliente do Pedido
		System.out.println(pedido.getData());
		
		System.out.println(pedido.getItens().size());
		
		em.close();
	}

}
