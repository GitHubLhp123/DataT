package com.datat.util.server;

/**
 * @author mlhp1
 */
public class CipherHelper {
    public CipherHelper() {
    }
    public static String fillChar(String key, int minLen) {
        if (key != null && key.length() >= 8) {
            int leng = key.length();
            if (leng >= minLen) {
                return key;
            } else {
                for(int i = leng; i < minLen; ++i) {
                    key = key + "m";
                }

                return key;
            }
        } else {
            return "b6fa92796c6431c5";
        }
    }
}
