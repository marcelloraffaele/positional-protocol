# positional-protocol
## Java annotation based positional byte protocol

This project is a Java Framework providing Object/Serialized byte mapping support to applications and other components/libraries.
It uses very simple Annotations to make marshalling and unmarshalling of object into(and from) bytes streams.
Create buffer beans is very simple and very similar to Hibernate/Java Persistence entity beans:

## POJO Beans

You can define simple POJO Beans using few simple Annotation to define the mapping:

'''
@BufferIn
@BufferOut
public static class StringBean {
        
	@ProtocolField(size=16)
	private String name;
	
	@ProtocolField(size=16)
	private String surname;
	
	@ProtocolField(size=12, filler = FillerType.RIGHT)
	private String city;
	
}
'''

Coversion is very simple and need only one line of code:

//parsing
ExampleBean bean = (ExampleBean) Engine.fromBytes( byteArray, ExampleBean.class );

//covert to byte array
byte[] res = Engine.toBytes( exampleBean );

The project is not completed at this version it maps only Strings and Integer fields.