package it.rmarcello.protocol.complex;

import it.rmarcello.protocol.annotation.FillerType;
import it.rmarcello.protocol.annotation.NumericEncoding;
import it.rmarcello.protocol.annotation.ProtocolEntity;
import it.rmarcello.protocol.annotation.ProtocolField;

/**
 * The Header is an abstract class that define the common attributes of the protocol header.
 * The filds list is the following:
 * version          String              2 bytes
 * sequenceNumber   Integer (bynary)    5 bytes
 * length           Integer (bynary)    3 bytes
 */
@ProtocolEntity
public abstract class HeaderProtocol {

    @ProtocolField(size=2, filler = FillerType.RIGHT )
    protected String version;

    @ProtocolField(size=5, numericEncoding = NumericEncoding.BINARY)
    protected Integer sequenceNumber;  
    
    @ProtocolField(size=3, numericEncoding = NumericEncoding.BINARY)
    protected Integer length;
    
    public HeaderProtocol() {
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "HeaderProtocol [length=" + length + ", sequenceNumber=" + sequenceNumber + ", version=" + version + "]";
    }
    
    
}
