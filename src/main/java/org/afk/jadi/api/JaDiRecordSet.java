package org.afk.jadi.api;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * This is used to store the internal data of the configuration
 * <p>
 * Created by axel on 30.10.15.
 */
public class JaDiRecordSet<T> {

    private T value;
    private String id;
    private Consumer<T> setter;

    public JaDiRecordSet(String id, Consumer<T> setter, T value) {
        this.id = id;
        this.setter = setter;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JaDiRecordSet<?> that = (JaDiRecordSet<?>) o;
        return Objects.equals(value, that.value) &&
                Objects.equals(id, that.id) &&
                Objects.equals(setter, that.setter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, id, setter);
    }

    public Consumer<T> getSetter() {
        return setter;
    }

    public void setSetter(Consumer<T> setter) {
        this.setter = setter;
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
