
public final class Debug {
	public static void Log(Object s){
		System.out.println(s);
	}
	
	public static void Log(String[] s){
		for(int i=0;i<s.length;i++){
			System.out.println(s[i]);
		}
	}
}
