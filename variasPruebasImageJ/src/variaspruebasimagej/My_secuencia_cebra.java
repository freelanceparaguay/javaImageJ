package variaspruebasimagej;

import ij.*;
import ij.process.*;
import ij.gui.*;
import ij.io.Opener;
import java.awt.*;
import ij.plugin.*;

/* 
 Octubre 2016
 http://otroblogdetecnologias.blogspot.com/

 -------------------------------------
 PRUEBAS DE SECUENCIAS DE IMAGENES
 -------------------------------------
 Ejercicio a partir de una imagen original de una cebra, obtener la cebra
 sin el fondo.



    A partir de una imagen original establece la secuencia de pasos para obtener el 
    contorno de la cebra quitando las lineas verticales.
    Se aplican operaciones para lograr un procesamiento de imágenes.
*/

public class My_secuencia_cebra implements PlugIn {

	public void run(String arg) {
                //apertura de imagen
                Opener opener = new Opener();                
                String directorioActualDefault = System.getProperty("user.dir"); //estableciendo el directorio del proyecto
                String imageFilePathDefault = directorioActualDefault+"/Cebra.png";
                ImagePlus Iorig= opener.openImage(imageFilePathDefault);            
            
	//	ImagePlus Iorig = IJ.openImage("/home/usuario/materialMaestria/2016trabajoTesisMaestria/javaCodigo/variasPruebasImageJ/Cebra.png");
                ImagePlus IB2 = Iorig.crop();
		Prefs.blackBackground = false;
		IJ.run(IB2, "Make Binary", ""); //convertir a imagen binaria

                ImagePlus IB3 = IB2.crop();
		IJ.run(IB3, "Options...", "iterations=1 count=1 do=Erode"); //erosionar

                ImagePlus IB4 = IB2.crop();
		IJ.run(IB4, "Options...", "iterations=1 count=2 do=Erode"); //erosionar con mayor iteraciones
		
                
                ImageCalculator ic = new ImageCalculator();

		ImagePlus IB5 = ic.run("XOR create", IB3, IB4);//operacion de calculo XOR entre imagenes

                //--- Mostrar imagenes ---
		Iorig.show();Iorig.setTitle("1) Original Gris");                
                IB2.show(); IB2.setTitle("2) Binarización");
                IB3.show(); IB3.setTitle("3) Erosión iteración=1 contador=1");               
		IB4.show(); IB4.setTitle("4) Erosión iteración=1 contador=2");                                               
		IB5.show(); IB5.setTitle("3) XOR 4)");

	}//run

}//fin clase
