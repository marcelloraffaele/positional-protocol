package it.rmarcello.protocol.engine;

import it.rmarcello.protocol.annotation.BufferIn;
import it.rmarcello.protocol.annotation.BufferOut;
import it.rmarcello.protocol.exception.ProtocolException;
import java.io.ByteArrayOutputStream;
import it.rmarcello.protocol.annotation.ProtocolField;
import it.rmarcello.protocol.parsing.Padding;
import it.rmarcello.protocol.utils.AnnotationUtils;
import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.util.List;

/**
 *
 * @author rmarcello
 */
public class EngineImpl implements Engine {

    public EngineImpl() {

    }

    /**
     * Parse from byte and make an Object of the desidered class.
     *
     * @param buffer
     * @param c
     * @return
     * @throws ProtocolException
     */
    @Override
    public <T> T fromBytes(byte[] buffer, Class<T> c) throws ProtocolException {
        try {

            //create the result class
            T obj = c.newInstance();

            //initialize the buffer out
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);

            //verify if the current Object has the @BufferIn annotation
            if (c.isAnnotationPresent(BufferIn.class)) {

                //get all the fields with @ProtocolField annotation
                List<Field> fieldsWithAnnotation = AnnotationUtils.getFieldsListWithAnnotation(c, ProtocolField.class);

                //for each field
                for ( Field reflectField : fieldsWithAnnotation ) {

                    ProtocolField protocolField = (ProtocolField) reflectField.getAnnotation(ProtocolField.class);
                    
                    byte[] fieldBytes = new byte[protocolField.size()];
                    bais.read(fieldBytes);

                    //create the arg
                    Object objArg = covertToObject(fieldBytes, reflectField.getType());
                    
//                    System.out.println("-> " + reflectField.getName() + ": " + reflectField.getType() + ": " + objArg );
                    
                    //write the field
                    AnnotationUtils.writeField(reflectField, obj, objArg);
                    
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
    @Override
    public <T> byte[] toBytes(T obj) throws ProtocolException {
        try {
            Class c = obj.getClass();

            //verify if the current Object has the @BufferOut annotation
            if ( c.isAnnotationPresent(BufferOut.class) ) {

                //initialize the buffer out
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                //get all the fields with @ProtocolField annotation
                List<Field> fieldsWithAnnotation = AnnotationUtils.getFieldsListWithAnnotation(c, ProtocolField.class);
                
                for ( Field reflectField : fieldsWithAnnotation ) {

                        ProtocolField protocolField = (ProtocolField) reflectField.getAnnotation(ProtocolField.class);

                        //read the field
                        Object value = AnnotationUtils.readField( reflectField, obj);
                        
//                        System.out.println(reflectField.getName() + " = " + value);

                        //convert the value in byte array
                        byte[] bytesToWrite = covertToBytes(protocolField, value);
                        
                        baos.write( bytesToWrite );

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

    public static byte[] covertToBytes(ProtocolField protocolField, Object value) throws ProtocolException {
        byte[] res = null;
        if (value instanceof String) {
            res = Padding.padLeft((String) value, protocolField.size()).getBytes();
        } else if (value instanceof Integer) {
            res = Padding.pad((Integer) value, protocolField.size()).getBytes();
        }
        
        else {
            throw new ProtocolException("Not recognized type: " + value.getClass().getCanonicalName() );
        }

        return res;
    }

    private static Object covertToObject(byte[] fieldBytes, Class<?> parameterType) throws ProtocolException {

        if (parameterType.getCanonicalName().equals(String.class.getCanonicalName())) {
            return new String(fieldBytes);
        } else if (parameterType.getCanonicalName().equals(Integer.class.getCanonicalName())) {
            return Integer.parseInt(new String(fieldBytes));
        }
        
        else {
            throw new ProtocolException("Not recognized type: " + parameterType.getCanonicalName() );
        }
    }

}
