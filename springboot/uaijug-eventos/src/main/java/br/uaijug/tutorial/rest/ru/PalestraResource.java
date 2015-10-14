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

import br.uaijug.tutorial.rest.domain.Palestra;
import br.uaijug.tutorial.service.palestra.PalestraService;

@RestController
@RequestMapping("/palestra")
public class PalestraResource {

	@Autowired
	PalestraService palestraService;
	
	@RequestMapping(value = "/buscarPalestras/{idEvento}", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
	@RolesAllowed({ "ADMIN" })
	public HttpEntity<String> buscarPalestras(@PathVariable("idEvento") String idEvento) throws Exception {
		
		
		List<Palestra> palestras = palestraService.buscarPalestras(idEvento);
		
		String json = new ObjectMapper().writeValueAsString(palestras);
		return new ResponseEntity<String>(json, HttpStatus.OK);

	}
}
