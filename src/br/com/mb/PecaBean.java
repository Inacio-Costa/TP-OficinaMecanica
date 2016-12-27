package br.com.mb;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.RollbackException;

import br.com.dao.DAO;
import br.com.modelo.Peca;

@ViewScoped
@ManagedBean
public class PecaBean {
	private Peca peca = new Peca();
	private List<Peca> pecas;
	private List<Peca> pecaFilter;
	private DAO<Peca> dao;
	
	
	public PecaBean() {
		dao = new DAO<Peca>(Peca.class);
	}

	public Peca getPeca() {
		return peca;
	}
	
	public void setPeca(Peca Peca) {
		this.peca = Peca;
	}
	
	public List<Peca> getPecaFilter() {
		return pecaFilter;
	}

	public void setPecaFilter(List<Peca> pecaFilter) {
		this.pecaFilter = pecaFilter;
	}

	public void setPecas(List<Peca> pecas) {
		this.pecas = pecas;
	}

	public void grava() {		
		if (peca.getId() == null){
			dao.adiciona(peca);
			pecas.add(peca);
		}else{
			dao.atualiza(peca);
			pecas.set(pecas.indexOf(peca), peca);
		}
		
		this.peca = new Peca();
		//this.pecas = dao.listaTodos();
	}
	
	public List<Peca> getPecas() {
		if (pecas == null) {
			pecas = dao.listaTodos();
		}
		return pecas;
	}
	
	public void remove(Peca peca) {
		try{
			dao.remove(peca);
			pecas.remove(peca);
			//this.pecas = dao.listaTodos();
		}catch (RollbackException e) {
			FacesMessage msg = new FacesMessage("Você não pode excluir uma peça usada em uma ordem de serviço!");
			FacesContext.getCurrentInstance().addMessage("erro", msg);
		}
	}
	
	public void cancela() {
		this.peca = new Peca();
	}
}
