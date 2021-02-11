/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nordboxcad;

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
        
        
        
        Usuario usuario = new Usuario(null, "aa@aa.aa", "kk", "nombre", "apellido", "2Apellido", "134654", "5664", 123, "sad", "asdda");
        
        try
        {
            boxCAD.crearUsuario(usuario);
//        EjerciciosBench bench = new EjerciciosBench(null, "pruebaSocket", 2);
//        int becnhComprobacion;
//
//        
//        becnhComprobacion = boxCAD.insertarEjerciciosBench(bench);
//        
//        System.out.println(becnhComprobacion);
        } catch (ExcepcionNordBox ex)
        {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
