package pr;

import java.util.Scanner;

public class ServerMain {
	public static void main(String[] args)
	{
		while(true)
		{
			Scanner in=new Scanner(System.in);
			String instr=in.next();
			if(instr.equals("set")||instr.equals("s")||instr.equals("setup")||instr.equals("set up")||instr.equals("setUp"))
			{
				new DataBaseSetUp().setUp();
				System.out.println("setup done!");
			}
			else if(instr.equals("u")||instr.equals("update"))
			{
				new DataBaseUpdate().update();
				System.out.println("update done!");
			}
			else if(instr.equals("q")||instr.equals("quit")||instr.equals("exit")||instr.equals("end"))
			{
				System.out.println("end!");
				System.exit(0);
			}
		}
	}
	
}
