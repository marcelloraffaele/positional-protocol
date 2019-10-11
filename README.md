# positional-protocol
## Java annotation based positional byte protocol

This project is a Java Framework providing Object/Serialized byte mapping support to applications and other components/libraries.
It uses very simple Annotations to make marshalling and unmarshalling of object into(and from) bytes streams.
Create buffer beans is very simple and very similar to Hibernate/Java Persistence entity beans:

## POJO Beans

You can define simple POJO Beans using few simple Annotation to define the mapping:

```java
@BufferIn
@BufferOut
public static class StringBean {

	@ProtocolField(size=16)
	private String name;

	@ProtocolField(size=16)
	private String surname;
	
	@ProtocolField(size=12, filler = FillerType.RIGHT)
	private String city;
	
	//...
}
```

Coversion is very simple and need only one line of code:

```java
//parsing
StringBean bean = EngineFactory.create().parseFrom(buffer1, StringBean.class );

//covert to byte array
byte[] buffer = EngineFactory.create().toByteArray(bean);
```

## Mapping of all scalar types

All the scalar types are enabled:
- int/Integer
- short/Short
- byte/Byte
- long/Long
- String

And this is an example:

```java
@BufferIn
@BufferOut
static class ExampleHeader {

	@ProtocolField(size = 4)
	protected String messageId;

	@ProtocolField(size = 3)
	protected Integer version;

	@ProtocolField(size = 3)
	protected Long length;
	
	//...
}
```

## Support inheritance
The developer can define a new classes from existing classes.
The mapping works appending the fields to the super class fields.

```java
class ExampleBody extends ExampleHeader {

	@ProtocolField(size = 10, filler = FillerType.LEFT)
	private String title;

	@ProtocolField(size = 20)
	private String text;

	@ProtocolField(size = 3)
	private int mynumber;
	//...
}
```

The library is the same:

```java
//parsing
ExampleBody bodyMsg = e.parseFrom(s.getBytes() , ExampleBody.class);
//covert to byte array
byte[] v = e.toByteArray(bodyMsg);
```

## Different kind of String filling
```java
@ProtocolField(size = 10, filler = FillerType.LEFT)	//fill on left
@ProtocolField(size = 10, filler = FillerType.RIGHT)	//fill on right
```

## Different kind of Numeric encoding
```java
@ProtocolField(size = 3, numericEncoding = NumericEncoding.TEXT)
@ProtocolField(size = 3, numericEncoding = NumericEncoding.BYNARY)
```
