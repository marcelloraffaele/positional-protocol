package it.rmarcello.protocol.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import it.rmarcello.protocol.exception.ProtocolException;
import it.rmarcello.protocol.utils.BufferUtils;
import it.rmarcello.protocol.utils.UtilTest;

/**
 *
 * @author rmarcello
 */
public class EngineTest {

    private final static Logger LOGGER = LogManager.getLogger(EngineTest.class);

    @Test
    public void wrongTypeTest(){
        try {
            final Engine e = EngineFactory.create();
            
            assertThrows(ProtocolException.class, ()-> e.toByte( new String() ) );

            assertThrows(ProtocolException.class, ()-> e.fromByte( new byte[5] , String.class) );
            
        } catch (Exception e) {
            LOGGER.error( "error", e );
        }
    }

    @Test
    public void toBynaryTest(){
        Long val = 256L*256L*256L-1;

        byte[] v = BufferUtils.toBinary(val, 3);

        LOGGER.info( UtilTest.hexDump(v) );

        Long l = BufferUtils.binaryToLong(v, 0, v.length);

        LOGGER.info("l=" + l);

        assertEquals( val, l, "check conversions");
    }

}
