package it.rmarcello.protocol.engine;

import it.rmarcello.protocol.annotation.ProtocolEntity;
import it.rmarcello.protocol.exception.ProtocolException;
import java.io.ByteArrayOutputStream;
import it.rmarcello.protocol.annotation.ProtocolField;
import it.rmarcello.protocol.converters.Converter;
import it.rmarcello.protocol.converters.ScalarsConverterFactory;
import it.rmarcello.protocol.utils.BufferUtils;
import it.rmarcello.protocol.utils.AnnotationUtils;
import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * @author rmarcello
 */
public class EngineImpl implements Engine {

    protected EngineImpl() {
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
    public <T> T fromByte(byte[] buffer, Class<T> c) throws ProtocolException {
        try {

            //create the result class
            T obj = c.getDeclaredConstructor().newInstance();

            //initialize the buffer out
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);

            //verify if the current Object has the @ProtocolEntity annotation
            if (c.isAnnotationPresent(ProtocolEntity.class)) {

                //get all the fields with @ProtocolField annotation
                List<Field> fieldsWithAnnotation = AnnotationUtils.getFieldsListWithAnnotation(c, ProtocolField.class);

                //for each field
                for ( Field reflectField : fieldsWithAnnotation ) {

                    ProtocolField protocolField = (ProtocolField) reflectField.getAnnotation(ProtocolField.class);
                    
                    byte[] fieldBytes = new byte[protocolField.size()];
                    bais.read(fieldBytes);

                    
                    //create the arg
                    Object objArg = ScalarsConverterFactory.create().byteToObjectConverter( reflectField.getType() ).convert( fieldBytes, protocolField );
    //                    LOGGER.info("-> " + reflectField.getName() + ": " + reflectField.getType() + ": " + objArg );
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
    public <T> byte[] toByte(T obj) throws ProtocolException {
        try {
            Class c = obj.getClass();

            //verify if the current Object has the @ProtocolEntity annotation
            if ( c.isAnnotationPresent(ProtocolEntity.class) ) {

                //initialize the buffer out
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                //get all the fields with @ProtocolField annotation
                List<Field> fieldsWithAnnotation = AnnotationUtils.getFieldsListWithAnnotation(c, ProtocolField.class);
                
                for ( Field reflectField : fieldsWithAnnotation ) {

                        ProtocolField protocolField = (ProtocolField) reflectField.getAnnotation(ProtocolField.class);

                        //read the field
                        Object value = AnnotationUtils.readField( reflectField, obj);
                        
                        //convert the value in byte array
                        Converter<Object, byte[]> converter = (Converter<Object, byte[]>) ScalarsConverterFactory.create().objectToByteConverter( reflectField.getType() );
                        byte[] bytesToWrite = converter.convert( value, protocolField );
                        
                        baos.write( bytesToWrite );

                }

                byte[] res = baos.toByteArray();
                return res;
            } else {
                throw new ProtocolException("The object isn't a BufferOut");
            }
        } catch (ProtocolException pe) {
            throw pe;
        } catch (Exception e) {
            throw new ProtocolException("Error", e);
        }

    }

//    public static byte[] covertToBytes(ProtocolField protocolField, Object value, Type type) throws ProtocolException {
//        byte[] res = null;
//        if (type == String.class ) {
//            res = BufferUtils.padLeft((String) value, protocolField.size()).getBytes();
//        } else if (type == Integer.class || type == int.class ) {
//            res = BufferUtils.pad((Integer) value, protocolField.size()).getBytes();
//        }
//        
//        else {
//            throw new ProtocolException("Not recognized type: " + type );
//        }
//
//        return res;
//    }

//    private static Object covertToObject(byte[] fieldBytes, Type type) throws ProtocolException {
//
//        LOGGER.info("type = " + type);
//        
//        if ( type == String.class ) {
//            return new String(fieldBytes);
//        } else if (type == Integer.class || type == int.class ) {
//            return Integer.parseInt(new String(fieldBytes));
//        }
//        
//        else {
//            throw new ProtocolException("Not recognized type: " + type );
//        }
//    }

}
