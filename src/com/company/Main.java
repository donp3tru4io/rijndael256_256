package com.company;

import java.io.UnsupportedEncodingException;
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



    public static void main(String[] args) throws UnsupportedEncodingException {
	// write your code here

        String key = "Some key information for Rijndael";
        String mes = "Testing rijndael work by encrypt";

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


        Random rand = new Random();

        long l = rand.nextLong();

        System.out.println(l);

        byte [] bytes = longToByteArr(l);

        long newL = byteArrToLong(bytes);

        System.out.println(newL);


    }
}
