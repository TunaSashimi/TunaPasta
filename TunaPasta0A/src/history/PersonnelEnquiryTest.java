package history;

import java.util.Scanner;

public class PersonnelEnquiryTest {
    public static void main(String[] args) {
        System.out.println("欢迎使用XX人员信息系统,人员上限50");
        PersonService.message();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String s = sc.next();
            if ("add".equals(s)) {
                PersonService.add();//添加操作
                PersonService.message();//使用提示信息
            } else if ("show".equals(s)) {
                //执行列表显示
                PersonService.show();
                PersonService.message();//使用提示信息
            } else if ("search".equals(s)) {
                //执行查询处理
                PersonService.search();
            } else if ("exit".equals(s)) {
                break;
            } else {
                System.out.println("命令错误!");
            }
        }
    }
}

class Person {
    private String id = "";
    private String name = "";
    private String loc = "";

    public String toString() {
        return "身份证号:" + id + " 姓名:" + name + " 户籍:" + loc;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Person)
            if (this.id.equals(((Person) obj).id))
                return true;
        return false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}

class PersonService {
    static Person[] ps = new Person[50];
    static int i = 0;

    public static void message() {
        System.out.println("========使用方法如下=========");
        System.out.println("添加命令：add \t 显示命令:show \t ");
        System.out.println("查询命令:search 身份证号 \t  退出命令: exit");
    }

    public static void add() {
        System.out.println("当前操作:添加");
        Person p = new Person();
        System.out.print("请输入身份证号:");
        p.setId(new java.util.Scanner(System.in).next());
        System.out.print("请输入姓名:");
        p.setName(new java.util.Scanner(System.in).next());
        System.out.print("请输入户籍:");
        p.setLoc(new java.util.Scanner(System.in).next());
        if (!exist(p)) {
            ps[i++] = p;
            System.out.println("人员添加成功!");
        } else
            System.out.println("人员信息已存在!");
    }

    public static void show() {
        for (Person p : ps) {
            if (p == null)
                break;
            System.out.println(p);
        }
    }

    public static boolean exist(Person person) {
        for (Person p : ps) {
            if (p == null)
                break;
            else if (p.equals(person))
                return true;
        }
        return false;
    }

    public static void search() {
        System.out.print("请输入身份证号:");
        String id = new java.util.Scanner(System.in).next();
        for (int i = 0; i < ps.length; i++) {
            if (ps[i] == null) {
                System.out.println("查无此人");
                break;
            } else if (id.equals(ps[i].getId())) {
                System.out.println(ps[i].getId() + " " + ps[i].getName() + " " + ps[i].getLoc());
                break;
            }
        }
    }
}