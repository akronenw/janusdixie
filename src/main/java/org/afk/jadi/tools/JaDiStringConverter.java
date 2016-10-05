package org.afk.jadi.tools;

import java.util.function.Function;

/**
 * Created by axel on 08.11.2015.
 */
public class JaDiStringConverter<T> {
    private final Function<String,T> parser;
    private final Function<T,String> formatter;

    public JaDiStringConverter(Function<String, T> parser, Function<T, String> formatter) {
        this.parser = parser;
        this.formatter = formatter;
    }

    public T parse(String value)
    {
        return parser.apply(value);
    }

    public String format (T value){
        return formatter.apply(value);
    }
}
