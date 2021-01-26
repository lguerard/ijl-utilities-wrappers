package ch.epfl.biop.wrappers.transformix;

import java.util.ArrayList;

public class DefaultTransformixTask extends TransformixTask {

    public void run() throws Exception {
        ArrayList<String> options = new ArrayList<>();
        if (!settings.imagePathSupplier.get().equals("")) {
            options.add("-in");options.add(settings.imagePathSupplier.get());
        }
        if (!settings.inputPtsFileSupplier.get().equals("")) {
            options.add("-def");options.add(settings.inputPtsFileSupplier.get());
        }
        options.add("-out");options.add(settings.outputFolderSupplier.get());
        options.add("-tp");options.add(settings.transformFileSupplier.get());
        //try {

        Transformix.execute(options, null);
		//} catch (IOException | InterruptedException e) {
		//	e.printStackTrace();
		//}
    }
}
