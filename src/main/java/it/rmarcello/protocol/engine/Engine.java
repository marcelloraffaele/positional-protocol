package it.rmarcello.protocol.engine;

import it.rmarcello.protocol.exception.ProtocolException;

/**
 *
 * @author rmarcello
 */
public interface Engine {

    public <T> T parseFrom(byte[] buffer, Class<T> c) throws ProtocolException;
    
    public <T> byte[] toByteArray(T t) throws ProtocolException;
    
}
