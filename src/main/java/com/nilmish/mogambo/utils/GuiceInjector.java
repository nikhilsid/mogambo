package com.nilmish.mogambo.utils;

import com.google.inject.Injector;

/**
 * Created by nilesh.m on 23/06/15.
 */
public class GuiceInjector {
    private static Injector injector = null;

    public static void assignInjector(Injector givenInjector) {
        if (injector == null)
            synchronized (GuiceInjector.class) {
                if (injector == null) {
                    injector = givenInjector;
                }
            }
        else
            throw new IllegalStateException("Injector instance was already assigned.");
    }

    public static void unassignInjector() {
        if (injector != null)
            synchronized (GuiceInjector.class) {
                if (injector != null) {
                    injector = null;
                }
            }
    }

    public static Injector getInjector() {
        if (injector == null) {
            synchronized (GuiceInjector.class) {
                if (injector == null)
                    throw new IllegalStateException("Injector instance is not assigned. Please assign first");

            }
        }
        return injector;
    }
}
