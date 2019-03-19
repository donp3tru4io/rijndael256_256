package com.company;

import java.io.UnsupportedEncodingException;
public class Main {


    private static String key = "This is 256 key for Rijndael alg";
    private static String  IB = "Server B identificator !";
    private static long dt= 5000;

    public static void main(String[] args) {
	// write your code here


        /// A side
        long tA = System.currentTimeMillis();
        byte[] bytetA = Utils.longToByteArr(tA);
        String tAHex = Utils.byteArrToHex(bytetA);

        System.out.println("A: time label in hex : "+tAHex);

        byte[] byteIB = IB.getBytes();
        String hexIB = Utils.byteArrToHex(byteIB);

        System.out.println("A: B identificator in hex : "+hexIB);

        byte[] authData = Utils.concatByteArr(bytetA,byteIB);
        String hexAuthData = Utils.byteArrToHex(authData);
        System.out.println("A: auth data in hex : "+hexAuthData);

        Rijndael rijndael = new Rijndael();

        byte[] encAuthData = rijndael.encryptMess(authData,key.getBytes());
        String hexEncAuthData = Utils.byteArrToHex(encAuthData);
        System.out.println("A: encrypted auth data in hex : "+hexEncAuthData);

        byte[] sendData = Utils.concatByteArr(bytetA,encAuthData);
        String hexSendData = Utils.byteArrToHex(sendData);
        System.out.println("A: data to sent : "+hexSendData);
        System.out.println("A: sending to server B");

        /// B side
        System.out.println();
        System.out.println("B: received data : "+hexSendData);
        long tB = System.currentTimeMillis();
        System.out.println("B: time label in hex : "+Long.toHexString(tB));
        byte[] receivedtA = Utils.subArray(sendData,0,8);
        long tAr1 = Utils.byteArrToLong(receivedtA);
        if (tB - tAr1 > dt || tB - tA < 0)
        {
            System.out.println("B: time out");
            System.out.println("B: response : no authentificated");
            return;
        }

        byte[] recAuthData = Utils.subArray(sendData, 8 ,sendData.length);
        String hexRecAuthData = Utils.byteArrToHex(recAuthData);
        System.out.println("B: received encrypted auth data : "+hexRecAuthData);

        byte[] decryptedAuthData = rijndael.decryptMess(recAuthData, key.getBytes());
        String hexDecAuthData = Utils.byteArrToHex(decryptedAuthData);
        System.out.println("B: decrypted auth data : "+hexDecAuthData);

        byte[] decTA = Utils.subArray(decryptedAuthData,0,8);
        String hexDecTA = Utils.byteArrToHex(decTA);
        System.out.println("B: decrypted time label : "+hexDecTA);

        if (!Utils.bytesEquels(receivedtA,decTA))
        {
            System.out.println("B: different time labels");
            System.out.println("B: response : no authentificated");
            return;
        }

        byte[] decIB = Utils.subArray(decryptedAuthData, 8, decryptedAuthData.length);
        String hexDecIB = Utils.byteArrToHex(decIB);
        System.out.println("B: decrypted B identificator in hex : "+hexDecIB);

        if (!Utils.bytesEquels(decIB,IB.getBytes()))
        {
            System.out.println("B: different B identificators");
            System.out.println("B: response : no authentificated");
            return;
        }

        System.out.println("B: AUTH SUCCESS");

    }
}
