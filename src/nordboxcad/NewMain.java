/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nordboxcad;

import java.io.File;

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
        
        File origen = new File("C:\\Users\\alex_\\Desktop\\Avatares\\prueba1.jpg");
       
        Usuario usuario = new Usuario(3, "aa@aa.aa", null ,"nombre1", "apellido", "2Apellido", "134654", "5664", 123, "sad", "asdda", origen.getAbsolutePath());
        System.out.println("Comienza la modificacion");
        int resultado = boxCAD.modificarUsuarioNoPass(usuario);
        System.out.println("Termina");
        if(resultado>0)
        {
            System.out.println("Exito");
        } else
        {
            System.out.println("Fallo");
        }
    }
}
