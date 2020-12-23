package it.rmarcello.protocol.complex;

import java.util.Arrays;

import it.rmarcello.protocol.annotation.NumericEncoding;
import it.rmarcello.protocol.annotation.ProtocolField;

/**
 * The HeartBeatProtocol is an class that define HeartBeat protocol. It extends the header.
 * The filds list is the following:
 * timestamp   Long (bynary)    10 bytes
 * crc     byte (bynary)    3 bytes
 * 
 */
public class HeartBeatProtocol extends HeaderProtocol {

    @ProtocolField(size = 10, numericEncoding = NumericEncoding.BINARY)
    private Long timestamp;

    @ProtocolField(size = 3)
    private byte[] crc;

    public HeartBeatProtocol() {
        super();
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public byte[] getCrc() {
        return crc;
    }

    public void setCrc(byte[] crc) {
        this.crc = crc;
    }

    @Override
    public String toString() {
        return "HeartBeatProtocol [ " + super.toString() + ", crc=" + Arrays.toString(crc) + ", timestamp=" + timestamp + "]";
    }

    
    
}
