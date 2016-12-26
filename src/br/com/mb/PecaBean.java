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
		DAO<Peca> dao = new DAO<Peca>(Peca.class);
		
		if (peca.getId() == null)
			dao.adiciona(peca);
		else
			dao.atualiza(peca);
		
		this.peca = new Peca();
		this.pecas = dao.listaTodos();
	}
	
	public List<Peca> getPecas() {
		if (pecas == null) {
			pecas = new DAO<Peca>(Peca.class).listaTodos();
		}
		return pecas;
	}
	
	public void remove(Peca Peca) {
		try{
			DAO<Peca> dao = new DAO<Peca>(Peca.class);
			dao.remove(Peca);
			this.pecas = dao.listaTodos();
		}catch (RollbackException e) {
			FacesMessage msg = new FacesMessage("Você não pode excluir uma peça usada em uma ordem de serviço!");
			FacesContext.getCurrentInstance().addMessage("erro", msg);
		}
	}
	
	public void cancela() {
		this.peca = new Peca();
	}
}
