/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nordboxcad;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejandro Campos Maestre
 */
public class NewMain
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {      
        NordBoxCADCliente boxCAD = new NordBoxCADCliente();
        
        File origen = new File("C:\\Users\\alex_\\Desktop\\Avatares\\prueba1.png");
       
        Usuario usuario = new Usuario(3, "aa@aa.aa", null ,"nombre", "apellido", "2Apellido", "134654", "5664", 123, "sad", "asdda", origen);
        
        int resultado = boxCAD.modificarUsuarioNoPass(usuario);
        
        if(resultado>0)
        {
            System.out.println("Exito");
        } else
        {
            System.out.println("Fallo");
        }
    }
}
