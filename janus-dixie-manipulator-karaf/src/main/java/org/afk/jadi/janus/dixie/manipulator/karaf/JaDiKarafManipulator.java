/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.afk.jadi.janus.dixie.manipulator.karaf;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.afk.jadi.api.JaDiManipulator;
import org.afk.jadi.api.JaDiRecordSet;

/**
 * The JaDiManipulator gets direct acces to all the registered recordsets and
 * provides all the required infromation of these to the Karaf Commands.
 *
 * @author axel
 */

public class JaDiKarafManipulator implements JaDiManipulator {

    private Map<String, JaDiRecordSet<?>> map = new HashMap<>();

    @Override
    public <T> void register(JaDiRecordSet<T> recordSet) {
        map.put(recordSet.getId(), recordSet);
    }

    public Set<String> keySet() {
        return map.keySet();
    }

}
