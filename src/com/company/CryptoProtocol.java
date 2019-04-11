package com.company;

public class CryptoProtocol {

     private Rijndael rijndael;

     public CryptoProtocol()
     {
         rijndael = new Rijndael();
     }

     public byte[] createClientAuthData(byte[] serverId, byte[] key)
     {
         long timeLable = System.currentTimeMillis();
         byte[] timeLableBytes = Utils.longToByteArr(timeLable);

         byte[] tl_sid = Utils.concatByteArr(timeLableBytes,serverId);

         byte[] encrypted = rijndael.encryptMess(tl_sid, key);

         byte[] message = Utils.concatByteArr(timeLableBytes, encrypted);

         return message;
     }

     public int authentificate(byte[] receivedAuthMessage, byte[] key, byte[] serverId, long timeDifference)
     {
         long timeLabel = System.currentTimeMillis();

         byte[] recOpenTimeLabel = Utils.subArray( receivedAuthMessage,0,8);

         long recTimeDiff = timeLabel - Utils.byteArrToLong(recOpenTimeLabel);

         if (recTimeDiff > timeDifference || recTimeDiff < 0)
             return 1;

         byte[] encrypted = Utils.subArray(receivedAuthMessage,8, receivedAuthMessage.length);
         byte[] decrypted = rijndael.decryptMess(encrypted, key);
         byte[] decTimeLabel = Utils.subArray(decrypted,0,8);

         if (!Utils.bytesEquels(recOpenTimeLabel, decTimeLabel))
             return 2;

         long recDecDiff = timeLabel - Utils.byteArrToLong(decTimeLabel);

         if (recDecDiff > timeDifference || recDecDiff < 0)
             return 3;

         byte[] decServerId = Utils.subArray(decrypted,8, decrypted.length);

         if (!Utils.bytesEquels(serverId, decServerId))
             return 4;
         return 0;
     }

}
