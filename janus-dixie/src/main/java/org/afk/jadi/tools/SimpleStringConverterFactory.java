package org.afk.jadi.tools;

import org.afk.jadi.api.JaDiException;
import org.afk.jadi.tools.JaDiStringConverter;
import org.afk.jadi.tools.JaDiStringConverterFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

/**
 * The SimpleStringConverterFactory provides JaDiStringConverter for String, Byte, Short, Integer, Long, BigInteger, Float, Double and BigDecimal.
 * If you require more conversions it is safe to extend this factory, overwrite the
 * Created by axel on 08.11.2015.
 */
public class SimpleStringConverterFactory implements JaDiStringConverterFactory {

    @Override
    public <T> JaDiStringConverter<T> getConverterForClass(Class<T> clazz) throws JaDiException {

        Objects.requireNonNull(clazz, "The Class parameter must not be null");

        if (clazz.isAssignableFrom(String.class))
            return new JaDiStringConverter<T>(s -> clazz.cast(s), i -> String.valueOf(i));

        if (clazz.isAssignableFrom(Byte.class))
            return new JaDiStringConverter<T>(s -> clazz.cast(Byte.valueOf(s)), i -> String.valueOf(i));
        if (clazz.isAssignableFrom(Short.class))
            return new JaDiStringConverter<T>(s -> clazz.cast(Short.valueOf(s)), i -> String.valueOf(i));
        if (clazz.isAssignableFrom(Integer.class))
            return new JaDiStringConverter<T>(s -> clazz.cast(Integer.valueOf(s)), s -> s.toString());
        if (clazz.isAssignableFrom(Long.class))
            return new JaDiStringConverter<T>(s -> clazz.cast(Long.valueOf(s)), i -> String.valueOf(i));
        if (clazz.isAssignableFrom(BigInteger.class))
            return new JaDiStringConverter<T>(s -> clazz.cast(new BigInteger(s)), i -> String.valueOf(i));

        if (clazz.isAssignableFrom(Float.class))
            return new JaDiStringConverter<T>(s -> clazz.cast(Float.valueOf(s)), i -> String.valueOf(i));
        if (clazz.isAssignableFrom(Double.class))
            return new JaDiStringConverter<T>(s -> clazz.cast(Double.valueOf(s)), i -> String.valueOf(i));
        if (clazz.isAssignableFrom(BigDecimal.class))
            return new JaDiStringConverter<T>(s -> clazz.cast(new BigDecimal(s)), i -> String.valueOf(i));

        throw new JaDiException("No Converter for class " + clazz + " defined");
    }
}