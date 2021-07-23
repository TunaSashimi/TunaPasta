package history;
class StringArgsTest {
	public static void main(String[] args) {
		java.util.Map<String, Integer> m=new java.util.HashMap();
		for (int i = 0; i < args.length; i++) 
			m.put(args[i], m.get(args[i])==null?1:m.get(args[i])+1);
		System.out.println(m.size()+" distinct words detected:");
		System.out.println(m);
	}
}
