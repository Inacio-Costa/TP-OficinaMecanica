package br.com.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.mb.LoginBean;



public class Autorizador implements PhaseListener{
	private static final long serialVersionUID = 1L;
	
	// Executa alfo depois da frase. Em nosso caso ser� feira a verifica��o do que o usu�rio est� tentando acessar e se ele j� est� logado ou n�o.
	public void afterPhase(PhaseEvent event) {
		// Valida��o se a requisi��o veio a partir da tela de login.
		// Nesse caso, n�o � necess�rio validar se o mesmo est� logado ou n�o.
		FacesContext contexto = event.getFacesContext();
		if ("/login.xhtml".equals(contexto.getViewRoot().getViewId())) {
			return;
		}
		// caso o usu�rio esteja tentando acessar uma tela diferente da tela de login � necess�rio realizar a valida��o necess�ria.
		// Ser� necess�rio obter uma inst�ncia de LoginBean da sess�o, que cont�m o usu�rio logado. Em seguida, faz-se a verifica��o se o atributo usu�rio de loginBean est� nulo ou n�o.
		// Se o usu�rio n�o est� logado, faremos um redirecionamento do usu�rio para a tela de login.
		// Se estiver logado, ao final do m�todo o usu�rio continua na fase atual da aplica��o.
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
