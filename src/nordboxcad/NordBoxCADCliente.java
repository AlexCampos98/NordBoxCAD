/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nordboxcad;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
            ObjectOutputStream envioObject = new ObjectOutputStream(socketCliente.getOutputStream());
            ObjectInputStream recepcionObject = new ObjectInputStream(socketCliente.getInputStream());
            
            envioData.writeUTF("crearUsuario");
            
            envioObject.writeObject(usuario);
            
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
            ObjectOutputStream envioObject = new ObjectOutputStream(socketCliente.getOutputStream());
            ObjectInputStream recepcionObject = new ObjectInputStream(socketCliente.getInputStream());

            envioData.writeUTF("insertarEjerciciosBench");
            System.out.println("Objeto");
            envioObject.writeObject(bench);
            System.out.println("Recepcion");
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
            ObjectOutputStream envioObject = new ObjectOutputStream(socketCliente.getOutputStream());
            ObjectInputStream recepcionObject = new ObjectInputStream(socketCliente.getInputStream());

            envioData.writeUTF("comprobarLogin");
            envioObject.writeObject(usuario);

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

    /**
     * Creacion de un registro en la tabla ejercicioBenchUsuario
     *
     * @param idUsuario
     * @param idEjercicio
     * @param peso
     * @throws nordboxcad.ExcepcionNordBox
     */
    public void crearEjeBench(Integer idUsuario, Integer idEjercicio, Integer peso)
    {

    }

    public ArrayList<EjercicioBenchUsuario> ejeBenchUsuario(Integer idUsuario, Integer idEjercicio)
    {
        ArrayList<EjercicioBenchUsuario> benchUsuarios = null;

        return benchUsuarios;
    }

//    private static String generateStorngPasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException
//    {
//        int iterations = 1000;
//        char[] chars = password.toCharArray();
//        byte[] salt = getSalt();
//
//        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
//        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
//        byte[] hash = skf.generateSecret(spec).getEncoded();
//        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
//    }
//
//    private static byte[] getSalt() throws NoSuchAlgorithmException
//    {
//        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
//        byte[] salt = new byte[16];
//        sr.nextBytes(salt);
//        return salt;
//    }
//
//    private static String toHex(byte[] array) throws NoSuchAlgorithmException
//    {
//        BigInteger bi = new BigInteger(1, array);
//        String hex = bi.toString(16);
//        int paddingLength = (array.length * 2) - hex.length();
//        if (paddingLength > 0)
//        {
//            return String.format("%0" + paddingLength + "d", 0) + hex;
//        } else
//        {
//            return hex;
//        }
//    }
//
//    private static boolean validatePassword(String originalPassword, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException
//    {
//        String[] parts = storedPassword.split(":");
//        int iterations = Integer.parseInt(parts[0]);
//        byte[] salt = fromHex(parts[1]);
//        byte[] hash = fromHex(parts[2]);
//
//        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
//        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
//        byte[] testHash = skf.generateSecret(spec).getEncoded();
//
//        int diff = hash.length ^ testHash.length;
//        for (int i = 0; i < hash.length && i < testHash.length; i++)
//        {
//            diff |= hash[i] ^ testHash[i];
//        }
//        return diff == 0;
//    }
//
//    private static byte[] fromHex(String hex) throws NoSuchAlgorithmException
//    {
//        byte[] bytes = new byte[hex.length() / 2];
//        for (int i = 0; i < bytes.length; i++)
//        {
//            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
//        }
//        return bytes;
//    }
}
