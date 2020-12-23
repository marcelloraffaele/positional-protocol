package it.rmarcello.protocol.engine;

import it.rmarcello.protocol.exception.ProtocolException;

/**
 *
 * @author rmarcello
 */
public interface Engine {

    public <T> T fromByte(byte[] buffer, Class<T> c) throws ProtocolException;
    
    public <T> byte[] toByte(T t) throws ProtocolException;
    
}
