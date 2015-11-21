package br.uaijug.tutorial.rest.ru;

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

import br.uaijug.tutorial.rest.domain.DadosCertificado;
import br.uaijug.tutorial.rest.domain.Participante;
import br.uaijug.tutorial.service.participante.ParticipanteService;

@RestController
@RequestMapping("/participante")
public class ParticipanteResource {

	@Autowired
	ParticipanteService participanteService;
	
	@RequestMapping(value = "/adicionarParticipante/{cpf}/{email}/{telefone}/{nome}", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
	@RolesAllowed({ "ADMIN" })
	public HttpEntity<String> buscarPercentualLotacaoEstimadaPorRestaurante(
			@PathVariable("cpf") String cpf,
			@PathVariable("email") String email,
			@PathVariable("telefone") String telefone,
			@PathVariable("nome") String nome) throws Exception {
		
		Participante participante = new Participante(nome, email, cpf, telefone);
		
		try{ 
			participanteService.inserirParticipante(participante);
			return new ResponseEntity<String>("Usuario adicionado".toString(), HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Não foi possivel inserir usuario".toString(), HttpStatus.OK);
		}
		
		
	}

	@RequestMapping(value = "/buscarParticipantePorId/{id}", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
	@RolesAllowed({ "ADMIN" })
	public HttpEntity<String> buscarParticipantePorId(
			@PathVariable("id") String id) throws Exception {
		
		Participante participante = participanteService.buscarParticipantePorId(id);
		
		String json = new ObjectMapper().writeValueAsString(participante);
		return new ResponseEntity<String>(json, HttpStatus.OK);
		
		
	}
	
	@RequestMapping(value = "/buscarParticipantePorCpf/{cpf}", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
	@RolesAllowed({ "ADMIN" })
	public HttpEntity<String> buscarParticipantePorCpf(
			@PathVariable("cpf") String cpf) throws Exception {
		
		Participante participante = participanteService.buscarParticipantePorCpf(cpf);
		
		String json = new ObjectMapper().writeValueAsString(participante);
		return new ResponseEntity<String>(json, HttpStatus.OK);
		
		
	}
	
	@RequestMapping(value = "/buscarDadosCertificado/{idParticipante}/{idEvento}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@RolesAllowed({ "ADMIN" })
	public HttpEntity<String> buscarDadosCertificado(
			@PathVariable("idParticipante") String idParticipante,
			@PathVariable("idEvento") String idEvento
			) throws Exception {
		
		DadosCertificado dadosCertificado = participanteService.buscarDadosCertificado(idParticipante, idEvento);
		
		String json = new ObjectMapper().writeValueAsString(dadosCertificado);
		return new ResponseEntity<String>(json, HttpStatus.OK);
		
		
	}
}
