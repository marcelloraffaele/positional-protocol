package it.rmarcello.protocol.exception;

/**
 *
 * @author rmarcello
 */
public class ProtocolException extends Exception{
    public ProtocolException() {
        super();
    }
    public ProtocolException(String s) {
        super(s);
    }
    public ProtocolException(String s, Exception e) {
        super(s,e);
    }
}
