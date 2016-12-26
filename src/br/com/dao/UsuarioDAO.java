package br.com.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.modelo.Usuario;



public class UsuarioDAO {
	public Usuario existe(Usuario usuario) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.login=:pLogin and u.login=:pSenha");
		query.setParameter("pLogin", usuario.getLogin());
		query.setParameter("pSenha", usuario.getSenha());
		
		
		Usuario usuarioConsulta;
		if (query.getResultList().isEmpty())
			usuarioConsulta = null;
		else
			usuarioConsulta = (Usuario) query.getResultList().get(0);
				
		em.getTransaction().commit();
		em.close();
		return usuarioConsulta;
	}
}


