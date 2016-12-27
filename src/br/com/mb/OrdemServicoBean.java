package br.com.mb;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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
	private int tab = 0;
	
	private Item item = new  Item();
	
	private Long idPeca;
	private Long idVeiculo;
	
	private DAO<OrdemServico> dao;

	public OrdemServicoBean() {
		System.err.println("Instancianando novamente");
		dao = new DAO<OrdemServico>(OrdemServico.class);
	}
	
	public Long getIdVeiculo() {
		return idVeiculo;
	}
	
	public int getTab() {
		return tab;
	}

	public void setTab(int tab) {
		this.tab = tab;
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
		
		if (idVeiculo == null){ 
			System.err.println("id do veiculo e nulo");
			return;
		}
		
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
		
		//return "wizard?faces-redirect=true";
	}
	
	public void remove(OrdemServico OrdemServico) {
		dao.remove(OrdemServico);
		ordemServicoList.remove(ordemServico);
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
		tab = 0;
	}
	
	public void adicionaItem(){
		
		if (idPeca == null){
			FacesMessage msg = new FacesMessage("Seleciona uma peça!");
			FacesContext.getCurrentInstance().addMessage("erro", msg);
			return;
		}
		
		DAO<Peca> pecaDAO = new DAO<>(Peca.class);
		
		// Buca peça
		Peca peca = pecaDAO.buscaPorld(idPeca);
		
		if (peca.getQuantidade() < item.getQuantidade()){
			FacesMessage msg = new FacesMessage("Quantidade de peças insuficiente!");
			FacesContext.getCurrentInstance().addMessage("erro", msg);
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
	}
	
	public void carregaVeiculo(Long id){
		retiraPeca = false;
		idVeiculo = id;
	}
	
	
	
	public void retirarPeca(Long id){
		retiraPeca = true;
		idVeiculo = id;
		tab = 1;
	}
	
	

}