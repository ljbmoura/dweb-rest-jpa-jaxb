package ljbm.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * 
 * Classe para implementar servico rest sem usar o web.xml
 * 
 * ref: http://buraktas.com/resteasy-example-without-using-a-web-xml/
 * 
 * @author luc
 * 
 */
@ApplicationPath("/rest")
public class Aplicacao extends Application {

}
