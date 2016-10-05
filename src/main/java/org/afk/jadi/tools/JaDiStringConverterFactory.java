package org.afk.jadi.tools;

import org.afk.jadi.api.JaDiException;
import org.afk.jadi.tools.JaDiStringConverter;

/**
 * Created by axel on 16.11.15.
 */
public interface JaDiStringConverterFactory {
    /**
     *
     * @param clazz
     * @param <T>
     * @return
     * @throws JaDiException
     */
    <T> JaDiStringConverter<T> getConverterForClass(Class<T> clazz) throws JaDiException;
}
