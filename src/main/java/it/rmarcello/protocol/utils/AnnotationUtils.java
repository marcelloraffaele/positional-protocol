package it.rmarcello.protocol.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Inspired to Apache commons 3
 *
 * @author rmarcello
 */
public class AnnotationUtils {

    public static List<Field> getFieldsListWithAnnotation(final Class<?> cls, final Class<? extends Annotation> annotationCls) {

        final List<Field> allFields = getAllFieldsList(cls);
        final List<Field> annotatedFields = new ArrayList<Field>();
        for (final Field field : allFields) {
            if (field.getAnnotation(annotationCls) != null) {
                annotatedFields.add(field);
            }
        }
        return annotatedFields;
    }


    /*
     * Gets all fields of the given class and its parents (if any).
     * Note: the fields order id from father to child.
     * 
     */
    public static List<Field> getAllFieldsList(final Class<?> cls) {
        LinkedList<Field> allFields = new LinkedList<Field>();
        Class<?> currentClass = cls;
        while (currentClass != null) {
            LinkedList<Field> fields = new LinkedList<Field>();
            final Field[] declaredFields = currentClass.getDeclaredFields();
            for (final Field field : declaredFields) {
                fields.add(field);
            }
            currentClass = currentClass.getSuperclass();
            fields.addAll(allFields);
            allFields = fields;
        }
        return allFields;
    }

    /**
     * Writes a {@link Field}.
     *
     * @param field to write
     * @param target the object to call on, may be {@code null} for {@code static} fields
     * @param value to set
     * @throws IllegalArgumentException if the field is {@code null} or {@code value} is not assignable
     * @throws IllegalAccessException if the field is not made accessible or is {@code final}
     */
    public static void writeField(final Field field, final Object target, final Object value) throws IllegalAccessException {
        field.setAccessible(true);
        field.set(target, value);
    }

    /**
     * Reads a {@link Field}.
     *
     * @param field the field to use
     * @param target the object to call on, may be {@code null} for {@code static} fields
     * @return the field value
     * @throws IllegalArgumentException if the field is {@code null}
     * @throws IllegalAccessException if the field is not made accessible
     */
    public static Object readField(final Field field, final Object target) throws IllegalAccessException {
        field.setAccessible(true);
        return field.get(target);
    }

}
