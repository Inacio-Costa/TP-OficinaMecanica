package br.com.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dao.DAO;
import br.com.modelo.Peca;



@ViewScoped
@ManagedBean
public class PecaBean {
	private Peca Peca = new Peca();
	private List<Peca> Pecas;
	private List<Peca> pecaFilter;
	
	
	
	public Peca getPeca() {
		return Peca;
	}
	
	public void setPeca(Peca Peca) {
		this.Peca = Peca;
	}
	
	public List<Peca> getPecaFilter() {
		return pecaFilter;
	}

	public void setPecaFilter(List<Peca> pecaFilter) {
		this.pecaFilter = pecaFilter;
	}

	public void setPecas(List<Peca> pecas) {
		Pecas = pecas;
	}

	public void grava() {
		DAO<Peca> dao = new DAO<Peca>(Peca.class);
		if (Peca.getId() == null) {
			dao.adiciona(Peca);
		}
		else
			dao.atualiza(Peca);
		
		this.Peca = new Peca();
		this.Pecas = dao.listaTodos();
	}
	
	public List<Peca> getPecas() {
		if (Pecas == null) {
			Pecas = new DAO<Peca>(Peca.class).listaTodos();
		}
		return Pecas;
	}
	
	public void remove(Peca Peca) {
		DAO<Peca> dao = new DAO<Peca>(Peca.class);
		dao.remove(Peca);
		this.Pecas = dao.listaTodos();
	}
	
	public void cancela() {
		this.Peca = new Peca();
	}
}
