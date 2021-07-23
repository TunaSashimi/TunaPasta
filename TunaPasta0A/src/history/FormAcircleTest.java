package history;

public class FormAcircleTest {
    public static void main(String[] args) {
        System.out.println("请输入排成一圈的人数n");
        int a = new java.util.Scanner(System.in).nextInt();
        java.util.List<Integer> list = new java.util.ArrayList();
        for (int i = 0; i < a; i++)
            list.add(i + 1);
        for (int k = 1; list.size() > 1; )
            for (java.util.Iterator<Integer> i = list.iterator(); i.hasNext(); k++) {
                i.next();
                if (k % 3 == 0)
                    i.remove();
            }
        System.out.println("原排在第" + list + "的人留下");

//		System.out.println("请输入排成一圈的人数n");
//		int a[]=new int[new java.util.Scanner(System.in).nextInt()];
//		for(int i=0;i<a.length;i++)
//			a[i]=i+1;
//		int j=0,sum=0;
//		for(int i=0;sum<a.length-1;i++){
//				j+=(a[i%a.length]!=0)?1:0;
//				sum+=(j%3==0 & a[i%a.length]!=0)?1:0;	
//				a[i%a.length]=(j%3==0 & a[i%a.length]!=0)?0:a[i%a.length];
//		}
//		for(int i=0;i<a.length;i++)
//			if(a[i]!=0)
//				System.out.println("原排在第"+a[i]+"的人留下");
    }
}