package com.spring.bookifybackend.entities;

public class PaginationRedirect {
    private boolean iSRedirect;
    private Long pageNumber;
    private String context;

    public PaginationRedirect() {
    }

    public PaginationRedirect(boolean iSRedirect, Long pageNumber, String context) {
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

    public Long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getContext() {
        return context;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaginationRedirect)) return false;

        PaginationRedirect that = (PaginationRedirect) o;

        if (isiSRedirect() != that.isiSRedirect()) return false;
        if (getPageNumber() != null ? !getPageNumber().equals(that.getPageNumber()) : that.getPageNumber() != null)
            return false;
        return getContext() != null ? getContext().equals(that.getContext()) : that.getContext() == null;
    }

    @Override
    public int hashCode() {
        int result = (isiSRedirect() ? 1 : 0);
        result = 31 * result + (getPageNumber() != null ? getPageNumber().hashCode() : 0);
        result = 31 * result + (getContext() != null ? getContext().hashCode() : 0);
        return result;
    }

    public void setContext(String context) {
        this.context = context;
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
