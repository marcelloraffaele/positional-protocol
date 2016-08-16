package it.rmarcello.protocol;

import it.rmarcello.protocol.bean.ExampleBean;
import it.rmarcello.protocol.engine.EngineImpl;
import it.rmarcello.protocol.exception.ProtocolException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rmarcello
 */
public class ProtocolTest {
//
//    @Test
//    public void testParsing() throws ProtocolException {
//        String type = "ABCD";
//        String version = "001";
//        String name = "Name01";
//        String description = "abcdefghil";
//        byte[] b = (type + version + name + description).getBytes();
//
//        ExampleBean bean = (ExampleBean) EngineImpl.fromBytes(b, ExampleBean.class);
//
//        System.out.println("-> " + bean);
//        Assert.assertNotNull(bean);
//        Assert.assertEquals(bean.getType(), type);
//        Assert.assertEquals(bean.getVersion(), new Integer(1));
//        Assert.assertEquals(bean.getName(), name);
//        Assert.assertEquals(bean.getDescription(), description);
//    }
//
//    @Test
//    public void testToByte() throws ProtocolException {
//        
//        String type = "ABCD";
//        String version = "001";
//        String name = "Name01";
//        String description = "abcdefghil";
//        byte[] b = (type + version + name + description).getBytes();
//        
//        ExampleBean bean = new ExampleBean();
//        bean.setType( type );
//        bean.setVersion( Integer.parseInt(version) );
//        bean.setName(name);
//        bean.setDescription(description);
//
//        byte[] res = EngineImpl.toBytes(bean);
//        
//        Assert.assertTrue(Arrays.equals(res, b));
//    }
//
//    @Test
//    public void testParsingAndToByte() throws ProtocolException {
//        String s = "ABCD"
//                + "001"
//                + "Name01"
//                + "abcdefghil";
//        byte[] b = s.getBytes();
//
//        System.out.println("buff= " + s);
//
//        ExampleBean bean = (ExampleBean) EngineImpl.fromBytes(b, ExampleBean.class);
//
//        System.out.println("-> " + bean);
//
//        byte[] res = EngineImpl.toBytes(bean);
//
//        String resStr = new String(res);
//
//        System.out.println("check = " + resStr);
//
//        Assert.assertTrue(Arrays.equals(res, b));
//    }

}
