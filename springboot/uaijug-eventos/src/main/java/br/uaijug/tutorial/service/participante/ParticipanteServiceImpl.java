package br.uaijug.tutorial.service.participante;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.helpers.collection.IteratorUtil;
import org.neo4j.rest.graphdb.RestAPI;
import org.neo4j.rest.graphdb.RestGraphDatabase;
import org.neo4j.rest.graphdb.entity.RestNode;
import org.neo4j.rest.graphdb.query.RestCypherQueryEngine;
import org.neo4j.rest.graphdb.util.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uaijug.tutorial.rest.domain.DadosCertificado;
import br.uaijug.tutorial.rest.domain.Evento;
import br.uaijug.tutorial.rest.domain.Participante;
import br.uaijug.tutorial.service.evento.EventoService;
import br.uaijug.tutorial.service.palestra.PalestraService;
import br.uaijug.tutorial.service.util.UtilService;
import br.uaijug.tutorial.utils.Constantes;

@Service
public class ParticipanteServiceImpl implements ParticipanteService {

	@Autowired
	UtilService utilService;
	
	@Autowired
	EventoService eventoService;
	
	@Autowired
	PalestraService palestraService;
	

	@Override
	public void inserirParticipante(Participante participante) {

		RestGraphDatabase gds = new RestGraphDatabase(Constantes.SERVER_ROOT_URI, Constantes.USERNAME,
				Constantes.PASSWORD);

		Transaction t = gds.beginTx();

		Node node = gds.createNode();
		participante.setId(utilService.buscarProximoId(Participante.class).toString());

		node.setProperty("id", participante.getId());
		node.setProperty("nome", participante.getNome());
		node.setProperty("email", participante.getEmail());
		node.setProperty("cpf", participante.getCpf());
		node.setProperty("telefone", participante.getTelefone());

		t.finish();

		utilService.setLabelParticipante(participante.getId(), Participante.class);
	}

	@Override
	public void realizarInscricaoEmEvento(String idParticipante, String idEvento) {
		RestGraphDatabase gds = new RestGraphDatabase(Constantes.SERVER_ROOT_URI, Constantes.USERNAME,
				Constantes.PASSWORD);

		StringBuilder query = new StringBuilder("MATCH (a:Participante),(b:Evento)");
		query.append("WHERE a.id = " + idParticipante + " AND b.id = " + idEvento + "");
		query.append("CREATE (a)-[r:INSCRITO]->(b)");
		query.append("RETURN r");

		Transaction t = gds.beginTx();

		RestAPI restAPI = gds.getRestAPI();
		RestCypherQueryEngine queryEngine = new RestCypherQueryEngine(restAPI);
		queryEngine = new RestCypherQueryEngine(restAPI);

		queryEngine.query(query.toString(), new HashMap<String, Object>());

		t.finish();
	}

	@Override
	public void realizarCheckinEmPalestra(String idParticipante, String idPalestra) {

		RestGraphDatabase gds = new RestGraphDatabase(Constantes.SERVER_ROOT_URI, Constantes.USERNAME,
				Constantes.PASSWORD);

		StringBuilder query = new StringBuilder("MATCH (a:Participante),(b:Palestra)");
		query.append("WHERE a.id = " + idParticipante + " AND b.id = " + idPalestra + "");
		query.append("CREATE (a)-[r:INSCRITO_PALESTRA]->(b)");
		query.append("RETURN r");

		Transaction t = gds.beginTx();

		RestAPI restAPI = gds.getRestAPI();
		RestCypherQueryEngine queryEngine = new RestCypherQueryEngine(restAPI);
		queryEngine = new RestCypherQueryEngine(restAPI);

		queryEngine.query(query.toString(), new HashMap<String, Object>());

		t.finish();
	}

	@Override
	public DadosCertificado buscarDadosCertificado(String idParticipante, String idEvento) {

		Participante participante = buscarParticipantePorId(idParticipante);
		Evento evento = eventoService.buscarEventoPorId(idEvento);
		
		DadosCertificado dadosCertificado = new DadosCertificado(
				participante.getNome(), 
				evento.getNome(), 
				evento.getData(),
				evento.getLocal(), 
				evento.getTotalHoras(), 
				evento.getCidade());
		
		return dadosCertificado;
	}

	@Override
	public Participante buscarParticipantePorId(String idParticipante) {

		RestGraphDatabase gds = new RestGraphDatabase(Constantes.SERVER_ROOT_URI, Constantes.USERNAME,
				Constantes.PASSWORD);

		String query = "MATCH (participante:Participante{id:" + idParticipante + "}) return participante";

		RestAPI restAPI = gds.getRestAPI();
		RestCypherQueryEngine queryEngine = new RestCypherQueryEngine(restAPI);
		queryEngine = new RestCypherQueryEngine(restAPI);

		final QueryResult qr = queryEngine.query(query, new HashMap<String, Object>());
		final Collection<Map<String, Object>> result = IteratorUtil.asCollection(qr);

		Iterator<Map<String, Object>> itResults = result.iterator();

		if (itResults.hasNext()) {

			Map<String, Object> entry = itResults.next();
			RestNode restnode = (RestNode) entry.get("participante");
			Participante participante = new Participante(restnode);
			return participante;
		}

		return null;

	}

	@Override
	public Participante buscarParticipantePorCpf(String cpfParticipante) {

		RestGraphDatabase gds = new RestGraphDatabase(Constantes.SERVER_ROOT_URI, Constantes.USERNAME,
				Constantes.PASSWORD);

		String query = "MATCH (participante:Participante{cpf:'" + cpfParticipante + "'}) return participante";

		RestAPI restAPI = gds.getRestAPI();
		RestCypherQueryEngine queryEngine = new RestCypherQueryEngine(restAPI);
		queryEngine = new RestCypherQueryEngine(restAPI);

		final QueryResult qr = queryEngine.query(query, new HashMap<String, Object>());
		final Collection<Map<String, Object>> result = IteratorUtil.asCollection(qr);

		Iterator<Map<String, Object>> itResults = result.iterator();

		if (itResults.hasNext()) {

			Map<String, Object> entry = itResults.next();
			RestNode restnode = (RestNode) entry.get("participante");
			Participante participante = new Participante(restnode);
			return participante;
		}

		return null;
	}

}
