/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author diego
 */
public interface interfazMonitor {
    public DefaultTableModel listarMonitores();
    public boolean añadirMonitor(String idMonitor, String nombre, String apellidos, int telefono, String correo);
    public boolean eliminarMonitor(String idMonitor2);
    
}
