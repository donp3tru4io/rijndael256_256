package com.company;

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
            res += Integer.toHexString(arr[i] & 0xff);
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

}
