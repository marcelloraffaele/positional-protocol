package it.rmarcello.protocol.parsing;

import it.rmarcello.protocol.annotation.ProtocolField;

/**
 *
 * @author rmarcello
 */
public interface Parsing {

//    public byte[] getBytes
    
    public String parseString(byte[] b, int initPos, int len);
    
    public Integer parseInt(byte[] b, int initPos, int len);
    
}
