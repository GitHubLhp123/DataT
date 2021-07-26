package com.datat.util.server;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author mlhp1
 */
public class AesCipher {
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static byte[] ivValue = null;
    private static final IvParameterSpec IV_SPEC;
    private static Logger LOG;

    public AesCipher() {
    }

    public static String encrypt(String message) {
        return encrypt(message, "b6fa92796c6431c5");
    }

    public static String encrypt(String message, String key) {
        try {
            return encrypt(message, new SecretKeySpec(CipherHelper.fillChar(key, 16).getBytes("UTF-8"), "AES"));
        } catch (UnsupportedEncodingException var3) {
            LOG.error(var3.getMessage());
            return message;
        }
    }

    public static String encrypt(String data, String key, String iv) {
        try {
            return encrypt(data, "AES/CBC/PKCS5Padding", new SecretKeySpec(CipherHelper.fillChar(key, 16).getBytes("UTF-8"), "AES"), new IvParameterSpec(iv.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException var4) {
            LOG.error(var4.getMessage());
            return data;
        }
    }

    public static String encrypt(String data, SecretKeySpec keySpec) {
        return encrypt(data, "AES/CBC/PKCS5Padding", keySpec, IV_SPEC);
    }

    public static String encrypt(String message, String algorithm, SecretKeySpec keySpec, IvParameterSpec ivSpec) {
        try {
            Cipher c = Cipher.getInstance(algorithm);
            if (ivSpec != null) {
                c.init(1, keySpec, ivSpec);
            } else {
                c.init(1, keySpec);
            }

            byte[] encVal = c.doFinal(message.getBytes("UTF-8"));
            String encryptedValue = Base64.encodeBase64String(encVal);
            return encryptedValue;
        } catch (NoSuchAlgorithmException var7) {
            LOG.error(var7.getMessage());
        } catch (NoSuchPaddingException var8) {
            LOG.error(var8.getMessage());
        } catch (UnsupportedEncodingException var9) {
            LOG.error(var9.getMessage());
        } catch (InvalidKeyException var10) {
            LOG.error(var10.getMessage());
        } catch (InvalidAlgorithmParameterException var11) {
            LOG.error(var11.getMessage());
        } catch (IllegalBlockSizeException var12) {
            LOG.error(var12.getMessage());
        } catch (BadPaddingException var13) {
            LOG.error(var13.getMessage());
        }

        return message;
    }

    public static String decrypt(String encryptedData) {
        return decrypt(encryptedData, "b6fa92796c6431c5");
    }

    public static String decrypt(String encryptedData, String key) {
        try {
            return decrypt(encryptedData, new SecretKeySpec(CipherHelper.fillChar(key, 16).getBytes("UTF-8"), "AES"));
        } catch (UnsupportedEncodingException var3) {
            LOG.error(var3.getMessage());
            return encryptedData;
        }
    }

    public static String decrypt(String encryptedData, String key, String iv) {
        try {
            return decrypt(encryptedData, "AES/CBC/PKCS5Padding", new SecretKeySpec(CipherHelper.fillChar(key, 16).getBytes("UTF-8"), "AES"), new IvParameterSpec(iv.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException var4) {
            LOG.error(var4.getMessage());
            return encryptedData;
        }
    }

    public static String decrypt(String data, SecretKeySpec keySpec) {
        return decrypt(data, "AES/CBC/PKCS5Padding", keySpec, IV_SPEC);
    }

    public static String decrypt(String encryptedData, String algorithm, SecretKeySpec keySpec, IvParameterSpec ivSpec) {
        if (encryptedData != null && encryptedData.length() != 0) {
            try {
                Cipher c = Cipher.getInstance(algorithm);
                if (ivSpec != null) {
                    c.init(2, keySpec, ivSpec);
                } else {
                    c.init(2, keySpec);
                }

                byte[] decordedValue = Base64.decodeBase64(encryptedData);
                byte[] decValue = c.doFinal(decordedValue);
                String decryptedValue = new String(decValue, "utf-8");
                return decryptedValue;
            } catch (NoSuchAlgorithmException var8) {
                LOG.error(var8.getMessage());
            } catch (NoSuchPaddingException var9) {
                LOG.error(var9.getMessage());
            } catch (InvalidKeyException var10) {
                LOG.error(var10.getMessage());
            } catch (InvalidAlgorithmParameterException var11) {
                LOG.error(var11.getMessage());
            } catch (IllegalBlockSizeException var12) {
                LOG.error(var12.getMessage(), var12);
            } catch (BadPaddingException var13) {
                LOG.error(var13.getMessage());
            } catch (UnsupportedEncodingException var14) {
                LOG.error(var14.getMessage());
            }

            return encryptedData;
        } else {
            return "";
        }
    }

    static {
        try {
            ivValue = "7b51fd7053196308".getBytes("UTF-8");
        } catch (UnsupportedEncodingException var1) {
            var1.printStackTrace();
        }

        IV_SPEC = new IvParameterSpec(ivValue);
        LOG = LoggerFactory.getLogger(AesCipher.class);
    }
}
