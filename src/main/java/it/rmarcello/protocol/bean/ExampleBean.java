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
public class ExampleBean {

    @ProtocolField(size=4)
    private String type;        //4
    
    @ProtocolField(size=3)
    private Integer version;    //3
    
    @ProtocolField(size=6)
    private String name;        //6
    
    @ProtocolField(size=10)
    private String description; //10

    public ExampleBean() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ExampleBean{" + "type=" + type + ", version=" + version + ", name=" + name + ", description=" + description + '}';
    }
    
    
    
}
