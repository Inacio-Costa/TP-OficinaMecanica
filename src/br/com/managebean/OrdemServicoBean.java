package br.com.managebean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dao.DAO;
import br.com.modelo.OrdemServico;
import br.com.modelo.Peca;
import br.com.modelo.Veiculo;


@ViewScoped
@ManagedBean
public class OrdemServicoBean {
	
	private OrdemServico ordemServico = new OrdemServico();	
	private Veiculo veiculo = new Veiculo();
	private List<OrdemServico> ordemServicoList;
	private List<Peca> pecaList = new ArrayList<>();
	private Peca peca = new Peca();
	
	public OrdemServico getOrdemServico() {
		return ordemServico;
	}
	
	public void setOrdemServico(OrdemServico OrdemServico) {
		this.ordemServico = OrdemServico;
	}
	
	
	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	
	
	public List<OrdemServico> getOrdemServicoList() {
		return ordemServicoList;
	}

	public void setOrdemServicoList(List<OrdemServico> ordemServicoList) {
		this.ordemServicoList = ordemServicoList;
	}

	public Peca getPeca() {
		return peca;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}

	public void grava() {
		
		DAO<OrdemServico> dao = new DAO<OrdemServico>(OrdemServico.class);
		DAO<Veiculo> veiculoDAO = new DAO<Veiculo>(Veiculo.class);
		
		veiculo = veiculoDAO.buscaPorld(veiculo.getId());
		//System.out.println(veiculo.getPlaca());
		//ordemServico.setDataServico(ordemServico.getDataOrcamento());
		ordemServico.setStatus("CRIADA");
		//ordemServico.setServico("teste");
		ordemServico.setVeiculo(veiculo);
		//ordemServico.setValor(10.0F);
		
		ordemServico.setPecaList(pecaList);
		
		
		if (ordemServico.getId() == null) {
			dao.adiciona(ordemServico);
		}
		else
			dao.atualiza(ordemServico);
		
		this.ordemServico = new OrdemServico();
		this.veiculo = new Veiculo();
		this.ordemServicoList = dao.listaTodos();
	}
	
	public void remove(OrdemServico OrdemServico) {
		DAO<OrdemServico> dao = new DAO<OrdemServico>(OrdemServico.class);
		dao.remove(OrdemServico);
		this.ordemServicoList = dao.listaTodos();
	}
	
	public List<OrdemServico> getOrdemServicos() {
		if (ordemServicoList == null) {
			ordemServicoList = new DAO<OrdemServico>(OrdemServico.class).listaTodos();
		}
		return ordemServicoList;
	}
	
	public List<Peca> getPecas(){
		//ordemServicoList = new DAO<OrdemServico>(OrdemServico.class).listaTodos();
		//System.out.println(pecaList.size());
		return pecaList;
	}
	
	public void aprova(OrdemServico ordemServico){
		DAO<OrdemServico> dao = new DAO<OrdemServico>(OrdemServico.class);
		ordemServico.setStatus("APROVADA");
		dao.atualiza(ordemServico);
	}
	
	public void conclui(OrdemServico ordemServico){
		DAO<OrdemServico> dao = new DAO<OrdemServico>(OrdemServico.class);
		ordemServico.setStatus("CONCLUIDA");
		dao.atualiza(ordemServico);
	}
	
	public void cancela() {
		this.ordemServico = new OrdemServico();
		//pecaList = null;
	}

	public List<Peca> getPecaList() {
		return pecaList;
	}

	public void setPecaList(List<Peca> pecaList) {
		this.pecaList = pecaList;
	}
	
	public void adicionaPeca(){
		/*for (Peca i : pecaList)
			System.out.println(i.getNome());*/
		System.err.println(peca.getId());
		peca = new DAO<>(Peca.class).buscaPorld(peca.getId());
		if (peca != null)
			pecaList.add(peca);
	}
}
