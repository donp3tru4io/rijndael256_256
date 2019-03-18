package com.company;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.Random;

public class Main {

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


    public static void main(String[] args) throws UnsupportedEncodingException {
	// write your code here

        String key = "Some key information for Rijndael";
        String mes = "Testing rijndael work by encrypting and decrypting this mes";

        Rijndael rijndael = new Rijndael();
        System.out.println(mes);
        byte[] encrypted = rijndael.encryptMess(mes.getBytes(),key.getBytes());
        System.out.println(new String(encrypted));
        byte[] decrypted = rijndael.decryptMess(encrypted,key.getBytes());
        System.out.println("|" + new String(decrypted) + "|");


        System.out.println(new String(concatByteArr("abcd".getBytes(),"qwerty".getBytes())));
        System.out.println(new String(subArray("qwertyuiop".getBytes(), 2,5)));


//        Rijndael rijndael = new Rijndael(key.getBytes());

//        byte[][] keyArr = rijndael.getKeyArray();
//        System.out.println("------------\nkey");
//        for(int i = 0; i < 8; i++)
//        {
//            for (int j = 0; j < 4; j++)
//                System.out.print((char)keyArr[i][j]+", ");
//            System.out.println();
//        }
//
//
//        System.out.println("------------\ndata");
//
//
//        byte[][] data = rijndael.buildBlock(mes.getBytes(),256);
//        for(int i = 0; i < 4; i++)
//        {
//            for (int j = 0; j < 8; j++)
//                System.out.print((char)data[i][j]+", ");
//            System.out.println();
//        }
//
//
//        System.out.println("------------\nencrypted");
//        data = rijndael.encrypt(data);
//        for(int i = 0; i < 4; i++)
//        {
//            for (int j = 0; j < 8; j++)
//                System.out.print((char)data[i][j]+", ");
//            System.out.println();
//        }
//
//        System.out.println("------------\ndecrypted");
//        data = rijndael.decrypt(data);
//        for(int i = 0; i < 4; i++)
//        {
//            for (int j = 0; j < 8; j++)
//                System.out.print((char)data[i][j]+", ");
//            System.out.println();
//        }


//        Random rand = new Random();
//
//        long l = rand.nextLong();
//
//        System.out.println(l);
//
//        byte [] bytes = longToByteArr(l);
//
//        long newL = byteArrToLong(bytes);
//
//        System.out.println(newL);


    }
}
