package it.rmarcello.protocol.annotation;

/**
 *
 * @author rmarcello
 */
public enum FillerType {
    
    /**
     * Indicates that the Filler provider should pick an appropriate strategy to fill the field.
     */
    AUTO,
    
    /**
     * Indicates that the Filler provider will fill the field on left.
     */
    LEFT,
    
    /**
     * Indicates that the Filler provider will fill the field on right.
     */
    RIGHT
    
}
