package history;
/**请在Line类中实现以下功能：
 *(1)完成参数列表为（Point point，double slope）的构造器，这两参数表示直线上的一点和直线的斜率
 *(2)完成参数列表为（Point p1，Point p2）的构造器，这两个参数表示直线上的两个点，斜率slope通过下列公式计算：slope=(p2.y2-p1.y1)/(p2.x2-p2.x1)
 *(3)完成参数列表为（int a，int b）的构造器，这两个参数表示直线上距x轴的截离为a和y轴的截离b，表示这条直线包含两个点(a,0)和（0,b)。斜率slope的计算公式和（2）相同。
 *(4)实现判断两条线平行的方法isParallel，斜率相等，两条线平行。
 */
public class StraightLinesTest {
	public static void main(String[] args) {
		new Line(2, 2).isParallel(new Line(new Point(0, 0), -1));
		new Line(2, 2).isParallel(new Line(new Point(0, 0), new Point(1, 1)));
		new Line(2, 2).isParallel(new Line(new Point(2, 0), new Point(0, 2)));
	}
}
class Line {
	public Point p;
	private double slope;
	public Line(Point p,double slope){
		this.p=p;
		this.slope=slope;
	}
	public Line(Point p1,Point p2){
		p=p1;
		slope=(double)(p2.getY()-p1.getY())/(p2.getX()-p1.getX());
	}
	public Line(int a,int b){
		p=new Point(a,0);
		slope=(b-0)/(0-a);
	}
	public void isParallel(Line l){
		if(slope==l.slope){
			if(l.p.getY()-slope*l.p.getX()==this.p.getY()-slope*this.p.getX())
				System.out.println("两直线重合");
			else
				System.out.println("两直线平行");
		}
		else
			System.out.println("两直线相交");
	}
}
class Point {
	private int x;
	private int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
