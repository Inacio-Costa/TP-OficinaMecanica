package br.com.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Cliente {
	@Id
	@SequenceGenerator(name="IdCliente", sequenceName="seq_cliente")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IdCliente")
	private Long id;
	
	@NotEmpty(message="O campo nome não pode ser vazio!")
	private String nome; 
	
	@NotEmpty(message="O campo telefone não pode ser vazio!")
	private String telefone;
	
	@CPF
	@Column(unique=true)
	private String cpf;
	
	@NotEmpty(message="O campo endereço não pode ser vazio!")
	private String endereco;
	
	@NotEmpty(message="O campo email não pode ser vazio!")
	private String email;
	
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
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}
