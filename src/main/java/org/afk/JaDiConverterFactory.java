package org.afk;

import java.math.BigInteger;

/**
 * Created by axel on 08.11.2015.
 */
public class JaDiConverterFactory {
    public <T> JaDiConverter<T> getConverter(Class<T> clazz) {
        if (clazz.isAssignableFrom(Integer.class)) {
            return new JaDiConverter<T>(s -> (T) Integer.valueOf(s), s -> s.toString());
        }
        if (clazz.isAssignableFrom(Long.class))
            return new JaDiConverter<T>((s -> (T) Long.valueOf(s)), i -> String.valueOf(i));

        if (clazz.isAssignableFrom(Double.class))
            return new JaDiConverter<T>((s -> (T) Double.valueOf(s)), i -> String.valueOf(i));

        if (clazz.isAssignableFrom(Float.class))
            return new JaDiConverter<T>((s -> (T) Float.valueOf(s)), i -> String.valueOf(i));

        if (clazz.isAssignableFrom(Short.class))
            return new JaDiConverter<T>((s -> (T) Short.valueOf(s)), i -> String.valueOf(i));

        if (clazz.isAssignableFrom(BigInteger.class))
            return new JaDiConverter<T>(s -> (T) new BigInteger(s), i -> String.valueOf(i));

        throw new JaDiException("No Converter for class " + clazz + " defined", JaDiError.NO_CONVERTER);
    }
}
