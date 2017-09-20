package org.itsimulator.germes.app.infra.util;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Sukora Stas.
 */

/**
 * Contains utility functions of the general purpose
 */
public class CommonUtil {
    private CommonUtil() {
    }

    /**
     * Returns not-null unmodifiable copy of the source set
     * @param source
     * @return
     */
    public static <T> Set<T> getSafeSet(Set<T> source) {
        return Collections.unmodifiableSet(Optional.ofNullable(source).orElse(Collections.emptySet()));
    }

    /**
     * Returns not-null unmodifiable copy of the source list
     * @param source
     * @return
     */
    public static <T> List<T> getSafeList(List<T> source) {
        return Collections.unmodifiableList(Optional.ofNullable(source).orElse(Collections.emptyList()));
    }

}


