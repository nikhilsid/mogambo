package com.nilmish.mogambo.scratchpad;

import org.bson.types.ObjectId;

/**
 * Created by nilesh.m on 20/06/15.
 */
public class prac {
    public static void main(String[] args) {
        ObjectId id=new ObjectId();
        String str=id.toHexString();
        String str1=id.toString();
        System.out.println(id);
        System.out.println(str);
        System.out.println(str1);
    }
}
