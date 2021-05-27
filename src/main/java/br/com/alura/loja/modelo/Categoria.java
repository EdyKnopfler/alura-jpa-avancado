package br.com.alura.loja.modelo;

import java.time.LocalDate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {
	
	// Chave composta!
	@EmbeddedId
	private CategoriaId id;
	
	private LocalDate dataCadastro = LocalDate.now();
	
	public Categoria() {}
	
	public Categoria(String nome, String tipo) {
		this.id = new CategoriaId(nome, tipo);
	}
	
	public CategoriaId getId() {
		return id;
	}
	
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}
	

}
