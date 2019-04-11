package com.company;

import java.io.UnsupportedEncodingException;
public class Main {


    private static String key = "This is 256 key for Rijndael alg";
    private static String  IB = "Server B identificator !";
    private static long dt= 5000;

    public static void main(String[] args) {
        // write your code here

        CryptoProtocol protocol = new CryptoProtocol();

        if (protocol.authentificate(protocol.createClientAuthData(IB.getBytes(), key.getBytes()), key.getBytes(), IB.getBytes(),10000) != 0)
            System.out.println("Auth failed");
        else
            System.out.println("Auth success");

        new DemoWindow();

    }
}
