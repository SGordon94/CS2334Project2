
public final class Debug {
	public static void Log(String s){
		System.out.println(s);
	}
	
	public static void Log(String[] s){
		for(int i=0;i<s.length;i++){
			System.out.println(s[i]);
		}
	}
	
	public static void Log(int s){
		System.out.println(s);
	}
	
	public static void Log(float s){
		System.out.println(s);
	}
	
	public static void Log(double s){
		System.out.println(s);
	}
}
