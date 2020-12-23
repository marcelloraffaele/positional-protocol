package it.rmarcello.protocol.simple;

import it.rmarcello.protocol.engine.Engine;
import it.rmarcello.protocol.engine.EngineFactory;
import it.rmarcello.protocol.exception.ProtocolException;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

/**
 *
 * @author rmarcello
 */
public class SimpleProtocolTest {

    private final static Logger LOGGER = LogManager.getLogger(SimpleProtocolTest.class);
    
    @Test
    public void fromBytetoObjectTest() throws ProtocolException {
        
        List<String> tokens = Arrays.asList(
            "01",
            "Michael Shumacher                                                                                   ",
            "                                                                                                                                           Hi How are you?"
        );
        
        byte[] sourceBuffer = tokens.stream().collect(Collectors.joining()).getBytes();     
        
        Engine engine = EngineFactory.create();
        SimpleProtocol simpleProtocol = engine.fromByte(sourceBuffer, SimpleProtocol.class);     
        
        assertEquals( tokens.get(0) , simpleProtocol.getType() );
        assertEquals( tokens.get(1), simpleProtocol.getSender() );
        assertEquals( tokens.get(2) , simpleProtocol.getMessage() );
    }
    
    @Test
    public void fromObjectToByteTest() throws ProtocolException {
        
        SimpleProtocol original = new SimpleProtocol();
        original.setType("T1");
        original.setSender("Mika Hakkinen");
        original.setMessage("Hey how are you?");
        
        
        Engine engine = EngineFactory.create();
        byte[] originalByte = engine.toByte(original);
        
        assertEquals( originalByte.length, 256, "check array byte length" );
        
        String stringRepresentation = new String(originalByte);
        
        assertEquals( stringRepresentation.substring(0, 2) , original.getType(), "check type" );
        assertEquals( stringRepresentation.substring(2, 102).trim(), original.getSender(), "check sender" );
        assertEquals( stringRepresentation.substring(102, 256).trim() , original.getMessage(), "check message" );
    }

    @Test
    public void fromObjectToByteToObjectTest() throws ProtocolException {
        
        SimpleProtocol original = new SimpleProtocol();
        original.setType("T1");
        original.setSender("Mika Hakkinen");
        original.setMessage("Hey how are you?");
        
        
        byte[] originalByte = EngineFactory.create().toByte(original);
        
        assertEquals( originalByte.length, 256, "check array byte length" );
        
        SimpleProtocol parsed = EngineFactory.create().fromByte(originalByte, SimpleProtocol.class);
        
        assertEquals( parsed.getType().trim() , original.getType(), "check type" );
        assertEquals( parsed.getSender().trim(), original.getSender(), "check sender" );
        assertEquals( parsed.getMessage().trim() , original.getMessage(), "check message" );
    }

}
