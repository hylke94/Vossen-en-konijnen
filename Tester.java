/**
 * Een klasse om de applicatie te testen/runnen
 * 
 * @author Hyke de Vries
 * @version 1.0
 */
public class Tester {
	
	@SuppressWarnings("unused")
	private static int heigth = 0;
	@SuppressWarnings("unused")
	private static int width = 0;
	static View sv;
	
	public static void main(String[] args){
		if (args.length==2){
			heigth = Integer.parseInt(args[0]);
			width = Integer.parseInt(args[1]);
		}
		sv = new View(/*heigth,width*/);
	}
}