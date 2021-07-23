package history;
//有甲、乙、丙三人，每人或者说真话，或者说假话。 
//甲说：“乙说假话。” 
//乙说：“甲和丙是同一种人。” 
//问甲、乙、丙三人谁说真话，谁说假话？ 
public class LieTest {
	public static void main(String[] args) {
		// 假设0为谎话,1为真话
		// 甲说：“乙说假话。” 即为 a!=b
		// 乙说：“甲和丙是同一种人。” 即为b==1 && a==c || b==0 && a!=c
		for (int a = 0; a <= 1; a++)
			for (int b = 0; b <= 1; b++)
				for (int c = 0; c <= 1; c++)
					if (a != b && (b == 1 && a == c || b == 0 && a != c))
						System.out.println(a + " " + b + " " + c);
	}
}
