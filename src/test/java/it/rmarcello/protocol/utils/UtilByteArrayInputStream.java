package it.rmarcello.protocol.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class UtilByteArrayInputStream extends ByteArrayInputStream {

    int pos = 0;

    public UtilByteArrayInputStream(byte[] buf) {
        super(buf);
        pos = 0;
    }

    public byte[] getNextByteArray(int size) throws IOException {
        byte[] v = new byte[size];
        this.read(v);
        pos+=size;
        return v;
    }

    public String getNextString(int size) throws IOException {
        byte[] v = getNextByteArray(size);
        return new String(v);
    }

    public Integer getNextBynaryInt(int size) throws IOException{
        byte[] v = getNextByteArray(size);
        return (int) BufferUtils.binaryToLong(v, 0, v.length);
    }

    public Long getNextBynaryLong(int size) throws IOException{
        byte[] v = getNextByteArray(size);
        return BufferUtils.binaryToLong(v, 0, v.length);
    }

}
