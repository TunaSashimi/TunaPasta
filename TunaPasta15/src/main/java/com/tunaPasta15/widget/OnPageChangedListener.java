package com.tunaPasta15.widget;

public interface OnPageChangedListener {

    /**
     * called when the grid is scrolled to another page
     * @param sender grid
     * @param newPageNumber 0 based
     */
    public void onPageChanged(PagedDragDropGrid sender, int newPageNumber);
}
