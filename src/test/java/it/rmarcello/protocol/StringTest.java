package it.rmarcello.protocol;

import it.rmarcello.protocol.annotation.BufferIn;
import it.rmarcello.protocol.annotation.BufferOut;
import it.rmarcello.protocol.annotation.FillerType;
import it.rmarcello.protocol.annotation.ProtocolField;
import it.rmarcello.protocol.bean.ExampleBean;
import it.rmarcello.protocol.engine.EngineFactory;
import it.rmarcello.protocol.engine.EngineImpl;
import it.rmarcello.protocol.exception.ProtocolException;
import it.rmarcello.protocol.utils.BufferUtils;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rmarcello
 */
public class StringTest {

    
    
    @Test
    public void test1() throws ProtocolException {
        
        byte[] buffer0 = "Marco           Rossi                   Roma".getBytes();
        
        StringBean bean = new StringBean();
        bean.setName("Marco           ");
        bean.setSurname( "Rossi           " ) ;
        bean.setCity("        Roma");
        
        System.out.println("-> " + bean);
        
        byte[] buffer1 = EngineFactory.create().toByteArray(bean);
        
        System.out.println("buffer1=" + BufferUtils.byteArrayToHex(buffer1) );
        

        Assert.assertTrue( Arrays.equals(buffer0, buffer1) );
        
        //parsing
        StringBean bean2 = EngineFactory.create().parseFrom(buffer1, StringBean.class );
        
        Assert.assertEquals( bean.getName() , bean2.getName() );
        Assert.assertEquals( bean.getSurname(), bean2.getSurname() );
        Assert.assertEquals( bean.getCity() , bean2.getCity() );
    }

    
    @BufferIn
    @BufferOut
    public static class StringBean {
        
        @ProtocolField(size=16)
        private String name;
        
        @ProtocolField(size=16)
        private String surname;
        
        @ProtocolField(size=12, filler = FillerType.RIGHT)
        private String city;

        public StringBean() {
        }

        
        
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        @Override
        public String toString() {
            return "StringBean{" + "name=" + name + ", surname=" + surname + ", city=" + city + '}';
        }
        
    }

}
