package ch.epfl.biop.wrappers.transformix;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import ij.Prefs;

public class Transformix {	
	
	public static String keyPrefix = Transformix.class.getName()+".";
	
	static String defaultExePath = "transformix";
	public static String exePath = Prefs.get(keyPrefix+"exePath",defaultExePath);//"/home/nico/Dropbox/BIOP/ABA/BrainServerTest/export.xml");

    public static void setExePath(File f) {
        exePath = f.getAbsolutePath().toString();
        Prefs.set(keyPrefix + "exePath", exePath);
    }
    
    public static void notifyIsInClassPath() {
    	exePath="transformix";
        Prefs.set(keyPrefix + "exePath", exePath);
    }

    private static File NULL_FILE = new File(
            (System.getProperty("os.name")
                    .startsWith("Windows") ? "NUL" : "/dev/null")
    );

    static void execute(List<String> options, boolean verbose)  throws IOException, InterruptedException {
            List<String> cmd = new ArrayList<>();
            cmd.add(exePath);
            cmd.addAll(options);
            ProcessBuilder pb = new ProcessBuilder(cmd);
            if (verbose) {
                pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            } else {
                pb.redirectOutput(NULL_FILE);
            }
            pb.redirectError(ProcessBuilder.Redirect.INHERIT);
            Process p = pb.start();
            p.waitFor();
    }
    
    public static void execute(String singleCommand) throws IOException, InterruptedException {
    	ArrayList<String> cmdList = new ArrayList<>();
    	cmdList.add(singleCommand);
    	execute(cmdList, false);
    }

}