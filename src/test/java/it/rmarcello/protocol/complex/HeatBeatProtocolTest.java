package it.rmarcello.protocol.complex;

import it.rmarcello.protocol.engine.Engine;
import it.rmarcello.protocol.engine.EngineFactory;
import it.rmarcello.protocol.exception.ProtocolException;
import it.rmarcello.protocol.utils.UtilByteArrayInputStream;
import it.rmarcello.protocol.utils.UtilTest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

/**
 *
 * @author rmarcello
 */
public class HeatBeatProtocolTest {

    private final static Logger LOGGER = LogManager.getLogger(HeatBeatProtocolTest.class);

    @Test
    public void fromObjectToByteToObjectTest() throws ProtocolException, IOException {
        Engine engine = EngineFactory.create();

        HeartBeatProtocol heartBeat = new HeartBeatProtocol();
        heartBeat.setVersion("01");
        heartBeat.setSequenceNumber(1);
        heartBeat.setLength( 23 );
        heartBeat.setTimestamp( System.currentTimeMillis() );
        heartBeat.setCrc( new byte[]{0x01,0x02,0x03} );

        byte[] heartBeatByte = engine.toByte(heartBeat);

        LOGGER.info( UtilTest.hexDump(heartBeatByte) );
        
        
        UtilByteArrayInputStream bais = new UtilByteArrayInputStream(heartBeatByte);
        assertEquals( heartBeat.getVersion(), bais.getNextString(2), "check version" );
        assertEquals( heartBeat.getSequenceNumber(), bais.getNextBynaryInt(5) , "check sequence number" );
        assertEquals( heartBeat.getLength(), bais.getNextBynaryInt(3), "check length" );
        assertEquals( heartBeat.getTimestamp(), bais.getNextBynaryLong(10), "check timestamp" );
        assertTrue( Arrays.equals( heartBeat.getCrc(), bais.getNextByteArray(3)), "check crc" );

        //and back
        HeartBeatProtocol heartBeatParsed = engine.fromByte(heartBeatByte, HeartBeatProtocol.class);
        assertEquals( heartBeat.getVersion(), heartBeatParsed.getVersion(), "check version" );
        assertEquals( heartBeat.getSequenceNumber(), heartBeatParsed.getSequenceNumber() , "check sequence number" );
        assertEquals( heartBeat.getLength(), heartBeatParsed.getLength(), "check length" );
        assertEquals( heartBeat.getTimestamp(), heartBeatParsed.getTimestamp(), "check timestamp" );
        assertTrue( Arrays.equals( heartBeat.getCrc(), heartBeatParsed.getCrc()), "check crc" );

    }
    

}
