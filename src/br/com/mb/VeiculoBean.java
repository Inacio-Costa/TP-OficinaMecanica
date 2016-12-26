package br.com.mb;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.RollbackException;

import br.com.dao.DAO;
import br.com.modelo.Cliente;
import br.com.modelo.Veiculo;

@ManagedBean
@ViewScoped
public class VeiculoBean {
	private Veiculo veiculo = new Veiculo();

	private List<Veiculo> veiculos;
	private List<Veiculo> veiculoFilter;
	private DAO<Veiculo> dao;

	private Long idCliente;
	

	public VeiculoBean() {
		 dao = new DAO<Veiculo>(Veiculo.class);
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}


	public List<Veiculo> getVeiculoFilter() {
		return veiculoFilter;
	}

	public void setVeiculoFilter(List<Veiculo> veiculoFilter) {
		this.veiculoFilter = veiculoFilter;
	}

	public String grava(){
		if (idCliente == null){
			FacesMessage msg = new FacesMessage("Selecione um cliente!");
			FacesContext.getCurrentInstance().addMessage("erro", msg);
			return null;
		}

		Cliente cliente = new DAO<Cliente>(Cliente.class).buscaPorld(idCliente);
		veiculo.setCliente(cliente);

		if (veiculo.getId()==null){
			dao.adiciona(veiculo);
			veiculos.add(veiculo);
		}else{
			dao.atualiza(veiculo);
			veiculos.set(veiculos.indexOf(veiculo), veiculo);
			System.err.println("Alterando veiculo");
		}


		veiculo = new Veiculo();
		veiculos = dao.listaTodos();

		return "veiculo?faces-redirect=true";
	}

	public void cancela(){
		veiculo = new Veiculo();
		idCliente = null;
	}

	public List<Veiculo> getVeiculos() {
		if (veiculos==null){
			veiculos = dao.listaTodos();
		}
		return veiculos;		
	}


	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

	public void remove(Veiculo veiculo){
		try{
			dao.remove(veiculo);
			veiculos.remove(veiculo);
			//this.veiculos = dao.listaTodos();
		
		}catch (RollbackException e) {
			FacesMessage msg = new FacesMessage("Remova as ordem de serviço referente ao veiculo!");
			FacesContext.getCurrentInstance().addMessage("erro", msg);
		}
	}

	public void carregaCliente(Long id){
		idCliente = id;
	}

}
