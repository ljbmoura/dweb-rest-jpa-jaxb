package ljbm.util;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.glassfish.jersey.jackson.JacksonFeature;

import ljbm.modelo.TituloTesouroDireto;

public class TituloTesouroDiretoUtil {
	private static final Logger LOG = Logger.getLogger(TituloTesouroDiretoUtil.class);
	
	private static final String REST_SERVICE_URL = "http://localhost:9080/lab/titulosTD/";
//	private static final Long NUMERO = 4706467l;
	private static final String NOME = "Tesouro Prefixado 2018 (LTN)";
//	private static final BigDecimal VALORUNITARIO = new BigDecimal("747.42");
//	private static final GregorianCalendar REALIZACAO = new GregorianCalendar(2016, 1, 13);
//	private static final GregorianCalendar LIQUIDACAO = new GregorianCalendar(2016, 1, 15);

	private static TituloTesouroDireto mockTituloTesouroDireto() {

		TituloTesouroDireto objeto = new TituloTesouroDireto();
		objeto.setId(1l);
		objeto.setNome(NOME);
//		objeto.setTipo(TituloTesouroDireto.Tipo.IndexadoIPCA);
//		objeto.setValorUnitario(VALORUNITARIO);
//		objeto.setTaxaBMFBOVESPA(BigDecimal.ZERO);
//		objeto.setTaxaCustodia(new BigDecimal("16.08"));
//		objeto.setTaxaJurosPactuada(new BigDecimal("5.16"));
//		objeto.setDataRealizacao(REALIZACAO);
//		objeto.setDataLiquidacao(LIQUIDACAO);
//		objeto.setQuantidade(3);
		return objeto;
	}
	
	public static void leTituloTesouroDireto() throws IOException {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(REST_SERVICE_URL);
		Response response = target.request().get();
		String objetosLidos = response.readEntity(String.class);
		response.close(); 
		LOG.info(objetosLidos);
	}

	public static void incluiTituloTesouroDireto(long l, String nome, 
//			TituloTesouroDireto.Tipo tipo
			String tipo
			) throws IOException {
		Client client = ClientBuilder.newClient().register(JacksonFeature.class);
		TituloTesouroDireto objeto = mockTituloTesouroDireto();
		objeto.setId(l);
		objeto.setNome(nome);
		objeto.setTipo(tipo);
		TituloTesouroDireto objetoIncluido = client.target(REST_SERVICE_URL).request()
				.post(Entity.entity(objeto, MediaType.APPLICATION_JSON), TituloTesouroDireto.class);
		LOG.info(objetoIncluido.toString());
	}

	public static void excluiTituloTesouroDireto(String idTituloTesouroDireto) throws IOException {
		Client client = ClientBuilder.newClient().register(JacksonFeature.class);
		Response objetoExcluido = client.target(REST_SERVICE_URL+idTituloTesouroDireto).request()
				.delete();
		LOG.info(objetoExcluido.toString());
	}
	
	public static void main(String[] args) throws IOException {
//		incluiTituloTesouroDireto(2l, "Tesouro IPCA+ 2019 (NTNB Princ)", "1");
		//excluiTituloTesouroDireto("2");
		leTituloTesouroDireto();
	}

}
