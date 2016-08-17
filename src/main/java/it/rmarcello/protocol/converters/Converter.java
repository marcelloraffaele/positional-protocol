package it.rmarcello.protocol.converters;

import it.rmarcello.protocol.annotation.ProtocolField;
import it.rmarcello.protocol.exception.ProtocolException;
import java.lang.reflect.Type;

/**
 *
 * @author rmarcello
 */
public interface Converter<F, T> {

    T convert(F value, ProtocolField protocolField) throws ProtocolException;

    abstract class Factory {

        public Converter<byte[], ?> byteToObjectConverter(Type type) throws ProtocolException {
            return null;
        }
        
        public Converter< ?,byte[] > objectToByteConverter(Type type) throws ProtocolException {
            return null;
        }
    }

}
