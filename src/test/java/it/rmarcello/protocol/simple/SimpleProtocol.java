package it.rmarcello.protocol.simple;

import it.rmarcello.protocol.annotation.FillerType;
import it.rmarcello.protocol.annotation.ProtocolEntity;
import it.rmarcello.protocol.annotation.ProtocolField;

/**
 * SimpleProtocol is a simple text based protocol.
 * The filds list is the following:
 * type     String      2 bytes
 * sender   String      100 bytes
 * message  String      154 bytes
 * Each message is 256 bytes.
 */
@ProtocolEntity
public class SimpleProtocol {
    
    @ProtocolField(size=2)
    private String type;
    
    @ProtocolField(size=100)
    private String sender;
    
    @ProtocolField(size=154, filler = FillerType.RIGHT)
    private String message;

    public SimpleProtocol() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SimpleProtocol [");
        sb.append("type=").append(type);
        sb.append(", sender=").append(sender);
        sb.append(", message=").append(message);
        sb.append("]");
        return sb.toString();
    }
  
    
}