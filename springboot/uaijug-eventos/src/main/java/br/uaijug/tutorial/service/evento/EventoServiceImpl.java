package br.uaijug.tutorial.service.evento;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.neo4j.helpers.collection.IteratorUtil;
import org.neo4j.rest.graphdb.RestAPI;
import org.neo4j.rest.graphdb.RestGraphDatabase;
import org.neo4j.rest.graphdb.entity.RestNode;
import org.neo4j.rest.graphdb.query.RestCypherQueryEngine;
import org.neo4j.rest.graphdb.util.QueryResult;
import org.springframework.stereotype.Service;

import br.uaijug.tutorial.rest.domain.Evento;
import br.uaijug.tutorial.utils.Constantes;

@Service
public class EventoServiceImpl implements EventoService {

	
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Evento> buscarEventos(){
		
		RestGraphDatabase gds = new RestGraphDatabase(Constantes.SERVER_ROOT_URI, Constantes.USERNAME, Constantes.PASSWORD);
		
		String query = "MATCH (evento:Evento)  RETURN evento";
		
		RestAPI restAPI = gds.getRestAPI();
		RestCypherQueryEngine queryEngine = new RestCypherQueryEngine(restAPI);
		queryEngine = new RestCypherQueryEngine(restAPI);

		final QueryResult qr = queryEngine.query(query, new HashMap<String, Object>());
		final Collection<Map<String,Object>> result = IteratorUtil.asCollection(qr);
		
		Iterator<Map<String, Object>> itResults = result.iterator();
		List<Evento> eventos = new ArrayList<>();
		
		if (itResults.hasNext()) {
			do {
				Map<String,Object> entry = itResults.next();
				RestNode restnode = (RestNode) entry.get("evento");
				Evento evento = new Evento(restnode);
				eventos.add(evento);
			}while (itResults.hasNext());
		}
			
		return eventos;
	}

	@Override
	public Evento buscarEventoPorId(String idEvento) {
		
		RestGraphDatabase gds = new RestGraphDatabase(Constantes.SERVER_ROOT_URI, Constantes.USERNAME,
				Constantes.PASSWORD);

		String query = "MATCH (evento:Evento{id:" + idEvento + "}) return evento";

		RestAPI restAPI = gds.getRestAPI();
		RestCypherQueryEngine queryEngine = new RestCypherQueryEngine(restAPI);
		queryEngine = new RestCypherQueryEngine(restAPI);

		final QueryResult qr = queryEngine.query(query, new HashMap<String, Object>());
		final Collection<Map<String, Object>> result = IteratorUtil.asCollection(qr);

		Iterator<Map<String, Object>> itResults = result.iterator();

		if (itResults.hasNext()) {

			Map<String, Object> entry = itResults.next();
			RestNode restnode = (RestNode) entry.get("evento");
			Evento evento = new Evento (restnode);
			return evento;
		}

		return null;
	}
	
}
