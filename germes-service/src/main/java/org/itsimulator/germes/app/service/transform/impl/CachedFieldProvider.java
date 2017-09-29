package org.itsimulator.germes.app.service.transform.impl;

/**
 * Created by Sukora Stas.
 */


import org.itsimulator.germes.app.infra.util.ReflectionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class caches field names for each transformation pair
 */
public class CachedFieldProvider extends FieldProvider{
    /**
     * Mapping between transformation pair(class names) and field list
     */
    private final Map<String, List<String>> cache;

    public CachedFieldProvider() {
        cache = new HashMap<>();
    }

    /**
     * Returns list of similar field names for source/destination classes
     * @param source
     * @param dest
     * @return
     */
    @Override
    public List<String> getFieldNames(Class<?> source, Class<?> dest) {
        String key = source.getSimpleName() + dest.getSimpleName();
        List<String> fields = cache.get(key);
        if(fields == null) {
            fields = ReflectionUtil.findSimilarFields(source, dest);
            cache.put(key, fields);
        }

        return fields;
    }
}