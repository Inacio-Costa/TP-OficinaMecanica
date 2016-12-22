package br.com.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.modelo.Usuario;

public class UsuarioDAO {
	public boolean existe(Usuario usuario) {
		EntityManager em = new JPAUtil().getEntityManager();
		Query query = em.createQuery("FROM Usuario u WHERE u.login = :pLogin AND u.senha=:pSenha");
		query.setParameter("pLogin", usuario.getLogin());
		query.setParameter("pSenha", usuario.getSenha());
		boolean encontrado = !query.getResultList().isEmpty();
		
		em.close();
		return encontrado;
	}
	
	public Usuario buscaPorLogin(String login) throws NoResultException{
		EntityManager em = new JPAUtil().getEntityManager();
		Query query = em.createQuery("FROM Usuario u WHERE u.login = :pLogin", Usuario.class);
		query.setParameter("pLogin", login);
		return (Usuario) query.getSingleResult();
	}
}


