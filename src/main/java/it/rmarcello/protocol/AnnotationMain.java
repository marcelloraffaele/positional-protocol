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
//            
//            String s = "ABCD"
//                    + "001"
//                    + "Name01"
//                    + "abcdefghil";
//            byte[] b = s.getBytes();
//
//            System.out.println("buff= " + s);
//
//            ExampleBean bean = e.fromBytes( b, ExampleBean.class );
//
//            System.out.println("-> " + bean);
//
//
//            byte[] res = e.toBytes(bean);
//
//            String resStr = new String(res);
//            
//            System.out.println("check = " + resStr);
//            
//            System.out.println(" OK =  " + Arrays.equals(res, b) );
            
            String s = "MSG1"
                    + "001"
                    + "030"
                    + "Titolo    "
                    + "Testo del messaggio!";
            
            ExampleBody bodyMsg = e.fromBytes( s.getBytes() , ExampleBody.class);
            System.out.println("bodyMsg = " + bodyMsg);
            
            byte[] v = e.toBytes(bodyMsg);
            
            System.out.println("check = " + new String(v));
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
