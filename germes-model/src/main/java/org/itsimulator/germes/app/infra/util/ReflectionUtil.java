package org.itsimulator.germes.app.infra.util;

/**
 * Created by Sukora Stas.
 */


import jdk.nashorn.internal.ir.annotations.Ignore;
import org.itsimulator.germes.app.infra.exception.ConfigurationException;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Contains reflection-related utility operations
 */
public class ReflectionUtil {
    private ReflectionUtil() {
    }

    /**
     * Creates an instance of the specified class. This method throws unchecked
     * exception if creation fails
     *
     * @param clz
     * @return
     * @throws ConfigurationException
     */
    public static <T> T createInstance(Class<T> clz) throws ConfigurationException {
        try {
            return clz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ConfigurationException(e);
        }
    }

    /**
     * Returns list of fields with identical names irregardles of their
     * modifiers
     *
     * @param clz1
     * @param clz2
     * @return
     */
    public static List<String> findSimilarFields(Class<?> clz1, Class<?> clz2) throws ConfigurationException {
        try {
            List<Field> fields = getFields(clz1);

            List<String> targetFields = getFields(clz2).stream()
                    .filter(field -> !field.isAnnotationPresent(Ignore.class)).map((field) -> field.getName())
                    .collect(Collectors.toList());
            return fields.stream().filter(field -> !field.isAnnotationPresent(Ignore.class))
                    .filter(field -> !Modifier.isStatic(field.getModifiers())
                            && !Modifier.isFinal(field.getModifiers()))
                    .map((field) -> field.getName()).filter((name) -> targetFields.contains(name))
                    .collect(Collectors.toList());
        } catch (SecurityException ex) {
            throw new ConfigurationException(ex);
        }
    }

    /***
     * Returns all declared fields of the specified classes and all superclasses
     *
     * @param cls
     * @return
     */
    public static <T> List<Field> getFields(Class<?> cls) {
        List<Field> fields = new ArrayList<Field>();
        while (cls != null) {
            fields.addAll(Arrays.asList(cls.getDeclaredFields()));
            cls = cls.getSuperclass();
        }

        return fields;
    }

    /**
     * Copy specified fields values from source to destination objects
     *
     * @param src
     * @param dest
     * @param fields
     */
    public static void copyFields(Object src, Object dest, List<String> fields) throws ConfigurationException {
        Checks.checkParameter(src != null, "Source object is not initialized");
        Checks.checkParameter(dest != null, "Destination object is not initialized");
        try {
            for (String field : fields) {
                Field fld = getField(src.getClass(), field);
                // Skip unknown fields
                if (fld != null) {
                    fld.setAccessible(true);
                    Object value = fld.get(src);

                    Field fldDest = getField(dest.getClass(), field);

                    if (fldDest != null) {
                        fldDest.setAccessible(true);
                        fldDest.set(dest, value);
                    }
                }
            }
        } catch (SecurityException | ReflectiveOperationException | IllegalArgumentException ex) {
            throw new ConfigurationException(ex);
        }
    }

    /**
     * Returns class field by its name. This method supports base classes as
     * well
     *
     * @param clz
     * @param name
     * @return
     */
    public static <T> Field getField(final Class<T> clz, final String name) {
        Class<?> current = clz;
        while (current != null) {
            try {
                return current.getDeclaredField(name);
            } catch (NoSuchFieldException | SecurityException e) {
                current = current.getSuperclass();
            }
        }
        throw new ConfigurationException("No field " + name + " in the class " + clz);
    }
}
