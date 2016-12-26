package br.com.mb;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.dao.UsuarioDAO;
import br.com.modelo.Usuario;


// Permite que os dados do usuário sejam mantidos enquanto ele estiver logado.
@SessionScoped
@ManagedBean
@FacesValidator("comecaComMaiuscula")
public class LoginBean implements Validator{
	
	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

	public String efetuaLogin() {
		UsuarioDAO dao = new UsuarioDAO();
		
		usuario = dao.existe(usuario);
		if(usuario != null) {
			return "index?faces-redirect=true";
		}
		else{
			FacesMessage msg = new FacesMessage("Usuário ou senha inválido!");
			FacesContext.getCurrentInstance().addMessage("erro", msg);
			usuario = new Usuario();
			//return "login?faces-redirect=true";
			return null;
		}
	}
	
	public boolean isUsuarioLogado() {
		return usuario.getLogin() != null;
	}
	
	public String logout() {
		this.usuario = new Usuario();
		return "login?faces-redirect=true";
	}

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		
		
	}
}
