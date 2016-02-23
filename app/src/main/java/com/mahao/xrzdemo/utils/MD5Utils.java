package com.mahao.xrzdemo.utils;

import java.security.MessageDigest;
import java.util.Date;

/**
 * Created by mdw on 2015/11/10.
 */
public class MD5Utils {


    public static final String MD5(String paramString)
    {
        char[] arrayOfChar1 = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
        try
        {
            byte[] arrayOfByte1 = paramString.getBytes();
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(arrayOfByte1);
            byte[] arrayOfByte2 = localMessageDigest.digest();
            int i = arrayOfByte2.length;
            char[] arrayOfChar2 = new char[i * 2];
            int j = 0;
            int k = 0;
            while (j < i)
            {
                int m = arrayOfByte2[j];
                int n = k + 1;
                arrayOfChar2[k] = arrayOfChar1[(0xF & m >>> 4)];
                k = n + 1;
                arrayOfChar2[n] = arrayOfChar1[(m & 0xF)];
                j++;
            }
            String str = new String(arrayOfChar2);
            return str;
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
        }
        return null;
    }

    public static void getParams(){
        long time = new Date().getTime();

        String str1 = MD5(time + "cqdeveloper");
        String str2 = "";
        char[] arrayOfChar = str1.toCharArray();
        for (int i = 0; i < str1.length(); i++)
            if (i % 2 == 1)
                str2 = str2 + arrayOfChar[i];
    }
}
