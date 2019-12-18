package com.rahulgaur.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaCodec;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import static android.content.ContentValues.TAG;

/**
 * Created by Rahul Gaur on 18, December, 2019
 * Email: rahul.gaur152@gmail.com
 * Github: github.com/iRahulGaur
 */
public class Utils {

    private static final String KEY_ALGORITHM = "RSA";
    private static SecretKeySpec secretKey;
    public static String SecretKeyString;
    private static final String AES_PADDING = "AES/ECB/PKCS5PADDING";
    private static final String RSA__PADDING = "RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING";
    private static SharedPreferences.Editor editor;
    private static SharedPreferences sharedPreferences;
    private static String SHARED_PREFERENCE_FILE_NAME;
    private static Class homeActivity;

    /*
    * Padding sizes
        RSA/ECB/PKCS1Padding, 11
        RSA/ECB/NoPadding, 0
        RSA/ECB/OAEPPadding, 42 // Actually it's OAEPWithSHA1AndMGF1Padding
        RSA/ECB/OAEPWithMD5AndMGF1Padding, 34
        RSA/ECB/OAEPWithSHA1AndMGF1Padding, 42
        RSA/ECB/OAEPWithSHA224AndMGF1Padding, 58
        RSA/ECB/OAEPWithSHA256AndMGF1Padding, 66
        RSA/ECB/OAEPWithSHA384AndMGF1Padding, 98
        RSA/ECB/OAEPWithSHA512AndMGF1Padding, 130
        RSA/ECB/OAEPWithSHA3-224AndMGF1Padding, 58
        RSA/ECB/OAEPWithSHA3-256AndMGF1Padding, 66
        RSA/ECB/OAEPWithSHA3-384AndMGF1Padding, 98
        RSA/ECB/OAEPWithSHA3-512AndMGF1Padding, 130
    *
    * real data size will be = (block size / 8) - paddingSize
    * in our case = (2048 / 8) - 66  = 190 (char)
    *
    * AES padding = AES/ECB/PKCS5PADDING
    * */

    //------------------------- Encryption of Data ----------------------------------------------//

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);

        keyGen.initialize(2048);

        return keyGen.generateKeyPair();
    }

    public static byte[] encrypt(String plainText, byte[] publicKeyEncoded) throws Exception {
        //Get Cipher Instance RSA With ECB Mode and OAEPWithSHA3-256AndMGF1Padding Padding
        Cipher cipher = Cipher.getInstance(RSA__PADDING);

        PublicKey publicKey =
                KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publicKeyEncoded));

        //Initialize Cipher for ENCRYPT_MODE
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        //Perform Encryption

        return cipher.doFinal(plainText.getBytes());
    }

    public static String decrypt(byte[] cipherTextArray, PrivateKey privateKey) throws Exception {
        //Get Cipher Instance RSA With ECB Mode and OAEPWithSHA3-256AndMGF1Padding Padding
        Cipher cipher = Cipher.getInstance(RSA__PADDING);

        //Initialize Cipher for DECRYPT_MODE
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        //Perform Decryption
        byte[] decryptedTextArray = cipher.doFinal(cipherTextArray);

        return new String(decryptedTextArray);
    }

    // function to generate a random string of length n
    public static String getAlphaNumericString(int n) {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz"
                + "-_#$%!?*";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    private static void setKey() {
        MessageDigest sha;
        try {
            byte[] key = SecretKeyString.getBytes(StandardCharsets.UTF_8);
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
            Log.e(TAG, "setKey: this is AES secretKey = " + Arrays.toString(secretKey.getEncoded()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    //for encryption
    @SuppressLint("GetInstance")
    public static String AESEncryptionString(String stringData) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(AES_PADDING);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }

        setKey();
        byte[] stringToByte = stringData.getBytes();
        byte[] encryptionByte = new byte[stringToByte.length];

        try {
            assert cipher != null;
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            encryptionByte = cipher.doFinal(stringToByte);

        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        String returnData;
        returnData = new String(encryptionByte, StandardCharsets.ISO_8859_1);
        return returnData;
    }

    //DecryptString
    @SuppressLint("GetInstance")
    public static String AESDecryptionString(String stringData) {
        Cipher decipher = null;
        byte[] encryptedString = stringData.getBytes(StandardCharsets.ISO_8859_1);
        String returnData = stringData;
        setKey();
        try {
            decipher = Cipher.getInstance(AES_PADDING);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        byte[] decryption;
        try {
            assert decipher != null;
            decipher.init(Cipher.DECRYPT_MODE, secretKey);
            decryption = decipher.doFinal(encryptedString);
            returnData = new String(decryption);
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return returnData;
    }

    //------------------------------------Encrypt file------------------------------------------//

    /*
     * encrypt(key, inputFile, encryptedFile);
     * decrypt(key, encryptedFile, decryptedFile);
     *
     * */

    private static final String FILE_ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";

    public static void encrypt(String key, File inputFile, File outputFile)
            throws MediaCodec.CryptoException {
        doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);
    }

    public static void decrypt(String key, File inputFile, File outputFile)
            throws MediaCodec.CryptoException {
        doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile);
    }

    private static void doCrypto(int cipherMode, String key, File inputFile,
                                 File outputFile) throws MediaCodec.CryptoException {
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), FILE_ALGORITHM);
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

    //*************************************Intents*****************************************//

    public static void setIntent(@NonNull Context context, Class destination) {
        Intent intent = new Intent(context, destination);
        context.startActivity(intent);
    }

    public static void setIntentNoBackLog(@NonNull Context context, Class destination) {
        Intent intent = new Intent(context, destination);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void setIntentExtra(@NonNull Context context, Class destination, String key, Bundle data) {
        Intent intent = new Intent(context, destination);
        intent.putExtra(key, data);
        context.startActivity(intent);
    }

    public static void sendToMain(Context context) {
        setIntentNoBackLog(context, homeActivity);
    }

    public static void setHomeActivityClass(Class homeActivity) {
        Utils.homeActivity = homeActivity;
    }

    // --------------------------- Logs and Toasts ------------------------------------------ //
    public static void showMessage(Context context, String message, int toast_length) {
        int length;

        if (toast_length == 1) {
            length = Toast.LENGTH_LONG;
        } else {
            length = Toast.LENGTH_SHORT;
        }

        Toast.makeText(context, message, length).show();
    }

    public static void showLog(String TAG, String message, String exception) {
        Log.e(TAG, "showLog: " + message + " " + exception);
    }

    // ---------------------------- Shared Preferences ------------------------------------ //

    public static void setPreferenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public static void saveString(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public static void saveBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBoolean(String key) {
        try {
            return sharedPreferences.getBoolean(key, false);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void clearPreferences() {
        editor.clear();
        editor.apply();
    }

    public static void saveInt(String key, Integer trainerId) {
        editor.putInt(key, trainerId);
        editor.apply();
    }

    public static int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public static void setSharedPreferenceFileName(String SHARED_PREFERENCE_FILE_NAME) {
        Utils.SHARED_PREFERENCE_FILE_NAME = SHARED_PREFERENCE_FILE_NAME;
    }
}
