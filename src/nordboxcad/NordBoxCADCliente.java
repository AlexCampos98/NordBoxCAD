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
            
//            capturarArchivo(u);

        } catch (IOException | ClassNotFoundException ex)
        {
            Logger.getLogger(NordBoxCADCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        return u;
    }

    public Usuario buscarUsuarioID(Usuario usuario)
    {
        Usuario u = null;
        try
        {
            DataOutputStream envioData = new DataOutputStream(socketCliente.getOutputStream());
            envioData.writeUTF("buscarUsuarioID");

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
    
    public int modificarUsuarioPass(Usuario usuario)
    {
        int resultado = 0;

        try
        {
            DataOutputStream envioData = new DataOutputStream(socketCliente.getOutputStream());
            envioData.writeUTF("modificarUsuarioPass");

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
     * @param apuntoEjercicio
     */
    public void crearEjeBench(ApuntoEjercicio apuntoEjercicio)
    {
        try
        {
            DataOutputStream envioData = new DataOutputStream(socketCliente.getOutputStream());
            envioData.writeUTF("crearEjeBench");
            envioData.flush();

            ObjectOutputStream envioObject = new ObjectOutputStream(socketCliente.getOutputStream());

            envioObject.writeObject(apuntoEjercicio);
        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
            Logger.getLogger(NordBoxCADCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    public void crearEvento(Evento evento)
    {
        try
        {
            DataOutputStream envioData = new DataOutputStream(socketCliente.getOutputStream());
            envioData.writeUTF("crearEvento");

            ObjectOutputStream envioObject = new ObjectOutputStream(socketCliente.getOutputStream());

            envioObject.writeObject(evento);
        } catch (IOException ex)
        {
            Logger.getLogger(NordBoxCADCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Evento> obtenerEventosFecha(Evento evento)
    {
        ArrayList<Evento> arrayList = new ArrayList<>();

        try
        {
            DataOutputStream envioData = new DataOutputStream(socketCliente.getOutputStream());
            envioData.writeUTF("obtenerEventosFecha");

            ObjectOutputStream envioObject = new ObjectOutputStream(socketCliente.getOutputStream());
            envioObject.writeObject(evento);

            ObjectInputStream recepcionObject = new ObjectInputStream(socketCliente.getInputStream());
            arrayList = (ArrayList<Evento>) recepcionObject.readObject();

        } catch (IOException | ClassNotFoundException ex)
        {
            Logger.getLogger(NordBoxCADCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arrayList;
    }
    
    public void apuntarseEvento(Integer idUsuario, Integer idEvento)
    {
        try
        {
            DataOutputStream envioData = new DataOutputStream(socketCliente.getOutputStream());
            envioData.writeUTF("apuntarseEvento");

            DataOutputStream dataOutputStream = new DataOutputStream(socketCliente.getOutputStream());

            dataOutputStream.writeInt(idUsuario);
            dataOutputStream.writeInt(idEvento);
        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
            Logger.getLogger(NordBoxCADCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void desapuntarseEvento(Integer idUsuario, Integer idEvento)
    {
        try
        {
            DataOutputStream envioData = new DataOutputStream(socketCliente.getOutputStream());
            envioData.writeUTF("desapuntarseEvento");

            DataOutputStream dataOutputStream = new DataOutputStream(socketCliente.getOutputStream());

            dataOutputStream.writeInt(idUsuario);
            dataOutputStream.writeInt(idEvento);
        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
            Logger.getLogger(NordBoxCADCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<EjercicioBenchUsuario> ejeBenchUsuario(EjercicioBenchUsuario benchUsuario)
    {
        ArrayList<EjercicioBenchUsuario> benchUsuarios = new ArrayList<>();

        try
        {
            DataOutputStream envioData = new DataOutputStream(socketCliente.getOutputStream());
            envioData.writeUTF("ejeBenchUsuario");

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socketCliente.getOutputStream());
            objectOutputStream.writeObject(benchUsuario);
            
            ObjectInputStream recepcionObject = new ObjectInputStream(socketCliente.getInputStream());

            benchUsuarios = (ArrayList<EjercicioBenchUsuario>) recepcionObject.readObject();
        } catch (IOException ex)
        {
            Logger.getLogger(NordBoxCADCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(NordBoxCADCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return benchUsuarios;
    }

    private void enviarArchivo(Usuario usuario)
    {        
        BufferedInputStream bis;
        BufferedOutputStream bos;
        int in;
        byte[] byteArray;
        //Fichero a transferir
        final String filename = usuario.getImg();

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
    
    private int capturarArchivo(Usuario usuario)
    {
        System.out.println("Dentro de capturar");
        BufferedInputStream bis;
        BufferedOutputStream bos;

        byte[] receivedData;
        int in;

        try
        {
            //Buffer de 1024 bytes
            receivedData = new byte[1024];
            bis = new BufferedInputStream(socketCliente.getInputStream());

            //Para guardar fichero recibido
            bos = new BufferedOutputStream(new FileOutputStream(getClass().getResource("/drawable/imgPerfil/" + usuario.getId() + ".jpg").getPath()));
            while ((in = bis.read(receivedData)) > 1023)
            {
                bos.write(receivedData, 0, in);
                bos.flush();
            }
            return 1;

        } catch (Exception e)
        {
            System.err.println(e);
            return 0;
        }
    }
    
    public int capturarArchivoPublico(File imgRuta)
    {
        System.out.println("Dentro de capturar");
        BufferedInputStream bis;
        BufferedOutputStream bos;

        byte[] receivedData;
        int in;

        try
        {
            //Buffer de 1024 bytes
            receivedData = new byte[8192];
            bis = new BufferedInputStream(socketCliente.getInputStream());

            //Para guardar fichero recibido
            bos = new BufferedOutputStream(new FileOutputStream(imgRuta.getAbsolutePath()));
            while ((in = bis.read(receivedData)) > 1023)
            {
                bos.write(receivedData, 0, in);
                bos.flush();
            }
            return 1;

        } catch (Exception e)
        {
            System.err.println(e);
            return 0;
        }
    }
}