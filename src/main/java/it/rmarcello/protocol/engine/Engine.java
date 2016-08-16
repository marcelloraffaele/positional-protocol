package it.rmarcello.protocol.engine;

import it.rmarcello.protocol.exception.ProtocolException;

/**
 *
 * @author rmarcello
 */
public interface Engine {

    public <T> T fromBytes(byte[] buffer, Class<T> c) throws ProtocolException;
    
    public <T> byte[] toBytes(T t) throws ProtocolException;
    
}
