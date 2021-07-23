package current;
public class SpecialCharReplaceTest {   

//	由于从服务器后端生成JSON格式的字符串，最后传递到客户端由 eval() 函数动态的生成Javascript，
//	如果JSON字符串格式错误，则eval解析会出错，经常也是最常出错的是 " ' \r\n  这三个字符，如 {name:'I'am'}解析时会出错，
//	同样，由于JavaScript中的字符串可由单引号也可由双引号引起来，所以双引号一样也有这样问题。另外，字符串是不能跨行写的，
//	即你不能写成这样：{name:"I'//	am"}
//	所以这3个需要转换，另外换行可能依赖于平台，所以 \n 也需要转换，比如我们需要将后台抛出的异常栈信息以JSON的格式传回到浏览器上显示时，就需要将回车换行转换。代码如下：
	 public String stringToJson(String s) {        
	        StringBuffer sb = new StringBuffer();        
	        for (int i=0; i<s.length(); i++) {  
	            char c = s.charAt(i);    
	             switch (c){  
	             case '\"':        
	                 sb.append("\\\"");        
	                 break;        
	             case '\\':        
	                 sb.append("\\\\");        
	                 break;        
	             case '/':        
	                 sb.append("\\/");        
	                 break;        
	             case '\b':        
	                 sb.append("\\b");        
	                 break;        
	             case '\f':        
	                 sb.append("\\f");        
	                 break;        
	             case '\n':        
	                 sb.append("\\n");        
	                 break;        
	             case '\r':        
	                 sb.append("\\r");        
	                 break;        
	             case '\t':        
	                 sb.append("\\t");        
	                 break;        
	             default:        
	                 sb.append(c);     
	             }  
	         }      
	        return sb.toString();     
	        }  
	
	
	
}   