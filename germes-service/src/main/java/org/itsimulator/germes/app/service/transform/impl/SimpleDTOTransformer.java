package org.itsimulator.germes.app.service.transform.impl;

import org.itsimulator.germes.app.infra.util.Checks;
import org.itsimulator.germes.app.infra.util.CommonUtil;
import org.itsimulator.germes.app.infra.util.ReflectionUtil;
import org.itsimulator.germes.app.model.entity.base.AbstractEntity;
import org.itsimulator.germes.app.model.transform.Transformable;
import org.itsimulator.germes.app.service.transform.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default transformation engine that uses reflection to transform objects
 */
public class SimpleDTOTransformer implements Transformer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleDTOTransformer.class);

    private final FieldProvider provider;

    public SimpleDTOTransformer() {
        provider = new CachedFieldProvider();
    }

    @Override
    public <T extends AbstractEntity, P extends Transformable<T>> P transform(final T entity, final Class<P> clz) {
        checkParams(entity, clz);

        return handleTransformation(entity, ReflectionUtil.createInstance(clz));
    }

    private void checkParams(final Object param, final Class<?> clz) {
        checkParam(param, "Source transformation object is not initialized");
        checkParam(clz, "No class is defined for transformation");
    }

    private void checkParam(final Object param, final String errorMessage) {
        Checks.checkParameter(param != null, errorMessage);
    }

    @Override
    public <T extends AbstractEntity, P extends Transformable<T>> T untransform(final P dto, final Class<T> clz) {
        checkParams(dto, clz);

        T entity = ReflectionUtil.createInstance(clz);

        ReflectionUtil.copyFields(dto, entity, provider.getFieldNames(dto.getClass(), clz));
        dto.untransform(entity);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("SimpleDTOTransformer.transform: {} entity", CommonUtil.toString(dto));
        }

        return entity;
    }

    @Override
    public <T extends AbstractEntity, P extends Transformable<T>> void transform(final T entity, final P dest) {
        checkParam(entity, "Source transformation object is not initialized");
        checkParam(dest, "Destination object is not initialized");

        handleTransformation(entity, dest);
    }

    private <T extends AbstractEntity, P extends Transformable<T>> P handleTransformation(final T entity, final P dest) {
        ReflectionUtil.copyFields(entity, dest, provider.getFieldNames(entity.getClass(), dest.getClass()));
        dest.transform(entity);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("SimpleDTOTransformer.transform: {} DTO object", CommonUtil.toString(dest));
        }
        return dest;
    }
}