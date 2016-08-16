package it.rmarcello.protocol.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

/**
 *
 * @author rmarcello
 */
@Target( ElementType.FIELD )
@Retention( RUNTIME )
public @interface ProtocolField {
    
    int size();
    
    FillerType filler() default FillerType.AUTO;
    
    NumericEncoding numericEncoding() default NumericEncoding.AUTO;
    
}
