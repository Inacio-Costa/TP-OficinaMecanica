package br.com.teste;

import br.com.dao.UsuarioDAO;
import br.com.modelo.Usuario;

public class Teste {

	public static void main(String[] args) {
		
		Usuario usuario = new Usuario();
		usuario.setLogin("admin");
		usuario.setSenha("admin");
		
		if (new UsuarioDAO().existe(usuario) != null){
			System.out.println("Existe");
		}
			
	}

}
