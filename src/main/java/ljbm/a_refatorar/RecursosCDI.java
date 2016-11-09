package ljbm.a_refatorar;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ApplicationScoped
public class RecursosCDI {

    @Produces
    Logger produceLog(InjectionPoint injectionPoint) {
        return LogManager.getFormatterLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
}