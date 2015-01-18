package com.rotterdam.tools;


import javax.crypto.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


public class SecuritySettings {
	  private final static class MySecretKey implements SecretKey {

	        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
			private byte[] key = new byte[]{1, 4, 2, 8, 5, 9, 5, 0}; // ����
	      // �� ������ ����� ����� ����� 8 ����, ��� ����������� ���������� ���
	      // ���������� �������� 

	        public String getAlgorithm() {
	            return "DES";
	        }

	        public String getFormat() {
	            return "RAW";
	        }

	        public byte[] getEncoded() {
	            return key;
	        }
	    }

	    private static SecretKey key;

	    private static Cipher ecipher;
	    private static Cipher dcipher;

	    static {
	        try {
	            key = new MySecretKey();
	            ecipher = Cipher.getInstance("DES");
	            dcipher = Cipher.getInstance("DES");
	            ecipher.init(Cipher.ENCRYPT_MODE, key);
	            dcipher.init(Cipher.DECRYPT_MODE, key);
	        } catch (InvalidKeyException ex) {
	        	ex.printStackTrace();
	        } catch (NoSuchAlgorithmException ex) {
	        	ex.printStackTrace();
	        } catch (NoSuchPaddingException ex) {
	        	ex.printStackTrace();
	        }
	    }


	    /**
	     * ������� ����������
	     * @param str ������ ��������� ������
	     * @return ������������� ������ � ������� Base64
	     */
	    public static String code(String str) {
	        try {
	            byte[] utf8 = str.getBytes("UTF8");
	            byte[] enc = ecipher.doFinal(utf8);
	            return new sun.misc.BASE64Encoder().encode(enc);
	        } catch (IllegalBlockSizeException ex) {
	        	ex.printStackTrace();
	        } catch (BadPaddingException ex) {
	        	ex.printStackTrace();
	        } catch (UnsupportedEncodingException ex) {
	        	ex.printStackTrace();
	        }
	        return null;
	    }

	    /**
	     * ������� �������������
	     * @param str ������������� ������ � ������� Base64
	     * @return �������������� ������
	     */
	    public static String decode(String str)  {
	        try {
	            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
	            byte[] utf8 = dcipher.doFinal(dec);
	            return new String(utf8, "UTF8");
	        } catch (IllegalBlockSizeException ex) {
	        	ex.printStackTrace();
	        } catch (BadPaddingException ex) {
	        	ex.printStackTrace();
	        } catch (IOException ex) {
	        	ex.printStackTrace();
	        }
	        return null;
	    }
	    
	    
	     //����� � ����������� ���������� � ������������ ��������� (������ �������� � ������ ����� ���������) 
	    public static String variableSecure(String variable){
	        String[] queries = {"delete","insert","select","drop","or"};
	        char[]blocked = {')','('};
	        variable =  delQueries(variable.toLowerCase(),queries,blocked);
	        return variable;
	    }

	    //������� ������� ������� ������, ��� �� �������� �����-�������, ������� ����������� �������
	    public static String delQueries(String command, String[] queries, char[] blocked){

	        String tmp = command;
	        if(blocked!=null){
	            for (char vl:blocked){
	                char[] tmpCommand = command.toCharArray();
	                command = "";
	                for (char tmpChar:tmpCommand){
	                    if (tmpChar==vl)tmpChar=' ';
	                    command +=tmpChar;
	                }
	            }
	        }
	        String tmp1 = command;

	        if (queries!=null){
	            for (String query:queries){
	                command = command.replaceAll(query,"");
	            }
	        }

	        if(tmp1.length()==command.length()){
	            return tmp;
	        }
	        return command;
	    }
	    

}
