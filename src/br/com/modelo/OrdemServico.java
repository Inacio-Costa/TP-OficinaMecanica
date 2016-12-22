package br.com.modelo;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ordemserv")
public class OrdemServico {
	@Id
	@SequenceGenerator(name="IdOrdemServico", sequenceName="seq_ordemservico")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IdOrdemServico")
	private Long id;
	
	@OneToOne
	private Veiculo veiculo;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataOrcamento = Calendar.getInstance();
	
	//@Temporal(TemporalType.DATE)
	private Calendar dataServico = Calendar.getInstance();
	
	//@NotEmpty(message="O campo valor não deve ser vazio!")
	private float valor;
	
	//@NotEmpty(message="O campo status não deve ser vazio!")
	private String status; 
	
	//@NotEmpty(message="O campo servico não deve ser vazio!")
	private String servico;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Peca> pecaList;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	public Calendar getDataOrcamento() {
		return dataOrcamento;
	}
	public void setDataOrcamento(Calendar dataOrcamento) {
		this.dataOrcamento = dataOrcamento;
	}
	public Calendar getDataServico() {
		return dataServico;
	}
	public void setDataServico(Calendar dataServico) {
		this.dataServico = dataServico;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getServico() {
		return servico;
	}
	public void setServico(String servico) {
		this.servico = servico;
	}
	public List<Peca> getPecaList() {
		return pecaList;
	}
	public void setPecaList(List<Peca> pecaList) {
		this.pecaList = pecaList;
	}
	
}
