package it.rmarcello.protocol.converters;

import it.rmarcello.protocol.annotation.FillerType;
import it.rmarcello.protocol.annotation.NumericEncoding;
import it.rmarcello.protocol.annotation.ProtocolField;
import it.rmarcello.protocol.exception.ProtocolException;
import it.rmarcello.protocol.utils.BufferUtils;

/**
 *
 * @author rmarcello
 */
public class ScalarsProtocolConverters {

    static final class ByteToStringConverter implements Converter<byte[], String> {

        static final ByteToStringConverter INSTANCE = new ByteToStringConverter();

        @Override
        public String convert(byte[] value, ProtocolField protocolField) throws ProtocolException {
            return new String(value);
        }
    }

    static final class ByteToBooleanConverter implements Converter<byte[], Boolean> {

        static final ByteToBooleanConverter INSTANCE = new ByteToBooleanConverter();

        @Override
        public Boolean convert(byte[] value, ProtocolField protocolField) throws ProtocolException {
            return (new String(value)).equals("1");
        }
    }

    static final class ByteToShortConverter implements Converter<byte[], Short> {

        static final ByteToShortConverter INSTANCE = new ByteToShortConverter();

        @Override
        public Short convert(byte[] value, ProtocolField protocolField) throws ProtocolException {
            if (protocolField.numericEncoding() == NumericEncoding.AUTO || protocolField.numericEncoding() == NumericEncoding.TEXT) {
                return Short.parseShort(new String(value));
            }
            if (protocolField.numericEncoding() == NumericEncoding.BINARY) {
                return (short)BufferUtils.binaryToLong(value, 0, value.length);
            }
            throw new ProtocolException("Error during conversion: " + value + " to byte array");
        }
    }

    static final class ByteToByteConverter implements Converter<byte[], Byte> {

        static final ByteToByteConverter INSTANCE = new ByteToByteConverter();

        @Override
        public Byte convert(byte[] value, ProtocolField protocolField) throws ProtocolException {
            if (protocolField.numericEncoding() == NumericEncoding.AUTO || protocolField.numericEncoding() == NumericEncoding.TEXT) {
                return Byte.parseByte(new String(value));
            }
            if (protocolField.numericEncoding() == NumericEncoding.BINARY) {
                return (byte)BufferUtils.binaryToLong(value, 0, value.length);
            }
            throw new ProtocolException("Error during conversion: " + value + " to byte array");
        }
    }

    static final class ByteToIntegerConverter implements Converter<byte[], Integer> {

        static final ByteToIntegerConverter INSTANCE = new ByteToIntegerConverter();

        @Override
        public Integer convert(byte[] value, ProtocolField protocolField) throws ProtocolException {
            if (protocolField.numericEncoding() == NumericEncoding.AUTO || protocolField.numericEncoding() == NumericEncoding.TEXT) {
                return Integer.parseInt(new String(value));
            }
            if (protocolField.numericEncoding() == NumericEncoding.BINARY) {
                return (int)BufferUtils.binaryToLong(value, 0, value.length);
            }
            throw new ProtocolException("Error during conversion: " + value + " to byte array");
        }
    }

    static final class ByteToLongConverter implements Converter<byte[], Long> {

        static final ByteToLongConverter INSTANCE = new ByteToLongConverter();

        @Override
        public Long convert(byte[] value, ProtocolField protocolField) throws ProtocolException {
            if (protocolField.numericEncoding() == NumericEncoding.AUTO || protocolField.numericEncoding() == NumericEncoding.TEXT) {
                return Long.parseLong(new String(value));
            }
            if (protocolField.numericEncoding() == NumericEncoding.BINARY) {
                return BufferUtils.binaryToLong(value, 0, value.length);
            }
            throw new ProtocolException("Error during conversion: " + value + " to byte array");
        }
    }

    static final class AyyayByteToArrayByteConverter implements Converter<byte[], byte[]> {

        static final AyyayByteToArrayByteConverter INSTANCE = new AyyayByteToArrayByteConverter();

        @Override
        public byte[] convert(byte[] value, ProtocolField protocolField) throws ProtocolException {
            return value;
        }
    }

