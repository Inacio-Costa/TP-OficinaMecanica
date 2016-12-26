package br.com.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Item {
	@Id
	@SequenceGenerator(name="item_id", sequenceName="item_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="item_id")
	private Long id;
	private Integer quantidade;

	
	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH})
	
	private Peca peca;
	
	@ManyToOne
	private OrdemServico ordemServico;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}


	public Peca getPeca() {
		return peca;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}
}
