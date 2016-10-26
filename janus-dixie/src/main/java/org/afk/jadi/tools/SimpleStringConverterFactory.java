package org.afk.jadi.tools;

import org.afk.jadi.api.JaDiException;
import org.afk.jadi.tools.JaDiStringConverter;
import org.afk.jadi.tools.JaDiStringConverterFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

/**
 * The SimpleStringConverterFactory provides JaDiStringConverter for String,
 * Byte, Short, Integer, Long, BigInteger, Float, Double and BigDecimal. If you
 * require more conversions it is safe to extend this factory, overwrite the
 * Created by axel on 08.11.2015.
 */
public class SimpleStringConverterFactory implements JaDiStringConverterFactory {

    @Override
    public <T> JaDiStringConverter<T> getConverterForClass(Class<T> clazz) throws JaDiException {

        Objects.requireNonNull(clazz, "The Class parameter must not be null");

        if (String.class.isAssignableFrom(clazz)) {
            return new JaDiStringConverter<>(s -> clazz.cast(s), i -> String.valueOf(i));
        }

        if (Byte.class.isAssignableFrom(clazz)) {
            return new JaDiStringConverter<>(s -> clazz.cast(Byte.valueOf(s)), i -> String.valueOf(i));
        }
        if (Short.class.isAssignableFrom(clazz)) {
            return new JaDiStringConverter<>(s -> clazz.cast(Short.valueOf(s)), i -> String.valueOf(i));
        }
        if (Integer.class.isAssignableFrom(clazz)) {
            return new JaDiStringConverter<>(s -> clazz.cast(Integer.valueOf(s)), s -> s.toString());
        }
        if (Long.class.isAssignableFrom(clazz)) {
            return new JaDiStringConverter<>(s -> clazz.cast(Long.valueOf(s)), i -> String.valueOf(i));
        }
        if (BigInteger.class.isAssignableFrom(clazz)) {
            return new JaDiStringConverter<>(s -> clazz.cast(new BigInteger(s)), i -> String.valueOf(i));
        }
        if (Float.class.isAssignableFrom(clazz)) {
            return new JaDiStringConverter<>(s -> clazz.cast(Float.valueOf(s)), i -> String.valueOf(i));
        }
        if (Double.class.isAssignableFrom(clazz)) {
            return new JaDiStringConverter<>(s -> clazz.cast(Double.valueOf(s)), i -> String.valueOf(i));
        }
        if (BigDecimal.class.isAssignableFrom(clazz)) {
            return new JaDiStringConverter<>(s -> clazz.cast(new BigDecimal(s)), i -> String.valueOf(i));
        }

        throw new JaDiException("No Converter for class " + clazz + " defined");
    }
}
