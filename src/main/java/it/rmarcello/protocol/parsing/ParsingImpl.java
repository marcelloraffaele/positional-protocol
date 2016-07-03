package it.rmarcello.protocol.parsing;

/**
 *
 * @author rmarcello
 */
public class ParsingImpl implements Parsing{

    private byte[] getBytes(byte[] b, int initPos, int len) {
        byte[] res = new byte[len];
        System.arraycopy(b, initPos, res, 0, len);
        return res;
    }
    
    @Override
    public String parseString(byte[] b, int initPos, int len) {
        return new String( getBytes(b, initPos, len) );
    }

    @Override
    public Integer parseInt(byte[] b, int initPos, int len) {
        return Integer.parseInt( parseString(b, initPos, len) );
    }
    
}