    //OBJECT TO BYTE
    static final class StringToByteConverter<T> implements Converter<T, byte[]> {

        static final StringToByteConverter<String> INSTANCE = new StringToByteConverter<>();

        private StringToByteConverter() {
        }

        @Override
        public byte[] convert(T value, ProtocolField protocolField) throws ProtocolException {
            String str = String.valueOf(value);
            if (protocolField.filler() == FillerType.AUTO || protocolField.filler() == FillerType.LEFT) {
                return BufferUtils.padLeft(str, protocolField.size()).getBytes();
            }
            if (protocolField.filler() == FillerType.RIGHT) {
                return BufferUtils.padRight(str, protocolField.size()).getBytes();
            }
            throw new ProtocolException("Error during conversion: " + value + " to byte array");
        }
    }

    static final class IntegerToByteConverter<T> implements Converter<T, byte[]> {

        static final IntegerToByteConverter<Object> INSTANCE = new IntegerToByteConverter<>();

        @Override
        public byte[] convert(T t, ProtocolField protocolField) throws ProtocolException {
            Integer value = (Integer) t;
            if (protocolField.numericEncoding() == NumericEncoding.AUTO || protocolField.numericEncoding() == NumericEncoding.TEXT) {
                return BufferUtils.pad(value, protocolField.size()).getBytes();
            }
            if (protocolField.numericEncoding() == NumericEncoding.BINARY) {
                return BufferUtils.toBinary(value, protocolField.size());
            }
            throw new ProtocolException("Error during conversion: " + value + " to byte array");
        }
    }

    static final class BooleanToByteConverter<T> implements Converter<T, byte[]> {

        static final BooleanToByteConverter<Object> INSTANCE = new BooleanToByteConverter<>();

        @Override
        public byte[] convert(T t, ProtocolField protocolField) throws ProtocolException {
            Boolean b = (Boolean) t;
            if (b.booleanValue()) {
                return "1".getBytes();
            }
            return "0".getBytes();
        }
    }

    static final class ShortToByteConverter<T> implements Converter<T, byte[]> {

        static final ShortToByteConverter<Object> INSTANCE = new ShortToByteConverter<>();

        @Override
        public byte[] convert(T t, ProtocolField protocolField) throws ProtocolException {
            Short value = (Short) t;
            if (protocolField.numericEncoding() == NumericEncoding.AUTO || protocolField.numericEncoding() == NumericEncoding.TEXT) {
                return BufferUtils.pad(value, protocolField.size()).getBytes();
            }
            if (protocolField.numericEncoding() == NumericEncoding.BINARY) {
                return BufferUtils.toBinary(value, protocolField.size());
            }
            throw new ProtocolException("Error during conversion: " + value + " to byte array");
        }
    }

    static final class LongToByteConverter<T> implements Converter<T, byte[]> {

        static final LongToByteConverter<Object> INSTANCE = new LongToByteConverter<>();

        @Override
        public byte[] convert(T t, ProtocolField protocolField) throws ProtocolException {
            Long value = (Long) t;
            if (protocolField.numericEncoding() == NumericEncoding.AUTO || protocolField.numericEncoding() == NumericEncoding.TEXT) {
                return BufferUtils.pad(value, protocolField.size()).getBytes();
            }
            if (protocolField.numericEncoding() == NumericEncoding.BINARY) {
                return BufferUtils.toBinary(value, protocolField.size());
            }
            throw new ProtocolException("Error during conversion: " + value + " to byte array");
        }
    }

    static final class ByteToByteConverter2<T> implements Converter<T, byte[]> {

        static final ByteToByteConverter2<Object> INSTANCE = new ByteToByteConverter2<>();

        @Override
        public byte[] convert(T t, ProtocolField protocolField) throws ProtocolException {
            Byte value = (Byte) t;
            return new byte[] { value.byteValue() };
        }
    }

    static final class ByteArrayToByteConverter<T> implements Converter<T, byte[]> {

        static final ByteArrayToByteConverter<Object> INSTANCE = new ByteArrayToByteConverter<>();

        @Override
        public byte[] convert(T t, ProtocolField protocolField) throws ProtocolException {
            byte[] value = (byte[]) t;
            return value;
        }
    }
}
