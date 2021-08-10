package com.spring.bookifybackend.helperClasses;

public class FormRedirect<T> {
    private boolean isRedirect;
    private T object;

    public FormRedirect(boolean isRedirect, T object) {
        this.isRedirect = isRedirect;
        this.object = object;
    }

    public boolean isRedirect() {
        return isRedirect;
    }

    public void setRedirect(boolean redirect) {
        isRedirect = redirect;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormRedirect)) return false;

        FormRedirect<?> that = (FormRedirect<?>) o;

        if (isRedirect() != that.isRedirect()) return false;
        return getObject() != null ? getObject().equals(that.getObject()) : that.getObject() == null;
    }

    @Override
    public int hashCode() {
        int result = (isRedirect() ? 1 : 0);
        result = 31 * result + (getObject() != null ? getObject().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FormRedirect{" +
                "isRedirect=" + isRedirect +
                ", object=" + object +
                '}';
    }
}
