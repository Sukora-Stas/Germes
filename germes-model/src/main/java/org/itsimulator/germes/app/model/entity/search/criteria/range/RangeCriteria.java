package org.itsimulator.germes.app.model.entity.search.criteria.range;

/**
 * Created by Sukora Stas.
 */


import com.google.common.base.Preconditions;
import org.itsimulator.germes.app.infra.exception.flow.InvalidParameterException;

/**
 * Pagination parameters for data retrieval operations
 */
public class RangeCriteria {
    /**
     * Page index(0-based)
     */
    private final int page;

    /**
     * Number of elements per page
     */
    private final int rowCount;

    public RangeCriteria(final int page, final int rowCount) {
        Preconditions.checkArgument(page >= 0, "Incorrect page index:%s", page);
        Preconditions.checkArgument(rowCount >= 0, "Incorrect row count:%s",
                rowCount);

        this.page = page;
        this.rowCount = rowCount;
    }

    public int getPage() {
        return page;
    }

    public int getRowCount() {
        return rowCount;
    }
}
