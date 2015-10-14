package br.uaijug.tutorial.service.participante;

import br.uaijug.tutorial.rest.domain.DadosCertificado;
import br.uaijug.tutorial.rest.domain.Participante;

public interface ParticipanteService {
	
	public void inserirParticipante(Participante participante);

	public void realizarInscricaoEmEvento (String idParticipante, String idEvento);
	
	public void realizarCheckinEmPalestra (String idParticipante, String idPalestra);
	
	public Participante buscarParticipantePorId (String idParticipante);
	
	public Participante buscarParticipantePorCpf (String cpfParticipante);
	
	public DadosCertificado buscarDadosCertificado (String idParticipante, String idEvento);
}
