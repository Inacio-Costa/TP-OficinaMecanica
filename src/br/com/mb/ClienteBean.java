package br.com.mb;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.RollbackException;

import br.com.dao.DAO;
import br.com.modelo.Cliente;



@ViewScoped
@ManagedBean
public class ClienteBean {
	private Cliente cliente = new Cliente();
	
	private List<Cliente> clientes;
	private List<Cliente> clientesFilter;
	
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<Cliente> getClientesFilter() {
		return clientesFilter;
	}

	public void setClientesFilter(List<Cliente> clientesFilter) {
		this.clientesFilter = clientesFilter;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public void grava() {
		DAO<Cliente> dao = new DAO<Cliente>(Cliente.class);
		
		if (cliente.getId() == null)
			dao.adiciona(cliente);
		else
			dao.atualiza(cliente);
		
		this.cliente = new Cliente();
		this.clientes = dao.listaTodos();
	}
	
	public List<Cliente> getClientes() {
		if (clientes == null) {
			clientes = new DAO<Cliente>(Cliente.class).listaTodos();
		}
		return clientes;
	}
	
	public void cancela() {
		this.cliente = new Cliente();
	}
	
	public void remove(Cliente Cliente) {
		try{	
			DAO<Cliente> dao = new DAO<Cliente>(Cliente.class);
			dao.remove(Cliente);
			this.clientes = dao.listaTodos();
		}catch (RollbackException e) {
			FacesMessage msg = new FacesMessage("Remova primeiro seus veículos!");
			FacesContext.getCurrentInstance().addMessage("erro", msg);
		}
	}
}
