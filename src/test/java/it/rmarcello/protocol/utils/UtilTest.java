package it.rmarcello.protocol.utils;

import org.apache.commons.io.HexDump;
import org.apache.commons.io.output.ByteArrayOutputStream;

public class UtilTest {
    
    public static String hexDump(byte[] v) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            HexDump.dump(v, 0, out, 0);
            return new String(out.toByteArray());
        } catch(Exception e ) {
            return null;
        }
    }

}
