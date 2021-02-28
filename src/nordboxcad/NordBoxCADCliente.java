/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nordboxcad;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author Alejandro Campos Maestre
 */
public class NordBoxCADCliente
{

    static String IP = null;
    static Integer puerto = null;
    Socket socketCliente;

    public NordBoxCADCliente()
    {
        try
        {
            socketCliente = new Socket("127.0.0.1", 30501);
        } catch (IOException ex)
        {
            Logger.getLogger(NordBoxCADCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public NordBoxCADCliente(String IP, Integer puerto)
    {
        try
        {
            NordBoxCADCliente.IP = IP;
            NordBoxCADCliente.puerto = puerto;

            socketCliente = new Socket(IP, puerto);
        } catch (IOException ex)
        {
            Logger.getLogger(NordBoxCADCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int crearUsuario(Usuario usuario) throws ExcepcionNordBox
    {
        int resultado = 0;

        try
        {
            DataOutputStream envioData = new DataOutputStream(socketCliente.getOutputStream());
            envioData.writeUTF("crearUsuario");

            ObjectOutputStream envioObject = new ObjectOutputStream(socketCliente.getOutputStream());

            envioObject.writeObject(usuario);

            ObjectInputStream recepcionObject = new ObjectInputStream(socketCliente.getInputStream());

            resultado = (int) recepcionObject.readObject();

        } catch (IOException ex)
        {
            Logger.getLogger(NordBoxCADCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(NordBoxCADCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultado;
    }

    public int insertarEjerciciosBench(EjerciciosBench bench)
    {
        int resultado = 0;
        try
        {
            DataOutputStream envioData = new DataOutputStream(socketCliente.getOutputStream());
            envioData.writeUTF("insertarEjerciciosBench");
//            System.out.println("Objeto");

            ObjectOutputStream envioObject = new ObjectOutputStream(socketCliente.getOutputStream());
            envioObject.writeObject(bench);
//            System.out.println("Recepcion");

            ObjectInputStream recepcionObject = new ObjectInputStream(socketCliente.getInputStream());
            resultado = (int) recepcionObject.readObject();
        } catch (IOException ex)
        {
            Logger.getLogger(NordBoxCADCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(NordBoxCADCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    /**
     * Busca en la base de datos el correo y comprueba que la contraseña
     * cifrada, concuerde con la que el usuario proporciona.
     *
     * @param usuario
     * @return
     */
    public Usuario comprobarLogin(Usuario usuario)
    {
        Usuario u = null;
        try
        {
            DataOutputStream envioData = new DataOutputStream(socketCliente.getOutputStream());
            envioData.writeUTF("comprobarLogin");

            ObjectOutputStream envioObject = new ObjectOutputStream(socketCliente.getOutputStream());
            envioObject.writeObject(usuario);

            ObjectInputStream recepcionObject = new ObjectInputStream(socketCliente.getInputStream());
            u = (Usuario) recepcionObject.readObject();

        } catch (IOException | ClassNotFoundException ex)
        {
            Logger.getLogger(NordBoxCADCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        return u;
    }

    public Usuario buscarUsuarioID(Integer id)
    {
        Usuario u = null;

        return u;
    }

    public Usuario buscarUsuarioCorreo(String correo)
    {
        Usuario u = null;

        return u;
    }

    
    public int modificarUsuarioNoPass(Usuario usuario)
    {
        int resultado = 0;

        try
        {
            DataOutputStream envioData = new DataOutputStream(socketCliente.getOutputStream());
            envioData.writeUTF("modificarUsuarioNoPass");

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socketCliente.getOutputStream());
            objectOutputStream.writeObject(usuario);
            //TODO terminar de enviar archivo
            if(usuario.getImg() != null)
                enviarArchivo(usuario);

            ObjectInputStream objectInputStream = new ObjectInputStream(socketCliente.getInputStream());
            System.out.println("Espera de resultado int");
            resultado = (int) objectInputStream.readObject();
        } catch (IOException ex)
        {
            Logger.getLogger(NordBoxCADCliente.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(NordBoxCADCliente.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }

        try
        {
            socketCliente.close();
        } catch (IOException ex)
        {
            Logger.getLogger(NordBoxCADCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    /**
     * Creacion de un registro en la tabla ejercicioBenchUsuario
     *
     * @param idUsuario
     * @param idEjercicio
     * @param peso
     */
    public void crearEjeBench(Integer idUsuario, Integer idEjercicio, Integer peso)
    {

    }

    public ArrayList<EjerciciosBench> ejeBench()
    {
        ArrayList<EjerciciosBench> arrayList = new ArrayList<>();

        try
        {
            DataOutputStream envioData = new DataOutputStream(socketCliente.getOutputStream());

            envioData.writeUTF("ejeBench");

            ObjectInputStream recepcionObject = new ObjectInputStream(socketCliente.getInputStream());

            arrayList = (ArrayList<EjerciciosBench>) recepcionObject.readObject();
        } catch (IOException ex)
        {
            Logger.getLogger(NordBoxCADCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(NordBoxCADCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arrayList;
    }

    public ArrayList<EjercicioBenchUsuario> ejeBenchUsuario(Integer idUsuario, Integer idEjercicio)
    {
        ArrayList<EjercicioBenchUsuario> benchUsuarios = null;

        return benchUsuarios;
    }

    private void enviarArchivo(Usuario usuario)
    {        
        BufferedInputStream bis;
        BufferedOutputStream bos;
        int in;
        byte[] byteArray;
        //Fichero a transferir
        final String filename = usuario.getImg().getAbsolutePath();

        try
        {
            final File localFile = new File(filename);
            
            bis = new BufferedInputStream(new FileInputStream(localFile));
            bos = new BufferedOutputStream(socketCliente.getOutputStream());
            
            //Enviamos el fichero
            byteArray = new byte[8192];
            while ((in = bis.read(byteArray)) > 0)
            {
                bos.write(byteArray, 0, in);
                bos.flush();
            }
            System.out.println("Salida de enviar foto");

        } catch (Exception e)
        {
            System.out.println(e);
        }
    }
}