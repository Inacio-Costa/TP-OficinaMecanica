package br.com.managebean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.dao.DAO;
import br.com.modelo.Peca;



@SessionScoped
@ManagedBean
public class PecaBean {
	private Peca Peca = new Peca();
	private List<Peca> Pecas = new DAO<Peca>(Peca.class).listaTodos();
	
	public Peca getPeca() {
		return Peca;
	}
	
	public void setPeca(Peca Peca) {
		this.Peca = Peca;
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
