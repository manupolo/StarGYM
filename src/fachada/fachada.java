/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import clases.Administrador;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import modelo.interfazAdministrador;
import modelo.interfazClase;
import modelo.interfazCliente;
import modelo.interfazMaquina;
import modelo.modeloAdministrador;
import modelo.modeloClase;
import modelo.modeloCliente;
import modelo.modeloMaquina;

/**
 *
 * @author diego
 */
public class fachada {
    
    //--------Clientes---------------//
    
    private interfazCliente cliente = new modeloCliente();
    
    
    public DefaultListModel listClases(String dni){
        return cliente.listClases(dni);
    }
    
    public DefaultTableModel listarClientes(){
         return cliente.listarClientes();
     }
     
    public boolean añadirCliente(String dni, String nombre, String apellidos, Date fechaNacimiento, String direccion, int codPostal, String ciudad, int telefono, String correo){
        return cliente.añadirCliente(dni, nombre, apellidos, fechaNacimiento, direccion, codPostal, ciudad, telefono, correo);
     }
    public boolean eliminarCliente(String dni){
         return cliente.eliminarCliente(dni);
     }
     
     //-------Aministradores---------//
     
    private interfazAdministrador admin = new modeloAdministrador();
     
    public DefaultListModel listAdmin() {
         return admin.listAdmin();
     }
    public Administrador datosAdmin(String idEmpleado){
         return admin.datosAdmin(idEmpleado);
     }
     
     
     //----------clases-------------//
     
    private interfazClase clase = new modeloClase();
     
    public DefaultTableModel listarClases(){
         return clase.listarClases();
     }
     
     //---------maquinas--------------//
     
     private interfazMaquina maquina = new modeloMaquina();
     
     public DefaultTableModel listarMaquinas(){
         return maquina.listarMaquinas();
     }
     
    
    
    
}
