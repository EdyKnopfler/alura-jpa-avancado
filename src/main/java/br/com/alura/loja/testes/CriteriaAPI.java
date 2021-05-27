package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CriteriaAPI {
	
	public static void main(String[] args) {
		EntityManager em = JPAUtil.getEntityManager();
		
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		List<Produto> porPreco = produtoDao.buscarPorParametros(null, new BigDecimal(800), null);
		for (Produto p : porPreco) {
			System.out.println(p.getNome());
		}
		
		List<Produto> porNome = produtoDao.buscarPorParametros("Frankenstein", null, null);
		for (Produto p : porNome) {
			System.out.println(p.getNome() + " - " + p.getPreco());
		}
		
		List<Produto> porData = produtoDao.buscarPorParametros(null, null, LocalDate.of(2021, 5, 27));
		for (Produto p : porData) {
			System.out.println(p.getNome() + " - " + p.getDataCadastro());
		}		
		
		em.close();
	}

}
