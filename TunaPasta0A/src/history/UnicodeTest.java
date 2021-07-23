package history;
public class UnicodeTest {
	public static void main(String[] args) {
//可以使用字符映射表（charmap）将特殊字符插入文档中。
//打开方法 Win+R输入charmap+回车
//	字符映射表可以显示的字符集：Windows、DOS 和 Unicode。 
		System.out.println('\u0061');
		System.out.println("\u6d4b\u8bd5");
		
		System.out.print("\\u"+Integer.toHexString('修'));
		System.out.println("\\u"+Integer.toHexString('改'));
	}
}

