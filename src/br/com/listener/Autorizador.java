package br.com.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.mb.LoginBean;



public class Autorizador implements PhaseListener{
	private static final long serialVersionUID = 1L;
	
	// Executa alfo depois da frase. Em nosso caso será feira a verificação do que o usuário está tentando acessar e se ele já está logado ou não.
	public void afterPhase(PhaseEvent event) {
		// Validação se a requisição veio a partir da tela de login.
		// Nesse caso, não é necessário validar se o mesmo está logado ou não.
		FacesContext contexto = event.getFacesContext();
		if ("/login.xhtml".equals(contexto.getViewRoot().getViewId())) {
			return;
		}
		// caso o usuário esteja tentando acessar uma tela diferente da tela de login é necessário realizar a validação necessária.
		// Será necessário obter uma instância de LoginBean da sessão, que contém o usuário logado. Em seguida, faz-se a verificação se o atributo usuário de loginBean está nulo ou não.
		// Se o usuário não está logado, faremos um redirecionamento do usuário para a tela de login.
		// Se estiver logado, ao final do método o usuário continua na fase atual da aplicação.
		LoginBean loginBean = contexto.getApplication().evaluateExpressionGet(contexto, "#{loginBean}", LoginBean.class);
		
		if (!loginBean.isUsuarioLogado()) {
			NavigationHandler handler = contexto.getApplication().getNavigationHandler();
			handler.handleNavigation(contexto, null, "login?faces-redirect=true");
			contexto.renderResponse();
		}
	}
	
	
	public void beforePhase(PhaseEvent arg0) {
	}

	// Recupera o identificador atual de uma determinada fase.
	// Informamos que queremos executar o Phanelistener na parte de recupear uma view.
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
	
}
