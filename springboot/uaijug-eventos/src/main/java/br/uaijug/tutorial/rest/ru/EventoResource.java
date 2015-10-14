package br.uaijug.tutorial.rest.ru;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.uaijug.tutorial.rest.domain.Evento;
import br.uaijug.tutorial.service.evento.EventoService;

@RestController
@RequestMapping("/evento")
public class EventoResource {

	@Autowired
	EventoService eventoService;
	
	@RequestMapping(value = "/buscarEventos", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
	@RolesAllowed({ "ADMIN" })
	public HttpEntity<String> buscarEventos() throws Exception {
		
		
		List<Evento> eventos = eventoService.buscarEventos();
		
		String json = new ObjectMapper().writeValueAsString(eventos);
		return new ResponseEntity<String>(json, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/buscarEventoPorId/{id}", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
	@RolesAllowed({ "ADMIN" })
	public HttpEntity<String> buscarEventoPorId(
			@PathVariable("id") String id) throws Exception {
		
		Evento evento = eventoService.buscarEventoPorId(id);
		
		String json = new ObjectMapper().writeValueAsString(evento);
		return new ResponseEntity<String>(json, HttpStatus.OK);
		
		
	}
}
