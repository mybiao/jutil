package top.mybiao.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtil {
    /**
     * MD5加密
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String getMd5(String str) throws NoSuchAlgorithmException {
        char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(str.getBytes());
        byte[] bytes = digest.digest();
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            int y = (bytes[i] & 0xF0) >>> 4;
            buf.append(chars[y]);
            int x = bytes[i] & 0xF;
            buf.append(chars[x]);

        }
        return buf.toString();
    }
}
