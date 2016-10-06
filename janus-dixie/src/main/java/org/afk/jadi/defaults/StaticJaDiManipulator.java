package org.afk.jadi.defaults;

import org.afk.jadi.api.JaDiManipulator;
import org.afk.jadi.api.JaDiRecordSet;

/**
 * The Static JaDiManipulator never changes any configuration values. Created by
 * axel on 28.10.15.
 */
public class StaticJaDiManipulator implements JaDiManipulator {

    @Override
    public <T> void register(JaDiRecordSet<T> recordSet) {
    }

}
