package com.uca.tarea5.controller;



import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.uca.tarea5.dao.EstudianteDAO;
import com.uca.tarea5.domain.Estudiante;

@Transactional
@Controller
public class MainController {
	
	@Autowired
	private EstudianteDAO estudianteDao;	
	
	@RequestMapping(value="/inicio")
	public ModelAndView inicio() {
		ModelAndView mav = new ModelAndView();	
		Estudiante estudiante = new Estudiante();
		mav.addObject("estudiante", estudiante);
		mav.setViewName("index");
		return mav;
	}
	
	
	@RequestMapping(value="/inicio",method=RequestMethod.POST)//ESETE MODIFIQUE '/add'
	public ModelAndView insertOne(@Valid @ModelAttribute Estudiante estudiante, BindingResult result) {
		
		ModelAndView mav = new ModelAndView();		
		try {
			if(result.hasErrors()) {
					mav.setViewName("index");
			}else {
				estudianteDao.insert(estudiante);
				mav.setViewName("index");
			}					
		}catch(Exception e) {
			e.printStackTrace();
		}		
		
		return mav;
		
	}
	
	//Mostrar Lista de Estudiantes
	
	@RequestMapping("/listado")
	public ModelAndView listadoMain() {
		
		ModelAndView mav = new ModelAndView();
		
		List<Estudiante> estudiantes = null;
		try {
			estudiantes=estudianteDao.findAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("estudiantes", estudiantes);
		mav.setViewName("listado");
		
		return mav;		
	}

}