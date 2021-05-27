package br.com.alura.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Cliente;

public class ClienteDao {
	
	private EntityManager em;

	public ClienteDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Cliente cliente) {
		this.em.persist(cliente);
	}

	public void atualizar(Cliente cliente) {
		this.em.merge(cliente);
	}

	public void remover(Cliente cliente) {
		cliente = em.merge(cliente);
		this.em.remove(cliente);
	}

	public List<Cliente> buscarPorNome(String nome) {
		String jpql = "SELECT c FROM Cliente c WHERE c.nome = :nome";
		return em.createQuery(jpql, Cliente.class)
				.setParameter("nome", nome)
				.getResultList();
	}

}
