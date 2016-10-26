/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.afk.jadi.api;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 *
 * @author axel
 */
public class JanusDixieProxy implements InvocationHandler {

    private final JanusDixie jadi;

    JanusDixieProxy(JanusDixie jadi) {
        this.jadi = jadi;

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        final Configured annotation = method.getAnnotation(Configured.class);
        final String key = annotation.key();
        return jadi.get(key);
    }

}
