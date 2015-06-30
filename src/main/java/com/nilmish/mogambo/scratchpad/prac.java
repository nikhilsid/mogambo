package com.nilmish.mogambo.scratchpad;

/**
 * Created by nilesh.m on 20/06/15.
 */
public class prac {
    public static String sanitiseUsername(String username){
        username=username.toLowerCase().trim();
        if(username.endsWith("@flipkart.com")){
            int id=username.indexOf('@');
            username=username.substring(0,id);
        }
        return username;
    }

    public static void main(String[] args) {
        String str=sanitiseUsername("  NileSh.m@flipkart.com  ");
        System.out.println(str);
    }
}
