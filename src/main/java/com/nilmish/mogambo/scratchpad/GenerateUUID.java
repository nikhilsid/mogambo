package com.nilmish.mogambo.scratchpad;

/**
 * Created by nilesh.m on 27/06/15.
 */

import java.util.UUID;

public class GenerateUUID {

    public static final void main(String... aArgs){
        //generate random UUIDs
        UUID idOne = UUID.randomUUID();
        UUID idTwo = UUID.randomUUID();
        log("UUID One: " + idOne);
        log("UUID Two: " + idTwo);
    }

    private static void log(Object aObject){
        System.out.println( String.valueOf(aObject) );
        System.out.println(aObject.toString().length());
    }
}