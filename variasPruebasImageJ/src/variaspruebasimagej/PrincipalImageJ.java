package variaspruebasimagej;


import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.io.Opener;
import ij.process.ImageProcessor;
import ij.IJ;

/*

 Octubre 2016
 http://otroblogdetecnologias.blogspot.com/

 -------------------------------------
 PRUEBAS DE SECUENCIAS DE IMAGENES
 -------------------------------------
 Este es el programa principal, la lógica que se plantea es la de poder explicar
 con ejemplos las funcionalidades básicas. Los ejemplos se estructuran en plugins
 En el programa principal se agrupan varios ejemplos.
*/
public class PrincipalImageJ {

    public static void main(String[] args) {
        //-----------------------------------
        // Declaracion de Plugins
        //-----------------------------------
        My_Inverter mi = new My_Inverter(); // ejemplo invertir imagen bit a bit
        EjemploAbrirImagenes ej1= new EjemploAbrirImagenes(); //ejemplo abrir imagenes
        EjemploCorrerComando ej2=new EjemploCorrerComando(); //plugin correr comandos
        My_secuencia_cebra ej3=new My_secuencia_cebra();
        
        //-----------------------------------------
        // Declaracion de variables iniciales
        //-----------------------------------------
        Opener opener = new Opener();                
        String directorioActualDefault = System.getProperty("user.dir"); //estableciendo el directorio del proyecto
        //String imageFilePathDefault = directorioActualDefault+"/barco.jpg";        
        String imageFilePathDefault = directorioActualDefault+"/Cebra.png";
        ImagePlus imagenDefault= opener.openImage(imageFilePathDefault);
        ImageProcessor imagenPDefault= imagenDefault.getProcessor();
        
        

        //-----------------------------------------
        // Llamado a ejemplos, los ejemplos pueden comentarse
        // o descomentarse según la necesidad, todos forman 
        // codigos independientes, para mantener legibilidad
        //-----------------------------------------
//        ej1.run(imagenPDefault); //ejemplo apertura de archivos


        //------------------------------------
        // correr comandos
        //EjemploCorrerComando comando=new EjemploCorrerComando(); //crear clase con scripts
//        imagenDefault.show(); //mostrar primera imagen
//        ej2.run(imagenPDefault); //ejemplo correr comando
//        imagenDefault.updateAndDraw(); //update imagen
//        imagenDefault.show(); //mostrar resultado de la imagen
        // -----------------------------------

        ej3.run("");

//        IJ.showMessage("Hello, World!");                
        //-----------------------------------------
        //funciona corriendo un plugin
        // ----------------------------------------
        //imp1.show();
        //mi.run(ip2); //llamada al comando
        //imp2.updateAndDraw();
        //imp2.show();                    
        
        //------------------------------------
        // llamado a ejercicios        
//        Ejercicio_3_2 ejercicio32=new Ejercicio_3_2();
//        ejercicio32.run(ip);        
        //-----------------------------------
    }
    
}
