package org.itsimulator.germes.app.persistence.hibernate.interceptor;

import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.itsimulator.germes.app.model.entity.base.AbstractEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Initializes mandatory timestamp fields for the entities
 * Created by Sukora Stas.
 */

public class TimestampInterceptor extends EmptyInterceptor {

    private static final long serialVersionUID = 6825201844366406253L;

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        int idx = ArrayUtils.indexOf(propertyNames, AbstractEntity.FIELD_CREATED_AT);
        if (idx >= 0) {
            state[idx] = LocalDateTime.now();
            return true;
        }
        return false;
    }
}