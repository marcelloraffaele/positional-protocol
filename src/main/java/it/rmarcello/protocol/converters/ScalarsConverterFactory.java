package it.rmarcello.protocol.converters;

import it.rmarcello.protocol.converters.ScalarsProtocolConverters.BooleanToByteConverter;
import it.rmarcello.protocol.converters.ScalarsProtocolConverters.ByteToBooleanConverter;
import it.rmarcello.protocol.converters.ScalarsProtocolConverters.ByteToByteConverter;
import it.rmarcello.protocol.converters.ScalarsProtocolConverters.ByteToByteConverter2;
import it.rmarcello.protocol.converters.ScalarsProtocolConverters.ByteToIntegerConverter;
import it.rmarcello.protocol.converters.ScalarsProtocolConverters.ByteToLongConverter;
import it.rmarcello.protocol.converters.ScalarsProtocolConverters.ByteToShortConverter;
import it.rmarcello.protocol.converters.ScalarsProtocolConverters.ByteToStringConverter;
import it.rmarcello.protocol.converters.ScalarsProtocolConverters.IntegerToByteConverter;
import it.rmarcello.protocol.converters.ScalarsProtocolConverters.LongToByteConverter;
import it.rmarcello.protocol.converters.ScalarsProtocolConverters.ShortToByteConverter;
import it.rmarcello.protocol.converters.ScalarsProtocolConverters.StringToByteConverter;
import it.rmarcello.protocol.exception.ProtocolException;
import java.lang.reflect.Type;

/**
 *
 * @author rmarcello
 */
public final class ScalarsConverterFactory extends Converter.Factory {

    public static ScalarsConverterFactory create() {
        return new ScalarsConverterFactory();
    }

    private ScalarsConverterFactory() {
    }

    @Override
    public Converter<byte[], ?> byteToObjectConverter(Type type) throws ProtocolException {

        if (type == String.class) {
            return ByteToStringConverter.INSTANCE;
        }
        if (type == Boolean.class || type == boolean.class) {
            return ByteToBooleanConverter.INSTANCE;
        }
        if (type == Short.class || type == short.class) {
            return ByteToShortConverter.INSTANCE;
        }
        if (type == Byte.class || type == byte.class) {
            return ByteToByteConverter.INSTANCE;
        }
        if (type == Integer.class || type == int.class) {
            return ByteToIntegerConverter.INSTANCE;
        }
        if (type == Long.class || type == long.class) {
            return ByteToLongConverter.INSTANCE;
        }

        throw new ProtocolException("Not recognized type: " + type.getTypeName());

    }

    @Override
    public Converter< ?, byte[]> objectToByteConverter(Type type) throws ProtocolException {

        if (type == String.class) {
            return StringToByteConverter.INSTANCE;
        }
        if (type == Boolean.class || type == boolean.class) {
            return BooleanToByteConverter.INSTANCE;
        }
        if (type == Short.class || type == short.class) {
            return ShortToByteConverter.INSTANCE;
        }
        if (type == Byte.class || type == byte.class) {
            return ByteToByteConverter2.INSTANCE;
        }
        if (type == Integer.class || type == int.class) {
            return IntegerToByteConverter.INSTANCE;
        }
        if (type == Long.class || type == long.class) {
            return LongToByteConverter.INSTANCE;
        }

        throw new ProtocolException("Not recognized type: " + type.getTypeName());
    }
}
