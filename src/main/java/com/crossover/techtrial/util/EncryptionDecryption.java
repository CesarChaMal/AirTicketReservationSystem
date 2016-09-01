package com.crossover.techtrial.util;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncryptionDecryption {
	
	private static Logger logger = Logger.getLogger(EncryptionDecryption.class);
//	private static String algorithm = "DESede";
//	private static String algorithm = "PBEWithMD5AndDES";
//	private static String algorithm = "DESede/ECB/PKCS5Padding";
	private static String algorithm = "PBEWithMD5AndDES/CBC/PKCS5Padding";
    private static Key key = null;
    private static Cipher cipher = null;
    private static BASE64Encoder base64encoder = new BASE64Encoder();
    private static BASE64Decoder base64decoder = new BASE64Decoder();
    private static SecretKeySpec skeySpec = null;
	
	private static EncryptionDecryption theInstance = new EncryptionDecryption();
	
	//	public static final String STRING_TO_DECRYPT = "EnUfeZcH9xFFf4KEXMu3Jg==";
	//	public static final String STRING_TO_DECRYPT = "3AhD7DEMb7uTPM0N6no7HRoBBb2LvqOA";
		public static final String STRING_TO_DECRYPT = "6s9or5YE1afDPpxXufqTUpXECrWsbMeI";
	//	public static final String STRING_TO_DECRYPT = "Rkrl/ZTTdRxtiincyC8veSrQLKikrX8I";
	//	public static final String STRING_TO_ENCRYPT = "Dev@aEtL@$123";
		public static final String STRING_TO_ENCRYPT = "UseIT SMO_@)!%";
		public static final String STRING_KEY = "Rkrl/ZTTdRxtiincyC8veSrQLKikrX8I";
	
	public static EncryptionDecryption getInstance()  {
		return theInstance;
	}
	
	@Autowired
	static StandardPBEStringEncryptor encryptor;

	
	public EncryptionDecryption() {
	    
	    try {
            setUp();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	public void setEncryptor(StandardPBEStringEncryptor encryptor) {
	    EncryptionDecryption.encryptor = encryptor;
	}

    private static void setUp() throws Exception {
   	 	//key = KeyGenerator.getInstance(algorithm).generateKey();
   	 	//String keyString = base64encoder.encode(key.getEncoded());
   	 	//System.out.println("keyString : " + keyString);
    	String keyString = "Rkrl/ZTTdRxtiincyC8veSrQLKikrX8I";
    	byte[] keyB = base64decoder.decodeBuffer(keyString);
//    	key = new SecretKeySpec(keyB, "DESede");
    	key = new SecretKeySpec(keyB,EncryptionDecryption.getInstance().algorithm);
    	
    	//skeySpec = new SecretKeySpec(key, algorithm);
   	 	//String keyString = "Rkrl/ZTTdRxtiincyC8veSrQLKikrX8I";
   	 	//key = base64decoder.decodeBuffer(keyString);
   	 	
//        cipher = Cipher.getInstance("DESede");
    	//cipher = Cipher.getInstance(algorithm);
        cipher = Cipher.getInstance(EncryptionDecryption.getInstance().algorithm);
    }
    
    public static String encrypt2(String value) {
        return encryptor.encrypt(value);
    }
    
    public static String decrypt2(String value) {
        return encryptor.decrypt(value);
    }
    
    public static String encrypt(String input) throws InvalidKeyException, 
           BadPaddingException,
           IllegalBlockSizeException {
    	cipher.init(Cipher.ENCRYPT_MODE, key);
    	byte[] inputBytes = input.getBytes();
    	byte[] encryptionBytes = cipher.doFinal(inputBytes);
    	String encodedString = base64encoder.encode(encryptionBytes);
    	
    	return encodedString;
    }

    @SuppressWarnings("restriction")
	public static String decrypt(String encryptedString) throws InvalidKeyException, 
           BadPaddingException,
           IllegalBlockSizeException, IOException, NullPointerException {
    	
    	logger.debug("EncryptionDecryption : Beginning decrypt()");    	
    	cipher.init(Cipher.DECRYPT_MODE, key);
    	
    	logger.debug("EncryptionDecryption : Inside decrypt() --> Beginning decodeBuffer()");   
    	byte[] encryptedBytes = base64decoder.decodeBuffer(encryptedString);
    	
    	logger.debug("Encrypted string: " +encryptedString);
    	logger.debug("Decoded encrypted Bytes: " + encryptedBytes);
    	
    	logger.debug("EncryptionDecryption : Inside decrypt() --> Beginning doFinal()");
    	byte[] recoveredBytes = cipher.doFinal(encryptedBytes);
    	
    	logger.debug("EncryptionDecryption : Inside decrypt() --> Setting recovered string");
    	String recovered = new String(recoveredBytes);
    	
    	/*System.out.println("Original decrypt: " + recovered);
        String newDec = encrypt2(recovered);
        System.out.println("Encrypted with NEW encryptor: " + newDec);
        String plainText = encryptor.decrypt(newDec);
        System.out.println("Decrypted : " + plainText);
        */
        
    	logger.debug("EncryptionDecryption : Ending decrypt() and returning String");   
    	return recovered;
    }
    
    public static void main ( String []  args )
    {
    	EncryptionDecryption encryptionDecryption;
    	StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
//        encryptor.setAlgorithm("DESede");
//        encryptor.setAlgorithm("PBEWithMD5AndDES");
//        encryptor.setAlgorithm("DESede/ECB/PKCS5Padding");
//        encryptor.setAlgorithm("PBEWithMD5AndDES/CBC/PKCS5Padding");
//        encryptor.setAlgorithm("PBEWithMD5AndDES/ECB/PKCS5Padding");

        
        // Set the password to what was in the EncryptionDecrclass
        encryptor.setPassword(STRING_KEY);
		System.out.println("Public Key : " + STRING_KEY);
		
		EncryptionDecryption.getInstance().setEncryptor(encryptor);
		encryptionDecryption = EncryptionDecryption.getInstance();

		System.out.println("String to encrypt : " + STRING_TO_ENCRYPT);
		String encrypted = EncryptionDecryption.encrypt2(STRING_TO_ENCRYPT);
		System.out.println("String encrypted : " + encrypted);
		
//		String decrypt = EncryptDecrypt.decrypt2("o2+bTqvf5SGI4YdIeQrEfwVHmZJ+pE9Z");
//		String decrypt = EncryptDecrypt.decrypt2("A80G604FUTM973zpG9nz3WiFCyGVHW5N");
		System.out.println("String to decrypt : " + STRING_TO_DECRYPT);
		String decrypt = EncryptionDecryption.decrypt2(STRING_TO_DECRYPT);
		System.out.println("String decrypted : " + decrypt);
		
		try 
		{
			encrypted = encryptionDecryption.decrypt2(encrypted);
		}
		catch (NullPointerException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
    }
    
}

