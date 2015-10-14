package br.uaijug.tutorial.service.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.neo4j.graphdb.Transaction;
import org.neo4j.helpers.collection.IteratorUtil;
import org.neo4j.rest.graphdb.RestAPI;
import org.neo4j.rest.graphdb.RestGraphDatabase;
import org.neo4j.rest.graphdb.query.RestCypherQueryEngine;
import org.neo4j.rest.graphdb.util.QueryResult;
import org.springframework.stereotype.Service;

import br.uaijug.tutorial.utils.Constantes;

@Service
public class UtilServiceImpl implements UtilService {


	@Override
	public Integer buscarProximoId(Class<?> entity) {
		
		RestGraphDatabase gds = new RestGraphDatabase(Constantes.SERVER_ROOT_URI, Constantes.USERNAME, Constantes.PASSWORD);

		String query = "MATCH (n:"+entity.getSimpleName()+")RETURN max(n.id) as maxid";
		
		RestAPI restAPI = gds.getRestAPI();
		RestCypherQueryEngine queryEngine = new RestCypherQueryEngine(restAPI);
		queryEngine = new RestCypherQueryEngine(restAPI);

		final QueryResult qr = queryEngine.query(query, new HashMap<String, Object>());
		final Collection<Map<String,Object>> result = IteratorUtil.asCollection(qr);
		Map<String,Object> firstEntry = result.iterator().next();
		
		Integer maxid = (Integer) firstEntry.get("maxid");
		
		if (maxid != null) return maxid++;
		else return 0;
		
	}
	
	public void setLabelParticipante (String id , Class<?> entity) {
		
		RestGraphDatabase gds = new RestGraphDatabase(Constantes.SERVER_ROOT_URI, Constantes.USERNAME, Constantes.PASSWORD);

		String query = "MATCH (n { id: "+id+" }) SET n :"+entity.getSimpleName()+" RETURN n";
		
		Transaction t = gds.beginTx(); 
		
		RestAPI restAPI = gds.getRestAPI();
		RestCypherQueryEngine queryEngine = new RestCypherQueryEngine(restAPI);
		queryEngine = new RestCypherQueryEngine(restAPI);

		queryEngine.	query(query, new HashMap<String, Object>());
		
		t.finish();
	}
	
}
