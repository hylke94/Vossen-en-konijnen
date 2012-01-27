/**
 * Een klasse om de applicatie te testen/runnen
 * 
 * @author Hyke de Vries
 * @version 1.0
 */
public class Tester {
	
	private static int heigth = 0;
	private static int width = 0;
	static Simulator sim;
	
	public static void main(String[] args){
		if (args.length==2){
			heigth = Integer.parseInt(args[0]);
			width = Integer.parseInt(args[1]);
		}
		try{
			sim = new Simulator(heigth,width);
		}
		catch (Exception e){
			System.out.println(e);
		}
	}
}