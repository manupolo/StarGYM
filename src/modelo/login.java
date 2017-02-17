/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author diego
 */
public class login extends conexion implements interfazLogin {
    
    @Override
    public int iniciarSesion(String Nombre, String Contraseña) {
        
        String nombre= "";
        String contraseña = "";
        int admin = 0;
        int comp= 2;
        
        //consulta sql
        try {
            String q = "SELECT * FROM empleado ";
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
                        
            while (res.next()) {
                
                //recorre uno a uno el Nombre y la Contraseña de cada Medico
                nombre = res.getString("idEmpleado");
                contraseña = res.getString("clave");
                admin = res.getInt("admin");
                //Si coincide los datos puesto con los datos de la base de datos
                //puedes entrar al menu
                
                if(nombre.equals(Nombre) && contraseña.equals(Contraseña) && admin == 1){

                    comp = 1;

                }else if(nombre.equals(Nombre) && contraseña.equals(Contraseña) && admin == 0){
                    comp=0;
                   

                }
                    
                System.out.println("Pulsando entrar - " + "Comprobacion: " + comp);    
            }
            res.close();
        } catch (SQLException e) {
            
            e.printStackTrace();
        } 
        return comp;
    }
    
}
