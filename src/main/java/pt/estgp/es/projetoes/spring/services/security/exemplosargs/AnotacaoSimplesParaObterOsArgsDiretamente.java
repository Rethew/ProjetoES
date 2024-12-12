package pt.estgp.es.projetoes.spring.services.security.exemplosargs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AnotacaoSimplesParaObterOsArgsDiretamente {

}
