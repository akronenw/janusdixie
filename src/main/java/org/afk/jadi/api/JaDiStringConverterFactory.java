package org.afk.jadi.api;

/**
 * Created by axel on 16.11.15.
 */
public interface JaDiStringConverterFactory {
    <T> JaDiStringConverter<T> getConverterForClass(Class<T> clazz);
}
