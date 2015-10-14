package br.uaijug.tutorial.service.palestra;

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
import br.uaijug.tutorial.rest.domain.Palestra;
import br.uaijug.tutorial.utils.Constantes;

@Service
public class PalestrasServiceImpl implements PalestraService {

	
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Palestra> buscarPalestras(String idEvento){
		
		RestGraphDatabase gds = new RestGraphDatabase(Constantes.SERVER_ROOT_URI, Constantes.USERNAME, Constantes.PASSWORD);
		
		StringBuilder query = new StringBuilder(); 
		query.append("MATCH (palestra:Palestra) -[r:PERTENCE_A]-> (evento:Evento)");
		query.append("where evento.id = "+idEvento+" return DISTINCT palestra");
		
		RestAPI restAPI = gds.getRestAPI();
		RestCypherQueryEngine queryEngine = new RestCypherQueryEngine(restAPI);
		queryEngine = new RestCypherQueryEngine(restAPI);

		final QueryResult qr = queryEngine.query(query.toString(), new HashMap<String, Object>());
		final Collection<Map<String,Object>> result = IteratorUtil.asCollection(qr);
		
		Iterator<Map<String, Object>> itResults = result.iterator();
		List<Palestra> palestras = new ArrayList<>();
		
		if (itResults.hasNext()) {
			do {
				Map<String,Object> entry = itResults.next();
				RestNode restnode = (RestNode) entry.get("palestra");
				Palestra palestra = new Palestra(restnode);
				palestras.add(palestra);
			}while (itResults.hasNext());
		}
			
		return palestras;
	}

	@Override
	public List<String> buscarDatasPalestrasPorEventos(String idEvento) {
		
		RestGraphDatabase gds = new RestGraphDatabase(Constantes.SERVER_ROOT_URI, Constantes.USERNAME, Constantes.PASSWORD);
		
		StringBuilder query = new StringBuilder(); 
		query.append("Match (palestra:Palestra) -[r]->(evento:Evento{id:"+idEvento+"})");
		query.append("return distinct palestra.data as data");
		
		RestAPI restAPI = gds.getRestAPI();
		RestCypherQueryEngine queryEngine = new RestCypherQueryEngine(restAPI);
		queryEngine = new RestCypherQueryEngine(restAPI);

		final QueryResult qr = queryEngine.query(query.toString(), new HashMap<String, Object>());
		final Collection<Map<String,Object>> result = IteratorUtil.asCollection(qr);
		
		Iterator<Map<String, Object>> itResults = result.iterator();
		List<String> datas = new ArrayList<>();
		
		if (itResults.hasNext()) {
			do {
				Map<String,Object> entry = itResults.next();
				String data = (String) entry.get("data");
				datas.add(data);
			}while (itResults.hasNext());
		}
			
		return datas;
	}
	
}
