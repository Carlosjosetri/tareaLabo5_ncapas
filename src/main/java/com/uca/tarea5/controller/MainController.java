package com.uca.tarea5.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uca.tarea5.dao.EstudianteDAO;
import com.uca.tarea5.domain.Estudiante;


@Controller
public class MainController {
	
	@Autowired
	private EstudianteDAO estudianteDAO;
	
	@RequestMapping("/inicio")
	public ModelAndView initMain() {
		ModelAndView mav = new ModelAndView();
		Estudiante estudiante = new Estudiante();
		mav.addObject("estudiante", estudiante);
		mav.setViewName("index");
		
		return mav;
	}
	
	
	@RequestMapping("/ingresarEstudiante")
	public ModelAndView ingresarEstudiante(@Valid @ModelAttribute Estudiante est, BindingResult res) {
		ModelAndView mav = new ModelAndView();
		
		if(res.hasErrors()) {
			mav.setViewName("index");
		}else {
			estudianteDAO.insert(est);
			mav.setViewName("index");
		}
		
		return mav;
	}
	
	@RequestMapping("/listado")
	public ModelAndView listado() {
		ModelAndView mav = new ModelAndView();
		List<Estudiante> estudiantes = null;
		
		try {
			estudiantes = estudianteDAO.findAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		mav.addObject("estudiantes", estudiantes);
		mav.setViewName("listado");
		
		return mav;
	}
}
	