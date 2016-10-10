/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package variaspruebasimagej;

/*
import ij.IJ;
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;
*/

/* 
    Ejemplo de clase para correr comando.
    Recibe una imagen y realiza una serie de operaciones guardando un resultado
    final.
*/


import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.*;

public class EjemploSecuenciaComandos implements PlugIn {
	public void run(String arg) {
		ImagePlus imp = IJ.openImage("/home/usuario/materialMaestria/2016trabajoTesisMaestria/javaCodigo/variasPruebasImageJ/Cebra.png");
		imp = new Duplicator().run(imp);
		IJ.run(imp, "Make Binary", "");
		imp.close();
		IJ.run("Close");
	}

}//EjemploSecuenciaComandos