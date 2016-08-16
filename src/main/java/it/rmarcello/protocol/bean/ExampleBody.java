package it.rmarcello.protocol.bean;

import it.rmarcello.protocol.annotation.ProtocolField;
import java.util.List;

/**
 *
 * @author rmarcello
 */
public class ExampleBody extends ExampleHeader{

    @ProtocolField(size=10)
    private String title;
    
    @ProtocolField(size=20)
    private String text;
        
//    @ProtocolField(size=20)
//    private List<Integer> list;
    
    public ExampleBody() {
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ExampleBody{" + super.toString() + ", title=" + title + ", text=" + text + '}';
    }
    
}
