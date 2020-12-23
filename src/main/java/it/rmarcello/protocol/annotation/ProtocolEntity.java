package it.rmarcello.protocol.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Inherited;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
/**
 *
 * @author rmarcello
 */
@Target(TYPE)
@Inherited
@Retention(RUNTIME)
public @interface ProtocolEntity {
    
}
