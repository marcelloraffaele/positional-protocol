package it.rmarcello.protocol.engine;

/**
 *
 * @author rmarcello
 */
public class EngineFactory {

    public static Engine create() {
        return new EngineImpl();
    }
    
}
