package org.afk;

import java.util.Objects;

/**
 * This is used to store the internal data of the configuration
 *
 * Created by axel on 30.10.15.
 */
public class JaDiRecordSet<T> {
    private T value;
    private String id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JaDiRecordSet<?> that = (JaDiRecordSet<?>) o;
        return Objects.equals(value, that.value) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, id);
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
