package it.rmarcello.protocol.utils;

/**
 *
 * @author rmarcello
 */
public class BufferUtils {

    public static String padLeft(String s, int size) {
        String res = "";
        if (s != null) {
            res += s;
        }
        if (res.length() < size) {
            for (int i = res.length(); i < size; i++) {
                res += " ";
            }
        }
        return res;
    }

    public static String padRight(String s, int size) {
        String res = "";
        if (s != null) {
            res += s;
        }
        if (res.length() < size) {
            for (int i = res.length(); i < size; i++) {
                res = " " + res;
            }
        }
        return res;
    }

    public static byte[] toBinary(Integer value, int size) {
        return toBinary((long) value, size);
    }

    public static byte[] toBinary(Short value, int size) {
        return toBinary((long) value, size);
    }

    public static byte[] toBinary(Long l, int size) {
        byte[] result = new byte[size];
        for (int i = size-1; i >= 0; i--) {
            result[i] = (byte)(l & 0xFF);
            l >>= 8;
        }
        return result;

    }
    
    public static long binaryToLong(byte[] buffer, int start, int lunghezzaInByte) {
        byte[] subBuffer = new byte[lunghezzaInByte];
        System.arraycopy(buffer, start, subBuffer, 0, subBuffer.length);

        String hexString = byteArrayToHex(subBuffer);

        return Long.parseLong(hexString, 16);

    }

    public static String pad(Integer x, int size) {
        return pad(x.toString(), '0', size);
    }

    public static String pad(Short x, int size) {
        return pad(x.toString(), '0', size);
    }

    public static String pad(Long x, int size) {
        return pad(x.toString(), '0', size);
    }

    /**
     * BufferUtils adding the padding char at the end of the string. Es: "hello" -> "hello "
     *
     * @param x
     * @param padding
     * @param size
     * @return
     */
    public static String pad(String x, char padding, int size) {
        StringBuilder b = new StringBuilder(x);

        if (x.length() < size) {
            for (int i = x.length(); i < size; i++) {
                b.append(padding);
            }
        }
        return b.toString();
    }

    public static byte[] hexToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static String byteArrayToHex(byte[] buf) {
        final char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();
        char[] chars = new char[2 * buf.length];
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; ++i) {
            chars[2 * i] = HEX_CHARS[(buf[i] & 0xF0) >>> 4];
            chars[2 * i + 1] = HEX_CHARS[buf[i] & 0x0F];
            sb.append(chars[2 * i]);
            sb.append(chars[2 * i + 1]);
        }

        return sb.toString();
    }

}
