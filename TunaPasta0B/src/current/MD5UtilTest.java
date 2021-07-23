package current;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * 提供将任意字符串转换成MD5加密字符串的工具方法。
 *
 */
public class MD5UtilTest {
	/**
	 * 该方法将指定的字符串用MD5算法加密后返回。
	 */
	public static String getMD5Encoding(String s) {
		byte[] input=s.getBytes(); 
		String output = null; 
//		声明16进制字母 
		char[] hexChar={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'}; 
		try{ 
			//		获得一个MD5摘要算法的对象 
			MessageDigest md=MessageDigest.getInstance("MD5"); 
			md.update(input); 
			/* 
			 MD5算法的结果是128位一个整数,在这里javaAPI已经把结果转换成字节数组了 
			 */ 
			byte[] tmp = md.digest();//获得MD5的摘要结果 
			char[] str = new char[32]; 
			byte b=0; 
			for(int i=0;i<16;i++){ 
				b=tmp[i]; 
				str[2*i] = hexChar[b>>>4 & 0xf];//取每一个字节的低四位换成16进制字母 
				str[2*i+1] = hexChar[b & 0xf];//取每一个字节的高四位换成16进制字母 
			} 
			output = new String(str); 
		}catch(NoSuchAlgorithmException e){ 
			e.printStackTrace(); 
		} 
		
		return output; 
	}
	
	public static void main(String[] args){
		String str=MD5UtilTest.getMD5Encoding("TunaSashimi");
		System.out.println(str);
		
		String strs=MD5UtilTest.getMD5Encoding("Tuna");
		System.out.println(strs);
	}

}

