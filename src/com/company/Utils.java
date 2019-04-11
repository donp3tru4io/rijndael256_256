package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Utils {

    public static byte[] longToByteArr(long l)
    {
        byte[] bytes = new byte[8];
        for(int i = 7; i >=0 ; i--)
        {
            bytes[i] = (byte)l;
            l = l >> 8;
        }
        return bytes;
    }

    public static long byteArrToLong(byte[] bytes)
    {
        long l = 0;
        for (int i = 0; i < 7; i++)
        {
            l = l | ( bytes[i] & 0xFF);
            l = l << 8;
        }
        l = l | ( bytes[7] & 0xFF);
        return l;
    }

    public static byte[] concatByteArr(byte[] arr1, byte[] arr2)
    {
        byte[] res = new byte[arr1.length + arr2.length];
        int i = 0;
        for( ; i < arr1.length; i++)
            res[i] = arr1[i];
        for(int j = 0; i < res.length; i++, j++)
            res[i] = arr2[j];
        return res;
    }

    public static byte[] subArray(byte[] arr1, int start, int end){
        byte[] res = new byte[end-start];
        for(int i = start, j = 0; i < end; i++, j++)
            res[j] = arr1[i];
        return res;
    }

    public static String byteArrToHex(byte[] arr)
    {
        String res = "";
        for (int i = 0; i < arr.length; i++)
        {
            String hx = Integer.toHexString(arr[i] & 0xff);
            res += hx.length() < 2 ? "0" + hx : hx;
        }
        return res;
    }

    public static boolean bytesEquels(byte[] arr1, byte[] arr2)
    {
        if (arr1.length != arr2.length)
            return false;

        for (int i = 0; i < arr1.length; i++)
        {
            if (arr1[i] != arr2[i]) return false;
        }
        return true;

    }

    public static byte[] hexToByteArray(String s)
    {
        byte[] response;
        if (s.length()%2 == 1) {
            response = new byte[s.length()/2 + 1];
            response[0] = (byte)Integer.parseInt(s.charAt(0)+"",16);
            for (int i = 1,j = 1; i < response.length; i++, j+=2) {
                response[i] = (byte)Integer.parseInt(s.substring(j, j + 2),16);
            }
        }else{
            response = new byte[s.length()/2];
            for (int i = 0, j = 0; i < response.length; i++, j+=2)
                response[i] = (byte)Integer.parseInt(s.substring(j, j + 2),16);
        }
        return response;
    }


    public static boolean hexRegex(String s)
    {
        Pattern pattern = Pattern.compile("[^a-fA-F0-9]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(s);
        return matcher.find();
    }

    public static boolean timeRegex(String s)
    {
        Pattern pattern = Pattern.compile("[^0-9]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(s);
        return matcher.find();
    }

}
