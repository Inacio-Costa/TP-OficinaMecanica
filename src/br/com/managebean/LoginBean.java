package br.com.managebean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.NoResultException;

import br.com.dao.UsuarioDAO;
import br.com.modelo.Usuario;


// Permite que os dados do usu�rio sejam mantidos enquanto ele estiver logado.
@SessionScoped
@ManagedBean
public class LoginBean {
	
	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String efetuaLogin() {
		UsuarioDAO dao = new UsuarioDAO();
		
		boolean loginValido;
		try{
			//Busca o login do Usu�rio no banco de dados.
			usuario = dao.buscaPorLogin(usuario.getLogin());
			
			// Verifica login valido.
			loginValido = dao.existe(this.usuario);
			
		}catch (NoResultException e) {
			loginValido = false;
		}
		
		
		
		if (loginValido) {
			return "index?faces-redirect=true";
		}
		else {
			this.usuario = new Usuario();
			return "login";
		}
	}
	

	
	public boolean isUsuarioLogado() {
		return usuario.getLogin() != null;
	}
	
	public String logout() {
		this.usuario = new Usuario();
		return "login?faces-redirect=true";
	}
}
