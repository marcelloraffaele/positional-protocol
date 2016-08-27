package it.rmarcello.protocol;

import it.rmarcello.protocol.annotation.BufferIn;
import it.rmarcello.protocol.annotation.BufferOut;
import it.rmarcello.protocol.annotation.FillerType;
import it.rmarcello.protocol.annotation.NumericEncoding;
import it.rmarcello.protocol.annotation.ProtocolField;

/**
 *
 * @author rmarcello
 */
public class ProtocolTest {

    @BufferIn
    @BufferOut
    static class ExampleHeader {

        @ProtocolField(size = 4)
        protected String messageId;

        @ProtocolField(size = 3)
        protected Integer version;

        @ProtocolField(size = 3)
        protected Long length;

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

        public Long getLength() {
            return length;
        }

        public void setLength(Long length) {
            this.length = length;
        }

        @Override
        public String toString() {
            return "Header{" + "messageId=" + messageId + ", version=" + version + ", length=" + length + '}';
        }

    }

    static class ExampleBody extends ExampleHeader {

        @ProtocolField(size = 10, filler = FillerType.LEFT)
        private String title;

        @ProtocolField(size = 20)
        private String text;

        @ProtocolField(size = 3, numericEncoding = NumericEncoding.TEXT)
        private int mynumber;

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
            return "ExampleBody{" + super.toString() + ", title=" + title + ", text=" + text + ", mynumber=" + mynumber + '}';
        }

    }
}
