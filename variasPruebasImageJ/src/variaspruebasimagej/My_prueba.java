package variaspruebasimagej;

import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.*;

public class My_prueba implements PlugIn {

	public void run(String arg) {

            //Imagen original
                ImagePlus Iorig = IJ.openImage("/home/usuario/materialMaestria/2016trabajoTesisMaestria/javaCodigo/variasPruebasImageJ/Cebra.png");
		Iorig.show();		
                
                //binarizar
                //nivel                
                ImagePlus IB2 = new Duplicator().run(Iorig);
		Prefs.blackBackground = true;
		IJ.run(IB2, "Make Binary", "");
		//IJ.run(IB2, "Invert LUT", "");
                IB2.show();
                
                //Erosion
                //elemento estructurante
                ImagePlus IB3 = new Duplicator().run(IB2);                
                
                
                
                
		//IJ.run(imp2, "Skeletonize", "");
		//imp2.show();
	}//run

}
