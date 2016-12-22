package br.com.constantes;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean
public class Perfil {
	 private String gerente = "admin";
	 private String mecanico = "admin";
	 private String recepcionista = "admin";
	public String getGerente() {
		return gerente;
	}
	public void setGerente(String gerente) {
		this.gerente = gerente;
	}
	public String getMecanico() {
		return mecanico;
	}
	public void setMecanico(String mecanico) {
		this.mecanico = mecanico;
	}
	public String getRecepcionista() {
		return recepcionista;
	}
	public void setRecepcionista(String recepcionista) {
		this.recepcionista = recepcionista;
	}
}
