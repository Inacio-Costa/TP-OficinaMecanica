package br.com.managebean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.dao.DAO;
import br.com.modelo.Cliente;



@SessionScoped
@ManagedBean
public class ClienteBean {
	private Cliente Cliente = new Cliente();
	private List<Cliente> Clientes = new DAO<Cliente>(Cliente.class).listaTodos();
	
	public Cliente getCliente() {
		return Cliente;
	}
	
	public void setCliente(Cliente Cliente) {
		this.Cliente = Cliente;
	}
	
	public void grava() {
		DAO<Cliente> dao = new DAO<Cliente>(Cliente.class);
		if (Cliente.getId() == null) {
			validarCpf(Cliente.getCpf());
			dao.adiciona(Cliente);
			
		}
		else
			dao.atualiza(Cliente);
		
		this.Cliente = new Cliente();
		this.Clientes = dao.listaTodos();
	}
	
	public List<Cliente> getClientes() {
		if (Clientes == null) {
			Clientes = new DAO<Cliente>(Cliente.class).listaTodos();
		}
		return Clientes;
	}
	
	public void cancela() {
		this.Cliente = new Cliente();
	}
	
	public void remove(Cliente Cliente) {
		DAO<Cliente> dao = new DAO<Cliente>(Cliente.class);
		dao.remove(Cliente);
		this.Clientes = dao.listaTodos();
	}
	
	private boolean validarCpf(String cpf) {
		  if (cpf.equals("11111111111") || cpf.equals("22222222222")
			       || cpf.equals("33333333333")
			       || cpf.equals("44444444444")
			       || cpf.equals("55555555555")
			       || cpf.equals("66666666666")
			       || cpf.equals("77777777777")
			       || cpf.equals("88888888888")
			       || cpf.equals("99999999999")) {

			              return true;
			         }

			     return false;
	}
}
