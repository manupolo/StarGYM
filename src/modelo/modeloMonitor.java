/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author diego
 */
public class modeloMonitor extends conexion implements interfazMonitor {
    
    
    @Override
    public DefaultTableModel listarMonitores()
    {
        DefaultTableModel tablemodel = new DefaultTableModel();
    
      int registros = 0;
      String[] columNames = {"DNI", "Nombre", "Apellidos", "Teléfono", "Correo"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
       CallableStatement cstmt = this.getConexion().prepareCall("{call numeroMonitores}");
        ResultSet res = cstmt.executeQuery();
         res.next();
         registros = res.getInt("todo");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
    //se crea una matriz con tantas filas y columnas que necesite
     Object[][] data = new String[registros][6];
     try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
        CallableStatement cstmt = this.getConexion().prepareCall("{call listarMonitores}");
        ResultSet res = cstmt.executeQuery();
         int i=0;
         while(res.next()){
                
                data[i][0] = res.getString("idMonitor");
                data[i][1] = res.getString("nombre");
                data[i][2] = res.getString("apellidos");
                data[i][3] = res.getString("telefono");
                data[i][4] = res.getString("correo");
                     
            i++;
         }
         res.close();
        //se añade la matriz de datos en el DefaultTableModel
         tablemodel.setDataVector(data, columNames );
        }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablemodel;
        
    }
    
    @Override
    public boolean añadirMonitor(String idMonitor, String nombre, String apellidos, int telefono, String correo){
     boolean res=false;
        
        try {
            CallableStatement cstm = this.getConexion().prepareCall("{call añadirMonitor(?,?,?,?,?)}");
            
            cstm.setString(1, idMonitor);
            cstm.setString(2, nombre);
            cstm.setString(3, apellidos);
            cstm.setInt(4, telefono);
            cstm.setString(5, correo);
            cstm.executeUpdate();
            
            cstm.close();
            res=true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage() + "     \n  " + ex.getSQLState());
        }
        return res;
        }
    
    @Override
    public boolean eliminarMonitor(String idMonitor2){
        boolean res=false;
        try {
            //Preparamos la funcion que va a ejecutar la eliminacion
            CallableStatement cstm = this.getConexion().prepareCall("{call eliminarMonitor(?)}");
            //Indicas el tipo de dato que devuelve
            //Indicas el parametro que le pasas, en este caso el codigo del bar y el dni
            cstm.setString(1, idMonitor2);
            //Ejecutas la funcion
            cstm.executeUpdate();
             //Recoges el resultado
            cstm.close();
            res=true;
            
            
        } catch (Exception e) {
        }
        return res;
    }
    @Override
    public boolean modificarMonitor(String idMonitor, String nombre, String apellidos, int telefono, String correo){
        boolean res=false;
        try {
            CallableStatement cstm = this.getConexion().prepareCall("{call modificarMonitor(?,?,?,?,?)}");
            
            cstm.setString(1, idMonitor);
            cstm.setString(2, nombre);
            cstm.setString(3, apellidos);
            cstm.setInt(4, telefono);
            cstm.setString(5, correo);
          
            
            cstm.executeUpdate();
            
            cstm.close();
            res=true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage() + "     \n  " + ex.getSQLState());
        }
        return res;
    }
    }

