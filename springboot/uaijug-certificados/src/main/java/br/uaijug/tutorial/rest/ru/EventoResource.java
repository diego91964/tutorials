package br.uaijug.tutorial.rest.ru;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.uaijug.tutorial.rest.domain.DadosCertificado;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@RestController
@RequestMapping("/certificado")
public class EventoResource {

	@RequestMapping(value = "/gerarCertificado/{idParticipante}/{idEvento}", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
	@RolesAllowed({ "ADMIN" })
	public HttpEntity<String> gerarCertificado(@PathVariable("idParticipante") String idParticipante,
			@PathVariable("idEvento") String idEvento) throws Exception {

		String sourceFileName = "src/main/resources/CERTIFICADO_UAIJUG.jasper";
		String printFileName = null;

		try {

			File jasperFile = new File(sourceFileName);

			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperFile);

			Map<String, Object> parameters = new HashMap<String, Object>();
			
			final String url = "http://localhost:8080/participante/buscarDadosCertificado/1/1";
			
			
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

			ResponseEntity<DadosCertificado> dadosCertificadoEntity = restTemplate.exchange(url, HttpMethod.GET, entity, DadosCertificado.class);
			
			DadosCertificado dadosCertificado = dadosCertificadoEntity.getBody();
			
			
			/*
			 * DadosCertificado dadosCertificado = new DadosCertificado();
			 * dadosCertificado.setNomeParticipante("Diego Alves da Silva");
			 * dadosCertificado.setNomeEvento("Tech Week");
			 * dadosCertificado.setDiasEvento("13, 14, 15 de Outubro");
			 * dadosCertificado.setNomeInstituicao(
			 * "Universidade Federal de Uberlândia");
			 * dadosCertificado.setTotalHoras("60");
			 * dadosCertificado.setNomeCidade("Uberlândia");
			 */

			Collection<DadosCertificado> dadosPessoas = new ArrayList<DadosCertificado>();

			dadosPessoas.add(dadosCertificado);

			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dadosPessoas);

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

			/*
			 * byte[] output;
			 * 
			 * ByteArrayOutputStream baos = new ByteArrayOutputStream();
			 * 
			 * JRPdfExporter exporter = new JRPdfExporter();
			 * 
			 * SimpleExporterInput simpleExporterInput = new
			 * SimpleExporterInput(jasperPrint);
			 * exporter.setExporterInput(simpleExporterInput);
			 * 
			 * SimpleOutputStreamExporterOutput simpleOutputStreamExporterOutput
			 * = new SimpleOutputStreamExporterOutput(baos);
			 * exporter.setExporterOutput(simpleOutputStreamExporterOutput);
			 * 
			 * exporter.exportReport();
			 * 
			 * output = baos.toByteArray();
			 */

			JRPdfExporter exporter = new JRPdfExporter();

			File file = new File("target/CERTIFICADO_UAIJUG.pdf");

			FileOutputStream fos = new FileOutputStream(file);

			SimpleExporterInput simpleExporterInput = new SimpleExporterInput(jasperPrint);

			exporter.setExporterInput(simpleExporterInput);

			SimpleOutputStreamExporterOutput simpleOutputStreamExporterOutput = new SimpleOutputStreamExporterOutput(
					fos);
			exporter.setExporterOutput(simpleOutputStreamExporterOutput);

			exporter.exportReport();

			String json = new ObjectMapper().writeValueAsString("SUCESSO");
			return new ResponseEntity<String>(json, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
