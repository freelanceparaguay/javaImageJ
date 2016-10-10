
package variaspruebasimagej;

import ij.IJ;
import ij.ImagePlus;
import ij.io.Opener;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

/* 
 Secuencia para abrir imagenes tanto desde archivos como desde urls
*/
public class EjemploAbrirImagenes implements PlugInFilter{

    @Override
    public int setup(String string, ImagePlus ip) {
        return PlugInFilter.DOES_ALL;
    }

    @Override
    public void run(ImageProcessor ip) {

        //-----------------------------------------
        // abrir un archivo de imagen desde directorio
        //-----------------------------------------
        Opener opener = new Opener();                
        String directorioActual = System.getProperty("user.dir"); //estableciendo el directorio del proyecto
        //String imageFilePath = directorioActual+"/cebra.png";
        String imageFilePath = directorioActual+"/barco.jpg";
        ImagePlus imp1 = opener.openImage(imageFilePath); //abrir primera imagen segun ruta
        ImagePlus imp2 = opener.openImage(imageFilePath); //abrir segunda imagen segun ruta
        ImageProcessor ip2 = imp2.getProcessor(); // preparar imagen para manipulacion

        imp2.setTitle("Primera imagen desde disco");
        imp2.show(); //mostrar imagen

        //-----------------------------------------

        //-----------------------------------------
        // abrir un archivo de imagen desde url
        //-----------------------------------------
        ImagePlus img3= IJ.openImage("http://imagej.nih.gov/ij/images/blobs.gif");        
        imp2.setTitle("Segunda imagen desde http://imagej.nih.gov/ij/images/blobs.gif");
        img3.show(); //mostrar imagen

        //------------------------------------
        
        
    } //run 
}//fin clase My_inverter
