package reflect;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectSetAccessableTest {  
    public static void main(String[] args) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {  
    	
        Method m = A.class.getDeclaredMethod("getName", new Class[]{});  
        
        //getName是public的,猜猜输出是true还是false  
        System.out.println(m.isAccessible());  
        System.out.println("请稍后");  
          
        A a = new A();  
        a.setName("Mr Lee");  
        
        long start = System.currentTimeMillis();  
        for(int i=0;i<10000000;i++){  
            m.invoke(a, new Object[]{});  
        }  
        System.out.println( "Simple===>              :" +(System.currentTimeMillis() - start));  
          
        m.setAccessible(true);//注意此处不同  
        long start1 = System.currentTimeMillis();  
        for(int i=0;i<10000000;i++){  
            m.invoke(a, new Object[]{});  
        }  
        System.out.println("setAccessible(true)===> :"+( System.currentTimeMillis() - start1));  
        System.out.println();
        
        System.out.println("明显Accessible并不是标识方法能否访问的因为public的方法Accessible仍为false ");
        System.out.println("使用了method.setAccessible(true)后 性能有了20倍的提升 ");
        
        System.out.println();
        System.out.println("Accessable属性是继承自AccessibleObject 类. 功能是启用或禁用安全检查 ");
        System.out.println("AccessibleObject 类是 Field、Method 和 Constructor 对象的基类。\n"
        		+ "它提供了将反射的对象标记为在使用时取消默认 Java 语言访问控制检查的能力。\n"
        		+ "对于公共成员、默认（打包）访问成员、受保护成员和私有成员，\n"
        		+ "在分别使用 Field、Method 或 Constructor 对象来设置或获得字段、调用方法，\n"
        		+ "或者创建和初始化类的新实例的时候，会执行访问检查。 ");
        
        System.out.println("由于JDK的安全检查耗时较多.所以通过setAccessible(true)的方式关闭安全检查就可以达到提升反射速度的目的 ");
    }  
}  

class A{  
    private String name;  
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
}  