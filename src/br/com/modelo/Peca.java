package br.com.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Peca {
	@Id
	@SequenceGenerator(name="IdPeca", sequenceName="seq_peca")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IdPeca")
	private Long id;
	
	@NotEmpty(message="O campo nome não deve ser vazio!")
	private String nome;
	
	@NotEmpty(message="O campo fornecedor não deve ser vazio!")
	private String fornecedor;
	
	@NotEmpty(message="O campo tipo não deve ser vazio!")
	private String tipo;
	
	@NotEmpty(message="O campo marca não deve ser vazio!")
	private String marca;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	
	
}
