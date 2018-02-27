package com.ailu.paas;

import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Signature {
    public Signature() {
    }

    public static String signature(Map<String, Object> maps) throws DigestException {
        String decrypt = getOrderByLexicographic(maps);

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(decrypt.getBytes());
            byte[] messageDigest = digest.digest();
            StringBuffer hexString = new StringBuffer();

            for(int i = 0; i < messageDigest.length; ++i) {
                String shaHex = Integer.toHexString(messageDigest[i] & 255);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }

                hexString.append(shaHex);
            }

            return hexString.toString().toUpperCase();
        } catch (NoSuchAlgorithmException var7) {
            var7.printStackTrace();
            throw new DigestException("签名错误！");
        }
    }

    private static String getOrderByLexicographic(Map<String, Object> maps) {
        return splitParams(lexicographicOrder(getParamsName(maps)), maps);
    }

    private static List<String> getParamsName(Map<String, Object> maps) {
        List<String> paramNames = new ArrayList();
        Iterator var2 = maps.entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry)var2.next();
            paramNames.add(entry.getKey());
        }

        return paramNames;
    }

    private static List<String> lexicographicOrder(List<String> paramNames) {
        Collections.sort(paramNames);
        return paramNames;
    }

    private static String splitParams(List<String> paramNames, Map<String, Object> maps) {
        StringBuilder paramStr = new StringBuilder();
        Iterator var3 = paramNames.iterator();

        while(var3.hasNext()) {
            String paramName = (String)var3.next();
            paramStr.append(paramName);
            Iterator var5 = maps.entrySet().iterator();

            while(var5.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry)var5.next();
                if (paramName.equals(entry.getKey())) {
                    paramStr.append(String.valueOf(entry.getValue()));
                }
            }
        }

        return paramStr.toString();
    }
}
