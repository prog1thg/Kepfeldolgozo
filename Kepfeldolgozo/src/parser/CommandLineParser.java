package parser;

import java.io.File;

public class CommandLineParser {
	/**
	 * Command Line Parser
	 * 
	 * Processes the command-line arguments to further use.
	 * 
	 * @author dzdeni
	 *
	 */			

	/**
	 * Defines that the software how many transforms and effects have.
	 */
	private static final int MAXARG=2;
	
	/**
	 * The default error message when a parameter is missing.
	 */
	private String error = "Missing parameter. Type \"-help\" for help";
	
	/**
	 * A kind of welcome message with help and about.
	 */
	private String welcome = "Image Transformer Software" + System.getProperty("line.separator") +
					         "Commands: " + System.getProperty("line.separator") +
					         "-b " + System.getProperty("line.separator") +
					         "-t " + System.getProperty("line.separator") +
					         "-e " + System.getProperty("line.separator") +
					         "-k " + System.getProperty("line.separator") +
							         System.getProperty("line.separator") +
					         "Created by: " + System.getProperty("line.separator") +
					         "Deniel Dzadik, Tamás Fekete, Zoltán Szabó, László Üveges" + System.getProperty("line.separator") +
					         "2012, Programming I. THG, Group 4.";
	
	/**
	 * @param -i The input file name and extension.
	 */
	public String filein = "";
	
	/**
	 * @param -o The output file name and extension.
	 */
	public String fileout = "";
	
	/**
	 * @param -t The transformation(s) that the software should make.
	 */
	public String[] transformation = new String[MAXARG];
	
	/**
	 * @param -t The effect(s) that the software should make.
	 */	
	public String[] effect = new String[MAXARG];
	
	/**
	 * The extension of the input file, based on the end of file name (last three characters).
	 */
	public String ext = "";
	
	public CommandLineParser(String[] args) {
		this.Processing(args);
	}
	
	private void Processing(String[] args) {		
		if (args.length>0) {
			/**
			 * If the very first parameter is -help, a little help is coming for you (~about/welcome message).
			 */
			if (args[0].equals("-help")) {
				System.out.println(this.welcome);
				System.exit(0);
			}
			for (int i = 0; i < args.length; i++) {
				if (args[i].equals("-b")) if (i+1 < args.length) filein = args[i+1]; 				    		
				if (args[i].equals("-k")) if (i+1 < args.length) fileout = args[i+1];
				if (args[i].equals("-t")) {
				for (int j = 0; j < MAXARG; j++)
					if (i+1+j < args.length) transformation[j] = args[i+1+j];
				}
				if (args[i].equals("-e")) {
				for (int j = 0; j < MAXARG; j++)
					if (i+1+j < args.length) effect[j] = args[i+1+j];
				}		    	
			}
			/**
			 * It will get an error if the input file parameter doesn't exists.		   
			 */
			if (filein != null && filein.length() == 0) {
				System.out.println(this.error);
			    System.exit(-1);
			}
			/**
			 * It will get an error if the file doesn't exists.
			 */
			   	File imgFile = new File(filein);

			   	if (!imgFile.exists()) {
			   		System.out.println("File doesnt exists. (" + filein + ")");
			   		System.exit(-1);	    		
			   	}
			   	/**
			   	 * If the input file name is less than five character then it will warn the user that there could be some kind of error for eg.: abmp, abp are not correct file names and types.
			   	 */
			   	if (filein.length() < 5) {
			   		System.out.println("File format error.");
			   		System.exit(-1);
			   	}
			   	/**
			   	 * The extension of the input file which based on the last 3 character of the file name.
			   	 */
			   	ext = filein.substring(Math.max(0, filein.length() - 3));
			   	
			   	/**
			   	 * The default output name of the file.
			   	 */
			   	if (fileout != null && fileout.length() == 0) {
			   		/**
			   		 * !!Missing exception: for eg.: file something will be someth_newing which is not correct
			   		 * 						what if the file is already exist?
			   		 */
			   		fileout = filein.substring(0, filein.length() - 4) + "_new." + ext;	    		
			   	}
			}
			/**
			 * Shows the default welcome page if there's no any other parameter.
			 */
			else System.out.println(this.error);
		}

	public String toString() {
		String out = "";
		out = "Input file: " + "\t\t" + this.filein +
			  "Output file: " + "\t\t" + this.fileout + "\n";
		for (int i = 0; i < transformation.length; i++) {
			out += "Trans["+ i+1 + "\t\t" + this.transformation[i] + "\n";
		}
		for (int i = 0; i < effect.length; i++) {
			out += "Effect["+ i+1 + "\t\t" + this.effect[i] + "\n";
		}		
		return out;
	}
	
}