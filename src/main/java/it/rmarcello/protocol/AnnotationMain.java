package it.rmarcello.protocol;

import it.rmarcello.protocol.bean.ExampleBean;
import it.rmarcello.protocol.engine.Engine;
import java.util.Arrays;

/**
 *
 * @author rmarcello
 */
public class AnnotationMain {

    public static void main(String args[]) {
        try {
            String s = "ABCD"
                    + "001"
                    + "Name01"
                    + "abcdefghil";
            byte[] b = s.getBytes();

            System.out.println("buff= " + s);
            
            
            ExampleBean bean = (ExampleBean) Engine.fromBytes(b, ExampleBean.class);

            System.out.println("-> " + bean);


            byte[] res = Engine.toBytes(bean);

            String resStr = new String(res);
            
            System.out.println("check = " + resStr);
            
            System.out.println(" OK =  " + Arrays.equals(res, b) );
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
