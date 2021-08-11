package com.spring.bookifybackend.helperClasses;

import java.io.Serializable;

public class PaginationRedirect implements Serializable {
    private boolean iSRedirect;
    private int pageNumber;
    private String context;

    public PaginationRedirect() {
    }

    public PaginationRedirect(boolean iSRedirect, int pageNumber, String context) {
        this.iSRedirect = iSRedirect;
        this.pageNumber = pageNumber;
        this.context = context;
    }

    public boolean isiSRedirect() {
        return iSRedirect;
    }

    public void setiSRedirect(boolean iSRedirect) {
        this.iSRedirect = iSRedirect;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaginationRedirect)) return false;

        PaginationRedirect that = (PaginationRedirect) o;

        if (isiSRedirect() != that.isiSRedirect()) return false;
        if (getPageNumber() != that.getPageNumber()) return false;
        return getContext() != null ? getContext().equals(that.getContext()) : that.getContext() == null;
    }

    @Override
    public int hashCode() {
        int result = (isiSRedirect() ? 1 : 0);
        result = 31 * result + getPageNumber();
        result = 31 * result + (getContext() != null ? getContext().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PaginationRedirect{" +
                "iSRedirect=" + iSRedirect +
                ", pageNumber=" + pageNumber +
                ", context='" + context + '\'' +
                '}';
    }


}
