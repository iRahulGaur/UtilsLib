package com.rahulgaur.utils;

import android.annotation.SuppressLint;
import android.media.MediaCodec;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import static android.content.ContentValues.TAG;

/**
 * Util file for File Encryption
 */
@SuppressWarnings("unused")
public class FileEncryption {

    /**
     * This method will encrypt the given file using a secret key; recommended key size = 16
     *
     * @param key        secret key for encrypting the file
     * @param inputFile  file you want to encrypt
     * @param outputFile encrypted file
     * @throws MediaCodec.CryptoException Thrown when a crypto error occurs while queueing a secure input buffer
     */
    public static void encrypt(String key, File inputFile, File outputFile)
            throws MediaCodec.CryptoException {
        doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);
    }

    /**
     * This method will decrypt the given file using the same secret key; recommended key size = 16
     *
     * @param key        secret key for encrypting the file
     * @param inputFile  file you want to encrypt
     * @param outputFile encrypted file
     * @throws MediaCodec.CryptoException Thrown when a crypto error occurs while queueing a secure input buffer
     */
    public static void decrypt(String key, File inputFile, File outputFile)
            throws MediaCodec.CryptoException {
        doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile);
    }

    /**
     * This method will decrypt and encrypt the file using secret key
     *
     * @param cipherMode cipher mode can be ENCRYPT or DECRYPT
     * @param key        secret key for encrypting the file
     * @param inputFile  file you want to encrypt
     * @param outputFile encrypted file
     * @throws MediaCodec.CryptoException Thrown when a crypto error occurs while queueing a secure input buffer
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static void doCrypto(int cipherMode, String key, File inputFile,
                                 File outputFile) throws MediaCodec.CryptoException {
        try {
            String FILE_ALGORITHM = "AES";
            Key secretKey = new SecretKeySpec(key.getBytes(), FILE_ALGORITHM);
            String TRANSFORMATION = "AES";
            @SuppressLint("GetInstance") Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(cipherMode, secretKey);

            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();

        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException | IOException ex) {
            Log.e(TAG, "doCrypto: exception 1111 " + ex.getMessage());
        }
    }
}
