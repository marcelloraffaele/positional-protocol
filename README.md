# positional-protocol
## Java Annotation-based byte array protocol framework

This project is a Java Framework providing Object/Serialized byte mapping support.
It uses very simple Annotations to make marshalling and unmarshalling of object into(and from) bytes arrays.
Defining a protocol is super easy and very similar to Hibernate/Java Persistence entity beans:

## POJO Beans

You can define simple POJO Beans using few simple Annotation to define the mapping:

```java
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
	
	//constructor, get and setter
}
```

Coversion is very simple and need only one line of code:

```java
//parsing
SimpleProtocol simpleProtocol = engine.fromByte(sourceBuffer, SimpleProtocol.class); 

//covert to byte array
byte[] buffer = EngineFactory.create().toByte(simpleProtocol);
```

## Mapping of all scalar types
All the scalar types are enabled:
- int/Integer
- short/Short
- byte/Byte/byte[]
- long/Long
- String


## Support inheritance
The developer can extend existing classes. The mapping works appending the fields to the super class fields.
In this way you can define header common part and separate Body entities will extend the header.

```java
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
    
    //constructor, get and setter
}


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
    
    //constructor, get and setter

}
```

The library is the same:

```java
//create an Engine
Engine engine = EngineFactory.create();

//parsing
HeartBeatProtocol heartBeatParsed = engine.fromByte(heartBeatByte, HeartBeatProtocol.class);

//covert to byte array
byte[] heartBeatByte = engine.toByte(heartBeat);

```

## Different kind of String filling
```java
//fill on left
@ProtocolField(size = 10, filler = FillerType.LEFT)

//fill on right
@ProtocolField(size = 10, filler = FillerType.RIGHT)
```

## Different kind of Numeric encoding
```java
@ProtocolField(size = 3, numericEncoding = NumericEncoding.TEXT)
@ProtocolField(size = 3, numericEncoding = NumericEncoding.BYNARY)
```
