package com.uca.tarea5.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.uca.tarea5.domain.Estudiante;

@Repository
public class EstudianteDAOImpl implements EstudianteDAO {

	@PersistenceContext(unitName="tarea5")
	public EntityManager entityManager;
	
	@Override
	public List<Estudiante> findAll() throws DataAccessException {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM PUBLIC.ESTUDIANTE;");//SINO FUNCIONA QUITAR ';' A LA QUERY
		Query query = entityManager.createNativeQuery(sb.toString(),Estudiante.class);
		List <Estudiante> result= query.getResultList();
		return result;
	}
	
	@Override
	public void insert (Estudiante estudiante) throws DataAccessException {
		entityManager.persist(estudiante);		
	}
	
	

}
