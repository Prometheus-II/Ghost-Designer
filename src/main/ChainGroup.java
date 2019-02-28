package main;

public class ChainGroup {
	
	public String[] stmts;
	public float[] times;
	String[] imgStrs;
	
	public ChainGroup() {
	}
	
	public ChainGroup(int i)
	{
		stmts = new String[i];
		times = new float[i];
		imgStrs = new String[i];
	}
	
	public void test()
	{
		System.out.println(stmts[0]);
		System.out.println(times[0]);
		System.out.println(imgStrs[0]);
	}
}
