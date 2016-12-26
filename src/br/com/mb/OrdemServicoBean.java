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
	
	private Item item = new  Item();
	
	private Long idPeca;
	private Long idVeiculo;

	public OrdemServicoBean() {
		System.err.println("Instancianando novamente");
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

	public void grava() {
		
		if (idVeiculo == null) return;
		
		DAO<OrdemServico> dao = new DAO<OrdemServico>(OrdemServico.class);
		Veiculo veiculo = new DAO<Veiculo>(Veiculo.class).buscaPorld(idVeiculo);
		
		ordemServico.setVeiculo(veiculo);
		
		if (ordemServico.getId() == null) {
			dao.adiciona(ordemServico);
		}else{
			dao.atualiza(ordemServico);
		}
		
		
		ordemServico = new OrdemServico();
		item = new Item();
		idVeiculo = null;
		
		ordemServicoList = dao.listaTodos();
		
		//return "wizard?faces-redirect=true";
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
	
	
	public void aprova(OrdemServico ordemServico){
		DAO<OrdemServico> dao = new DAO<OrdemServico>(OrdemServico.class);
		ordemServico.setStatus("Aprovada");
		dao.atualiza(ordemServico);
	}
	
	public void conclui(OrdemServico ordemServico){
		DAO<OrdemServico> dao = new DAO<OrdemServico>(OrdemServico.class);
		ordemServico.setStatus("Conclu�da");
		dao.atualiza(ordemServico);
	}
	
	public void pagamento(OrdemServico ordemServico){
		DAO<OrdemServico> dao = new DAO<OrdemServico>(OrdemServico.class);
		ordemServico.setStatus("Pago");
		dao.atualiza(ordemServico);
	}
	
	public void cancela() {
		ordemServico = new OrdemServico();
		idVeiculo = 0L;
	}
	
	public void adicionaItem(){
		
		if (idPeca == null) return;
		
		DAO<Peca> pecaDAO = new DAO<>(Peca.class);
		
		// Buca pe�a
		Peca peca = pecaDAO.buscaPorld(idPeca);
		
		
		
		if (peca.getQuantidade() < item.getQuantidade()){
			System.out.println("quantidade de pe�a insuficiente");
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
			
			item.setOrdemServico(ordemServico);
			
			new DAO<>(OrdemServico.class).atualiza(ordemServico);
			
			ordemServicoList = new DAO<>(OrdemServico.class).listaTodos();
	}
	
	public void carregaVeiculo(Long id){
		idVeiculo = id;
	}
	

}