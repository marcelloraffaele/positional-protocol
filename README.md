# positional-protocol
Java annotation based positional byte protocol

This project is a little framework useful to make marshalling and unmarshalling of object into(and from) bytes streams.
It Uses  Very simple Annotations to make it very simple, quicly and safe.
Create buffer beans is very simple and very similar to Hibernate/Java Persistence entity beans:

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
	
	...
	
}

Coversion is very simple and need only one line of code:

//parsing
ExampleBean bean = (ExampleBean) Engine.fromBytes( byteArray, ExampleBean.class );

//covert to byte array
byte[] res = Engine.toBytes( exampleBean );

The project is not completed at this version it maps only Strings and Integer fields.