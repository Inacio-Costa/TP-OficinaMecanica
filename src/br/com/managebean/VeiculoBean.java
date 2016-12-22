package br.com.managebean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dao.DAO;
import br.com.modelo.Cliente;
import br.com.modelo.Veiculo;


@ManagedBean
@ViewScoped
public class VeiculoBean {
	private Veiculo veiculo = new Veiculo();
	private Cliente cliente = new Cliente();
	private List<Veiculo> veiculos;
	
	

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void grava(){
		DAO<Veiculo> dao = new DAO<Veiculo>(Veiculo.class);
		DAO<Cliente> daoCliente = new DAO<Cliente>(Cliente.class);
		
		cliente = daoCliente.buscaPorld(cliente.getId());
		veiculo.setCliente(cliente);
		
		if (veiculo.getId()==null)
			dao.adiciona(veiculo);
		else
			dao.atualiza(veiculo);
		this.veiculo = new Veiculo();
		this.cliente = new Cliente();
		this.veiculos = dao.listaTodos();
	}
	
	public void cancela(){
		veiculo = new Veiculo();
		cliente = new Cliente();
	}

	public List<Veiculo> getVeiculos() {
		if (veiculos==null){
			DAO<Veiculo> dao = new DAO<Veiculo>(Veiculo.class);
			veiculos = dao.listaTodos();
		}
		return veiculos;		
	}

	public void altera(Cliente cliente){
		this.cliente = cliente;
	}
	
	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}
	
	public void remove(Veiculo veiculo){
		DAO<Veiculo> dao =new DAO<Veiculo>(Veiculo.class);
		dao.remove(veiculo);		
		this.veiculos = dao.listaTodos();
	}
	
	
}
