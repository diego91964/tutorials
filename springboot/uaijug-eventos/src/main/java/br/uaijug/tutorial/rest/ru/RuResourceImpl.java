package br.uaijug.tutorial.rest.ru;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

import org.neo4j.helpers.collection.IteratorUtil;
import org.neo4j.rest.graphdb.RestAPI;
import org.neo4j.rest.graphdb.RestGraphDatabase;
import org.neo4j.rest.graphdb.entity.RestNode;
import org.neo4j.rest.graphdb.query.RestCypherQueryEngine;
import org.neo4j.rest.graphdb.util.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.uaijug.tutorial.rest.domain.Evento;
import br.uaijug.tutorial.service.evento.EventoService;

/*@RestController
@RequestMapping("/evento")*/
/* @PreAuthorize("hasRole('READER')") */
public class RuResourceImpl {

	@Autowired
	EventoService eventoServices;

	public static String SERVER_ROOT_URI = "http://localhost:7474";

	/*
	 * @RequestMapping(value =
	 * "/buscarPercentualLotacaoEstimadaPorRestaurante/{idRestaurante}", method
	 * = RequestMethod.GET, produces = "text/plain; charset=utf-8")
	 * 
	 * @ResponseBody
	 * 
	 * @RolesAllowed({ "admin", "default-client" })
	 * 
	 * @Override public HttpEntity<String>
	 * buscarPercentualLotacaoEstimadaPorRestaurante(@PathVariable(
	 * "idRestaurante") Long idRestaurante) throws Exception { Integer lotacao =
	 * ruServices.buscarPercentualLotacaoEstimadaPorRestaurante(idRestaurante);
	 * return new ResponseEntity<String>(lotacao.toString(), HttpStatus.OK); }
	 */

	@RequestMapping(value = "/buscarEventos", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
	/* @PreAuthorize("hasRole('RU')") */
	@RolesAllowed({ "ADMIN" })
	public HttpEntity<String> buscarEventos() throws Exception {
		/*
		 * List<UfuLocaisRu> result = eventoServices.buscarTodosRestaurantes();
		 * 
		 * String json = new ObjectMapper().writeValueAsString(result);
		 * 
		 * getUserDetails();
		 */

		RestGraphDatabase gds = new RestGraphDatabase("http://172.17.0.26:7474/db/data", "neo4j", "root");

		/*
		 * Transaction t = gds.beginTx(); Node node = gds.createNode();
		 * node.setProperty("teste", "teste"); t.finish();
		 */

		//String query = "MATCH (evento:Evento) RETURN evento";
		String query = "MATCH (n:Evento)RETURN max(n.id)";
		RestAPI restAPI = gds.getRestAPI();
		RestCypherQueryEngine queryEngine = new RestCypherQueryEngine(restAPI);
		queryEngine = new RestCypherQueryEngine(restAPI);

		// final String queryString = "start n=node({reference}) return n";
		final QueryResult qr = queryEngine.query(query, new HashMap<String, Object>());
		final Collection<Map<String,Object>> result = IteratorUtil.asCollection(qr);
		Map<String,Object> firstEntry = result.iterator().next();
		
		RestNode restnode = (RestNode) firstEntry.get("evento");
		
		Evento evento = new Evento(restnode);
		
		
		/*Long id = 0l;
        for (Object anEntity: nodes) {
            Node aFriend = (Node)anEntity;
            id = aFriend.getId();
        }*/
			
		String json = new ObjectMapper().writeValueAsString(evento);
		return new ResponseEntity<String>(json, HttpStatus.OK);

	}

	/*
	 * private void getUserDetails() { UserDetails userDetails =
	 * (UserDetails)SecurityContextHolder.getContext().getAuthentication().
	 * getPrincipal(); System.out.println("username: " +
	 * userDetails.getUsername()); System.out.println("password: " +
	 * userDetails.getPassword()); System.out.println("active: " +
	 * userDetails.isEnabled());
	 * 
	 * Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
	 * SecurityContextHolder.getContext().getAuthentication().getAuthorities();
	 * for (GrantedAuthority authority : authorities) { System.out.println(
	 * "role: " + authority.getAuthority()); } }
	 */

}
