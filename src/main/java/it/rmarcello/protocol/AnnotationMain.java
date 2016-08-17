package it.rmarcello.protocol;

import it.rmarcello.protocol.bean.ExampleBody;
import it.rmarcello.protocol.engine.Engine;
import it.rmarcello.protocol.engine.EngineFactory;

/**
 *
 * @author rmarcello
 */
public class AnnotationMain {

    public static void main(String args[]) {
        try {
            Engine e = EngineFactory.create();
            
            String s = "MSG1"
                    + "001"
                    + "030"
                    + "Titolo    "
                    + "Testo del messaggio!"
                    + "012";
            
            ExampleBody bodyMsg = e.parseFrom(s.getBytes() , ExampleBody.class);
            System.out.println("bodyMsg = " + bodyMsg);
            
            bodyMsg.setTitle( bodyMsg.getTitle().trim() );
            
            byte[] v = e.toByteArray(bodyMsg);
            
            System.out.println("check = " + new String(v));
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
