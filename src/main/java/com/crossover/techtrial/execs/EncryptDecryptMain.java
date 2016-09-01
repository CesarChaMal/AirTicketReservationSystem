package com.crossover.techtrial.execs;

import java.util.ArrayList;
import java.util.List;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.stereotype.Controller;

import com.crossover.techtrial.util.EncryptArguments;
import com.crossover.techtrial.util.EncryptionDecryption;

/*
 * 	JVM args
 *  -e passToEncrypt - d passToDecrypt
 *  
 */

@Controller
public class EncryptDecryptMain {
	/*
	 * 
	 * This is meant to be an entry point into the encryption/decryption subsytem to be used locally
	 */
	public static void main(String[] args) {
		try{
			List<EncryptArguments> arguments = parseArguments(args);
	        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
	        encryptor.setAlgorithm("PBEWithMD5AndDES");
	        // Set the password to what was in the EncryptionDecrclass
	        encryptor.setPassword("Rkrl/ZTTdRxtiincyC8veSrQLKikrX8I");
	        EncryptionDecryption.getInstance().setEncryptor(encryptor);
	        EncryptionDecryption cryptor = EncryptionDecryption.getInstance();
			
			for(EncryptArguments arg : arguments){
				try{
					if(arg.getMethod().equals(EncryptArguments.encryption_method.ENCRYPT)){
						System.out.println(EncryptArguments.encryption_method.ENCRYPT.toString() + ":" + arg.getText() + ":" + cryptor.encrypt2(arg.getText()));
					}else if(arg.getMethod().equals(EncryptArguments.encryption_method.DECRYPT)){
						System.out.println(EncryptArguments.encryption_method.DECRYPT.toString() + ":" + arg.getText() + ":" + cryptor.decrypt2(arg.getText()));
					}else{
						System.err.println("Unknown method: " + arg.getMethod().toString());
					}
				}catch(Exception e){
					System.err.println("Exception caught while executing on " + arg.getMethod().toString() + ":" + arg.getText() + " : " + e.getMessage());
					e.printStackTrace();
				}
			}
		}catch(Exception e){
			System.err.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	/*
	 * returns a 
	 */
	private static List<EncryptArguments> parseArguments(String[] theArgs)
	{
		int index = 0;
		List<EncryptArguments> args = new ArrayList<EncryptArguments>();
		for (String arg : theArgs)
		{
		    if(  arg.matches("-h(elp?)?$") )
		    {
		    	showHelp(null);
		    	
		    }
		    else if(arg.equals("-e"))  // encrypt the string
		    {
		    		if( (index + 1) < theArgs.length )
			    	{
							EncryptArguments tmp = new EncryptArguments();
							tmp.setMethod(EncryptArguments.encryption_method.ENCRYPT);
							tmp.setText(theArgs[index + 1]);
							args.add(tmp);
			    	}else
			    		showHelp("ERROR: -e requires an argument.");
		    	
		        
		    }
		    else if(arg.equals("-d"))  // decrypt the string
		    {

			    	if( (index + 1) < theArgs.length )
			    	{
			    		EncryptArguments tmp = new EncryptArguments();
						tmp.setMethod(EncryptArguments.encryption_method.DECRYPT);
						tmp.setText(theArgs[index + 1]);
						args.add(tmp);
			    	}else
			    		showHelp("ERROR: -d requires an argument.");
		    }
		    
		   
			index++;  
		}
		
		// check for required arguments
		if(args.size() == 0)
			showHelp("ERROR: must provide at least one encryption or decryption string");
	
		return args;
		
	}
	
	private static void showHelp(String msg)
	{
		String out;
	    out = ""+
		"This program will output encrypted or decrypted strings		          \n"+
		msg + "\n" +
		"                                                                         \n"+
		" Required :                                                              \n"+
		"	One of:                                                          	  \n"+
		"            -e  \"plaintext to encrypt\"             		  			  \n"+  
		"                                                                         \n"+ 
		"            -d  \"ciphertext to decrypt\"                     			  \n"+  
		"";
		System.out.println(out);
		System.exit(-1);
	}
}
