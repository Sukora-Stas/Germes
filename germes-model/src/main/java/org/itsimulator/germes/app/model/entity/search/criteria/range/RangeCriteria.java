package org.itsimulator.germes.app.model.entity.search.criteria.range;

/**
 * Created by Sukora Stas.
 */


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
