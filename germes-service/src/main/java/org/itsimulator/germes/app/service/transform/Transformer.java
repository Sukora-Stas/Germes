package org.itsimulator.germes.app.service.transform;

/**
 * Created by Sukora Stas.
 */


import org.itsimulator.germes.app.model.entity.base.AbstractEntity;
import org.itsimulator.germes.app.model.transform.Transformable;

/**
 * Represents transformation engine to convert business entities
 * into DTO objects
 */
public interface Transformer {

    /**
     * Converts specified entity into DTO object
     *
     * @param entity
     * @param clz
     * @return
     */
    <T extends AbstractEntity, P extends Transformable<T>> P transform(T entity, Class<P> clz);

    /**
     * Converts specified entity into existing DTO object
     *
     * @param entity
     * @param dest
     * @return
     */
    <T extends AbstractEntity, P extends Transformable<T>> void transform(T entity, P dest);

    /**
     * Converts specified DTO object into business entity
     *
     * @param dto
     * @param clz
     * @return
     */
    <T extends AbstractEntity, P extends Transformable<T>> T untransform(P dto, Class<T> clz);

}