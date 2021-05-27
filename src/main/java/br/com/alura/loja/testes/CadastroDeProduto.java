package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.Endereco;
import br.com.alura.loja.modelo.Livro;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {
	
	public static void main(String[] args) {
		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		Produto p = produtoDao.buscarPorId(1l);
		System.out.println(p.getPreco());
		
		List<Produto> todos = produtoDao.buscarPorNomeDaCategoria("Celulares");
		todos.forEach(p2 -> System.out.println(p.getNome()));
	
		BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("Xiaomi");
		System.out.println("Preco do Produto: " +precoDoProduto);
	}

	private static void popularBancoDeDados() {
		Categoria terror = new Categoria("Terror", "Entretenimento");
		Categoria celulares = new Categoria("Celulares", "Departamento");
		
		Livro livro = new Livro();
		livro.setNome("Frankenstein");
		livro.setPreco(new BigDecimal(300));
		livro.setCategoria(terror);
		livro.setAutor("Mary Shelley");
		
		Produto celular = new Produto("Xiaomi", "Xing-ling", new BigDecimal(800), celulares);
		
		Endereco endereco = new Endereco();
		endereco.setLogradouro("Rua da Amargura");
		
		Cliente cliente = new Cliente("Sélh", "444444", endereco);
		
		EntityManager em = JPAUtil.getEntityManager();
		
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(terror);
		categoriaDao.cadastrar(celulares);
		
		produtoDao.cadastrar(livro);
		produtoDao.cadastrar(celular);
		
		clienteDao.cadastrar(cliente);
		
		em.getTransaction().commit();
		em.close();
	}

}
