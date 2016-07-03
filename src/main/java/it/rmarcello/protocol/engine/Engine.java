package it.rmarcello.protocol.engine;

import it.rmarcello.protocol.annotation.BufferIn;
import it.rmarcello.protocol.annotation.BufferOut;
import it.rmarcello.protocol.exception.ProtocolException;
import java.io.ByteArrayOutputStream;
import it.rmarcello.protocol.annotation.ProtocolField;
import it.rmarcello.protocol.parsing.Padding;
import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;

/**
 *
 * @author rmarcello
 */
public class Engine {

    /**
     * Parse from byte and make an Object of the desidered class.
     * 
     * @param buffer
     * @param cl
     * @return
     * @throws ProtocolException 
     */
    public static Object fromBytes(byte[] buffer, Class cl) throws ProtocolException {
        try {
            Object obj = cl.newInstance();  //I create a new object of that class

            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            
            if (cl.isAnnotationPresent(BufferIn.class)) {

//                BufferIn bufferIn = (BufferIn) obj.getAnnotation(BufferIn.class);
                for (java.lang.reflect.Field reflectField : cl.getDeclaredFields()) {

                    if (reflectField.isAnnotationPresent(ProtocolField.class)) {
                        
                        ProtocolField protocolField = (ProtocolField) reflectField.getAnnotation(ProtocolField.class);
                        byte[] fieldBytes = new byte[protocolField.size()];
                        bais.read(fieldBytes);
                        
                        //invoco il metodo set
                        String methodName = createSetMethod(reflectField.getName());
                        Method method = cl.getMethod(methodName, reflectField.getType() );

                        method.invoke(obj, covertToObject(fieldBytes, method.getParameterTypes()[0]) );
                        
                    }
                }

            } else {
                throw new ProtocolException("The object isn't a BufferIn");
            }

            return obj;
        } catch (Exception e) {
            throw new ProtocolException("Error", e);
        }
    }

    /**
     * Transforms the BufferIn Object in byte array.
     *
     * @param obj
     * @return
     * @throws ProtocolException
     */
    public static byte[] toBytes(Object obj) throws ProtocolException {
        try {
            Class cl = obj.getClass();

            if (cl.isAnnotationPresent(BufferOut.class)) {

                ByteArrayOutputStream baos = new ByteArrayOutputStream();

//            BufferOut bufferOut = (BufferOut) cl.getAnnotation(BufferOut.class);
                for (java.lang.reflect.Field reflectField : cl.getDeclaredFields()) {

                    if (reflectField.isAnnotationPresent(ProtocolField.class)) {

                        ProtocolField protocolField = (ProtocolField) reflectField.getAnnotation(ProtocolField.class);

                        Class<?> fieldType = reflectField.getType();
//                        System.out.printf("%s: %s\n", reflectField.getName(), fieldType);

                        String methodName = createGetMethod(reflectField.getName());
                        Method method = cl.getMethod(methodName);

                        Object value = method.invoke(obj);

//                        System.out.println(reflectField.getName() + " = " + value);

                        baos.write(covertToBytes(protocolField, value));

                    }

                }

                byte[] res = baos.toByteArray();
                return res;
            } else {
                throw new ProtocolException("The object isn't a BufferOut");
            }
        } catch (Exception e) {
            throw new ProtocolException("Error", e);
        }

    }

    public static byte[] covertToBytes(ProtocolField protocolField, Object value) {
        byte[] res = null;
        if (value instanceof String) {
            res = Padding.padLeft((String) value, protocolField.size()).getBytes();
        } else if (value instanceof Integer) {
            res = Padding.pad((Integer) value, protocolField.size()).getBytes();
        }

        return res;
    }

    private static String createGetMethod(String fieldName) {
        return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }
    
    private static String createSetMethod(String fieldName) {
        return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    private static Object covertToObject(byte[] fieldBytes, Class<?> parameterType) {
        
        if( parameterType.getCanonicalName().equals( String.class.getCanonicalName() ) ) {
            return new String(fieldBytes);
        } else if( parameterType.getCanonicalName().equals( Integer.class.getCanonicalName() ) ) {
            return Integer.parseInt( new String(fieldBytes) );
        }
        return null;
    }
}
