package br.com.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dao.DAO;
import br.com.modelo.Item;
import br.com.modelo.OrdemServico;
import br.com.modelo.Peca;
import br.com.modelo.Veiculo;


@ViewScoped
@ManagedBean
public class OrdemServicoBean {
	
	private OrdemServico ordemServico  = new OrdemServico();	
	private List<OrdemServico> ordemServicoList;
	private List<OrdemServico> ordemServicoFilter;
	private Boolean retiraPeca = false;
	private DAO<OrdemServico> dao;
	
	private Item item = new  Item();
	
	private Long idPeca;
	private Long idVeiculo;

	public OrdemServicoBean() {
		System.err.println("Instancianando novamente");
		dao = new DAO<OrdemServico>(OrdemServico.class);
	}
	
	public Long getIdVeiculo() {
		return idVeiculo;
	}


	public void setIdVeiculo(Long idVeiculo) {
		this.idVeiculo = idVeiculo;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Long getIdPeca() {
		return idPeca;
	}

	public void setIdPeca(Long idPeca) {
		this.idPeca = idPeca;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}
	
	public void setOrdemServico(OrdemServico OrdemServico) {
		this.ordemServico = OrdemServico;
	}
	
	public List<OrdemServico> getOrdemServicoList() {
		return ordemServicoList;
	}

	public void setOrdemServicoList(List<OrdemServico> ordemServicoList) {
		this.ordemServicoList = ordemServicoList;
	}

	public List<OrdemServico> getOrdemServicoFilter() {
		return ordemServicoFilter;
	}

	public void setOrdemServicoFilter(List<OrdemServico> ordemServicoFilter) {
		this.ordemServicoFilter = ordemServicoFilter;
	}
	

	public Boolean getRetiraPeca() {
		return retiraPeca;
	}

	public void setRetiraPeca(Boolean retiraPeca) {
		this.retiraPeca = retiraPeca;
	}
	
	

	public void grava() {
		
		if (idVeiculo == null) return;
		
		Veiculo veiculo = new DAO<Veiculo>(Veiculo.class).buscaPorld(idVeiculo);
		
		ordemServico.setVeiculo(veiculo);
		
		if (ordemServico.getId() == null) {
			dao.adiciona(ordemServico);
			ordemServicoList.add(ordemServico);
		}else{
			dao.atualiza(ordemServico);
			ordemServicoList.set(ordemServicoList.indexOf(ordemServico), ordemServico);
		}
		
		ordemServico = new OrdemServico();
		item = new Item();
		idVeiculo = null;
		
		//ordemServicoList = dao.listaTodos();
		
		//return "wizard?faces-redirect=true";
	}
	
	public void remove(OrdemServico ordemServico) {
		dao.remove(ordemServico);
		ordemServicoList.remove(ordemServico);
		//this.ordemServicoList = dao.listaTodos();
	}
	
	public List<OrdemServico> getOrdemServicos() {
		if (ordemServicoList == null) {
			ordemServicoList = dao.listaTodos();
		}
		return ordemServicoList;
	}
	
	
	public void aprova(OrdemServico ordemServico){
		ordemServico.setStatus("Aprovada");
		dao.atualiza(ordemServico);
	}
	
	public void conclui(OrdemServico ordemServico){
		ordemServico.setStatus("Concluída");
		dao.atualiza(ordemServico);
	}
	
	public void pagamento(OrdemServico ordemServico){
		ordemServico.setStatus("Pago");
		dao.atualiza(ordemServico);
	}
	
	public void cancela() {
		ordemServico = new OrdemServico();
		idVeiculo = 0L;
		retiraPeca = false;
	}
	
	public void adicionaItem(){
		
		if (idPeca == null) return;
		
		DAO<Peca> pecaDAO = new DAO<>(Peca.class);
		
		// Buca peça
		Peca peca = pecaDAO.buscaPorld(idPeca);
		
		
		
		if (peca.getQuantidade() < item.getQuantidade()){
			System.out.println("quantidade de peça insuficiente");
			return ;
		}
		
		peca.setQuantidade(peca.getQuantidade() - item.getQuantidade());
		
		item.setPeca(peca);
		ordemServico.getItens().add(item);
		
		
		item.setOrdemServico(ordemServico);
		item = new Item();
		
		
	}
	
	public void removeItem(Item item){
		System.out.println("Item a ser removido: " + item.getPeca().getNome());
		
		
			ordemServico.getItens().remove(item);
			
			//item.setOrdemServico(ordemServico);
			
			//new DAO<>(OrdemServico.class).atualiza(ordemServico);
			new DAO<>(Item.class).remove(item);
			
			ordemServicoList = new DAO<>(OrdemServico.class).listaTodos();
	}
	
	public void carregaVeiculo(Long id){
		retiraPeca = false;
		idVeiculo = id;
	}
	
	
	
	public void retirarPeca(){
		retiraPeca = true;
	}
	
	

}