package ljbm.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.GregorianCalendar;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.glassfish.jersey.jackson.JacksonFeature;

import ljbm.modelo.Modelo;

public class ModeloUtil {
	private static final Logger LOG = Logger.getLogger(ModeloUtil.class);
	
	private static final String REST_SERVICE_URL = "http://localhost:9080/lab/modelos/";
	private static final Long NUMERO = 4706467l;
	private static final String TITLE = "Tesouro Prefixado 2018 (LTN)";
	private static final String agente = "agora";
	private static final BigDecimal VALORUNITARIO = new BigDecimal("747.42");
	private static final GregorianCalendar REALIZACAO = new GregorianCalendar(2016, 1, 13);
	private static final GregorianCalendar LIQUIDACAO = new GregorianCalendar(2016, 1, 15);

	private static Modelo mockModelo() {

		Modelo modelo = new Modelo();
		modelo.setNumero(NUMERO);
		modelo.setTitulo(TITLE);
		modelo.setAgente(agente);
		modelo.setValorUnitario(VALORUNITARIO);
		modelo.setTaxaBMFBOVESPA(BigDecimal.ZERO);
		modelo.setTaxaCustodia(new BigDecimal("16.08"));
		modelo.setTaxaJurosPactuada(new BigDecimal("5.16"));
		modelo.setDataRealizacao(REALIZACAO);
		modelo.setDataLiquidacao(LIQUIDACAO);
		modelo.setQuantidade(3);
		return modelo;
	}
	
	public static void leModelo() throws IOException {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(REST_SERVICE_URL);
		Response response = target.request().get();
		String modelosLidos = response.readEntity(String.class);
		response.close(); 
		LOG.info(modelosLidos);
	}

	public static void incluiModelo(Long numero) throws IOException {
		Client client = ClientBuilder.newClient().register(JacksonFeature.class);
		Modelo modelo = mockModelo();
		modelo.setNumero(numero);
		Modelo modeloIncluido = client.target(REST_SERVICE_URL).request()
				.post(Entity.entity(modelo, MediaType.APPLICATION_JSON), Modelo.class);
		LOG.info(modeloIncluido.toString());
	}

	public static void excluiModelo(String idModelo) throws IOException {
		Client client = ClientBuilder.newClient().register(JacksonFeature.class);
		Response modeloExcluido = client.target(REST_SERVICE_URL+idModelo).request()
				.delete();
		LOG.info(modeloExcluido.toString());
	}
	
	public static void main(String[] args) throws IOException {
//		incluiModelo(4706471l);
		leModelo();
//		excluiModelo("4706471");
	}

}
