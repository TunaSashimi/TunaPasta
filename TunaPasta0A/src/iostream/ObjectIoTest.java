package iostream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectIoTest {
    public static void main(String[] args) throws Exception {
        T tt = new T();
        tt.k = 8;
        // 序列化
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/iostream/ObjectIoDemo.dat"));
        oos.writeObject(tt);
        oos.close();
        // 反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/iostream/ObjectIoDemo.dat"));
        T t = (T) ois.readObject();//强转回T型;
        System.out.println("继承自Serializable里面的类也需要继承Serializable接口,不然会报NotSerializableException异常");
        System.out.println("t.i=" + t.i + " t.j= " + t.j + " t.d=" + t.d + " t.k=" + t.k + "t.u.a=" + t.u.a);
    }
}

class T implements Serializable {
    private static final long serialVersionUID = 1L;
    int i = 10;
    int j = 9;
    double d = 2.3;
    U u = new U();
    transient int k = 15;//短暂的,数据不被序列化,重读数据显示0
}

//T里面的类也需要继承Serializable接口,不然会报NotSerializableException异常
class U implements Serializable {
    private static final long serialVersionUID = 1L;
    int a = 10;
}

//序列化之二
//ByteArrayOutputStream bos = new ByteArrayOutputStream();
//ObjectOutputStream oos= new ObjectOutputStream(bos);
//oos.writeObject(t);
//oos.close();
//
//byte[] array = bos.toByteArray();
////反序列化
//ByteArrayInputStream bis= new ByteArrayInputStream(array);
//ObjectInputStream ois= new ObjectInputStream(bis);
//T tread1 = (T)ois.readObject();
//System.out.println(tread1.i+" "+tread1.j+" "+tread1.d+" "+tread1.k);