/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author diego
 */
public class modeloClase extends conexion implements interfazClase {
    public DefaultTableModel listarClases()
    {
        DefaultTableModel tablemodel = new DefaultTableModel();
    
      int registros = 0;
      String[] columNames = {"Identificador", "Nombre", "Precio", "Monitor"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
       CallableStatement cstmt = this.getConexion().prepareCall("{call numeroClases}");
        ResultSet res = cstmt.executeQuery();
         res.next();
         registros = res.getInt("todo");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
    //se crea una matriz con tantas filas y columnas que necesite
     Object[][] data = new String[registros][4];
     try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
        CallableStatement cstmt = this.getConexion().prepareCall("{call listarClases}");
        ResultSet res = cstmt.executeQuery();
         int i=0;
         while(res.next()){
                
                data[i][0] = res.getString("idClase");
                data[i][1] = res.getString("nombre");
                data[i][2] = res.getString("precio");
                data[i][3] = res.getString("idMonitor");
                
                     
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
}
