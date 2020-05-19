package com.uca.tarea5.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.dao.DataAccessException;

import com.uca.tarea5.domain.Estudiante;

public interface EstudianteDAO {

	public List<Estudiante>findAll() throws DataAccessException;
	
	@Transactional
	public void insert (Estudiante estudiante) throws DataAccessException;
	
}
