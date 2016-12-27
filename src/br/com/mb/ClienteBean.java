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
	private DAO<Cliente> dao;
	
	
	public ClienteBean() {
		dao = new DAO<Cliente>(Cliente.class);
	}

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
		if (cliente.getId() == null){
			try{
				dao.adiciona(cliente);
				clientes.add(cliente);
			}catch (RollbackException e) {
				FacesMessage msg = new FacesMessage("Cliente já cadastrado!");
				FacesContext.getCurrentInstance().addMessage("erro", msg);
			}
		}else{
			dao.atualiza(cliente);
			clientes.set(clientes.indexOf(cliente), cliente);
		}
		
		this.cliente = new Cliente();
		//this.clientes = dao.listaTodos();
	}
	
	public List<Cliente> getClientes() {
		if (clientes == null) {
			clientes = dao.listaTodos();
		}
		return clientes;
	}
	
	public void cancela() {
		this.cliente = new Cliente();
	}
	
	public void remove(Cliente cliente) {
		try{			
			dao.remove(cliente);
			System.out.println("remove clienteBean");
			clientes.remove(cliente);
			//this.clientes = dao.listaTodos();
		}catch (RollbackException e) {
			FacesMessage msg = new FacesMessage("Remova primeiro seus veículos!");
			FacesContext.getCurrentInstance().addMessage("erro", msg);
		}
	}
}
