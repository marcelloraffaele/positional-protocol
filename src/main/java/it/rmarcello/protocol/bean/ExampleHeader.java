package it.rmarcello.protocol.bean;

import it.rmarcello.protocol.annotation.BufferIn;
import it.rmarcello.protocol.annotation.BufferOut;
import it.rmarcello.protocol.annotation.ProtocolField;

/**
 *
 * @author rmarcello
 */
@BufferIn
@BufferOut
public class ExampleHeader {

    @ProtocolField(size=4)
    protected String messageId;
    
    @ProtocolField(size=3)
    protected Integer version;
    
    @ProtocolField(size=3)
    protected Integer length;
    
    public ExampleHeader() {
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Header{" + "messageId=" + messageId + ", version=" + version + ", length=" + length + '}';
    }

    
    
    
}
