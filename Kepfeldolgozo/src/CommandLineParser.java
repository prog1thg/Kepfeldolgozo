import java.io.File;

public class CommandLineParser {

	/**
	 * @param args
	 */
	public static final int MAXARG=2;
	public static void main(String[] args) {
		String welcome = "Hiányzó paraméter. Segítséghez: -help";
		String bemenet = "";
		String kimenet = "";
		String kiterjesztes = "";
		String[] transzformacio = new String[MAXARG];
		String[] effekt = new String[MAXARG];
		
		
	    if (args.length>0) {
	    	// Ha az elsõ paraméter help, akkor jön egy kis segítség
		    if (args[0].equals("-help")) {
	    		System.out.println("Képátalakító program" + System.getProperty("line.separator") +
	    				            "Parancsok: " + System.getProperty("line.separator") +
	    				            "-b " + System.getProperty("line.separator") +
	    				            "-t " + System.getProperty("line.separator") +
	    				            "-e " + System.getProperty("line.separator") +
	    				            "-k " + System.getProperty("line.separator") +
	    				            System.getProperty("line.separator") +
	    				            "Készítették: " + System.getProperty("line.separator") +
	    				            "Dzadik Deniel, Fekete Tamás, Szabó Zoltán, Üveges László" + System.getProperty("line.separator") +
	    				            "2012, Programozás I. Tehetséggondozó, 4-es csoport");
	    		System.exit(0);
		    }
		    for (int i = 0; i < args.length; i++) {
		    	if (args[i].equals("-b")) if (i+1 < args.length) bemenet = args[i+1]; 				    		
		    	if (args[i].equals("-k")) if (i+1 < args.length) kimenet = args[i+1];
		    	if (args[i].equals("-t")) {
		    		for (int j = 0; j < MAXARG; j++)
		    			if (i+1+j < args.length) transzformacio[j] = args[i+1+j];
		    	}
		    	if (args[i].equals("-e")) {
		    		for (int j = 0; j < MAXARG; j++)
		    			if (i+1+j < args.length) effekt[j] = args[i+1+j];
		    	}		    	
		    }
		    // Ha nincs bemeneti paraméter, nincs mirõl tovább beszélni..		   
	    	if (bemenet != null && bemenet.length() == 0) {
	    		System.out.println(welcome);
	    		System.exit(0);
	    	}
	    	// Ha nem létezik a fájl, nincs mirõl tovább beszélni..
	    	File imgFile = new File(bemenet);

	    	if (!imgFile.exists()) {
	    		System.out.println("Nem létezõ fájl. (" + bemenet + ")");
	    		System.exit(0);	    		
	    	}
	    	// Ha a teljes bemeneti kép kevesebb, mint 5 karakter, akkor biztos nem jo [pl.: n.jpg már 5 karakter, njpg, jpg, pg pedig nem megfelelõ]
	    	if (bemenet.length() < 5) {
	    		System.out.println("Nem megfelelõ formátumú fájl.");
	    		System.exit(0);
	    	}
	    	// Kiterjesztés
	    	kiterjesztes = bemenet.substring(Math.max(0, bemenet.length() - 3));
	    	// Default kimenet
	    	if (kimenet != null && kimenet.length() == 0) {
	    		kimenet = bemenet + "_new" + kimenet.length();	    		
	    	}
	    }
	    // Alapértelmezetten ez jön be
	    else System.out.println(welcome);
	    
	    
	    // Teszthez: 
	    System.out.println("Bemenet: " + bemenet);
	    System.out.println("Kimenet: " + kimenet);
	    System.out.println("Transzformáció 1: " + transzformacio[0]);
	    System.out.println("Transzformáció 2: " + transzformacio[1]);	    
	    System.out.println("Effekt 1: " + transzformacio[0]);
	    System.out.println("Effekt 2: " + transzformacio[1]);
	    System.out.println("Kiterjesztés: " + kiterjesztes);
		// TODO Auto-generated method stub

	}

}
