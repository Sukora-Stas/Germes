package org.itsimulator.germes.app.service.transform.impl;

/**
 * Created by Sukora Stas.
 */

import org.itsimulator.germes.app.infra.util.ReflectionUtil;

import java.util.List;

/**
 * Base functionality of the field preparation
 */
public class FieldProvider {
    public List<String> getFieldNames(Class<?> source, Class<?> dest) {
        return ReflectionUtil.findSimilarFields(source, dest);
    }
}