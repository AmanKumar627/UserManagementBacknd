package com.fundoo.utility;


	
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
	import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
  import javax.mail.Authenticator;
  import javax.mail.Message;
  import javax.mail.MessagingException;
    import javax.mail.PasswordAuthentication;
	import javax.mail.Session;
	import javax.mail.Transport;
	import javax.mail.internet.InternetAddress;
	import javax.mail.internet.MimeMessage;

import com.fundoo.models.User;

    




	public class Utility 
	{
	private static SecretKeySpec secretKey;
	    private static byte[] key;
	 
	    public static void setKey(String myKey)
	    {
	        MessageDigest sha = null;
	        try {
	            key = myKey.getBytes("UTF-8");
	            sha = MessageDigest.getInstance("SHA-1");
	            key = sha.digest(key);
	            key = Arrays.copyOf(key,16);
	            secretKey = new SecretKeySpec(key, "AES");
	        }
	        catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
       catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
	    }
	 
	    public static String encrypt(String strToEncrypt, String secret)
	    {   
	    	System.out.println("check5");
	        try
	        {
	            setKey(secret);
	            
	            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	          
	          System.out.println("abc");
	          System.out.println(secret);
	          System.out.println("aul");
	            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
	            
        }
	        catch (Exception e)
        {
          System.out.println("Error while encrypting: " + e.toString());
	        }
	        return null;
	    }
	 
	    public static String decrypt(String strToDecrypt, String secret)
	    {
	    	
	        try
	        {
	            setKey(secret);

	        	
	            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

	        	
	            cipher.init(Cipher.DECRYPT_MODE, secretKey);

	        	
	            
	            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
	        }
	        catch (Exception e)
	        {
	            System.out.println("Error while decrypting: " + e.toString());
	        }
	        return null;
	    }
	    
	    public static void sendEmail(User user,String password,String username)
		{  
		     send("aman.kumar627@gmail.com","sxxneeyeikxazsas", user.getEmailId(), password,username); 

		}
		
	    public static void send(final String from,final String password,String toEmail,String userpassword,String username){  
	        //Get properties object 
	    	 
	        Properties props = new Properties(); 
	       
	        props.put("mail.smtp.host", "smtp.gmail.com");    
	        props.put("mail.smtp.socketFactory.port", "465");    
	        props.put("mail.smtp.socketFactory.class",    
                  "javax.net.ssl.SSLSocketFactory");    
	        props.put("mail.smtp.auth", "true");    
	        props.put("mail.smtp.port", "465");    
	        //get Session   
	         Session session = Session.getDefaultInstance(props,    
	         new javax.mail.Authenticator() {    
	         protected PasswordAuthentication getPasswordAuthentication() {    
	         return new PasswordAuthentication(from,password);  
	         }    
	        });    
	        //compose message    
	        try {    
         
	        	String sub=	"User Management ";
	        	
	        	String msg="Your account password" +userpassword+ "and username is" +username;
	         MimeMessage message = new MimeMessage(session);    
	         message.addRecipient(Message.RecipientType.TO,new InternetAddress(toEmail));    
	         message.setSubject(sub);    
	         message.setText(msg);  
	         
	     
         
	         //send message  
	         Transport.send(message);    
	         System.out.println("message sent successfully");    
	        } catch (MessagingException e) {throw new RuntimeException(e);}    
	           
	  }  
//		
//		   public static String OTP() 
//		    { 
//
//		        System.out.println("Generating OTP using random() : "); 
//		        System.out.print("You OTP is : "); 
//		        Random random = new Random(); 
//		  	  	
//		  	  	int otp=0;
//		        for (int i = 0; i < 6; i++) 
//		        {
//		        	if(i==0 && random.nextInt(10)==0)
//		        	{
//		        		i=0;
//		        	}
//		        	else
//		        	{
//		        		otp=otp*10+random.nextInt(10);
//		        	}
//		        }
//		        for (int i = Integer.toString(otp).length(); i < 6; i++) 
//		        {
//		        	otp*=10;
//				}
//		        String otpPassword=otp+"";
//		       System.out.println(otpPassword);
//		       return otpPassword;
//		    }
//		  
//	}
		
		public static String getMd5(String input)
		{
			try { 			  
	            // Static getInstance method is called with hashing MD5 
	            MessageDigest md = MessageDigest.getInstance("MD5"); 
	  
	            // digest() method is called to calculate message digest 
	            //  of an input digest() return array of byte 
	            byte[] messageDigest = md.digest(input.getBytes()); 
	  
	            // Convert byte array into signum representation 
	            BigInteger no = new BigInteger(1, messageDigest); 
	  
	            // Convert message digest into hex value 
	            String hashtext = no.toString(16); 
	            while (hashtext.length() < 32) { 
	                hashtext = "0" + hashtext; 
	            } 
	            return hashtext; 
	        }    
	        // For specifying wrong message digest algorithms 
	        catch (NoSuchAlgorithmException e) { 
	            throw new RuntimeException(e); 
	        } 	
		}
		
		public static void emailSend(String email,String passwd)
		{
			final String fromEmail = "aman.kumar627@gmail.com"; //requires valid gmail id
			final String password = "9905119995"; // correct password for gmail id
			//final String toEmail = request.getParameter("emailid");//"kccaishwarya2012@gmail.com"; // can be any email id 
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com"); 
			props.put("mail.smtp.port", "587"); 
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");			
			Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, password);			
				}
			};		
			Session session = Session.getInstance(props, auth);		
			try
		    {
		      MimeMessage msg = new MimeMessage(session);	    
		      msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));
		      msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));
		      msg.setSubject("Aman", "UTF-8");
		      msg.setText("Your password is :"+passwd+" ", "UTF-8");
		      msg.setSentDate(new Date());
		      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
		      System.out.println("Message is ready");
		      Transport.send(msg);  
		      System.out.println("Email Sent Successfully!!");
			  }
			  catch (Exception e) {
			      e.printStackTrace();
			  }	
		}	
	}
	
	

