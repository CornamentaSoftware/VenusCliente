/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Util.Hilo;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Alumno
 */
public class Solicita extends HttpServlet {
 
    protected void doPost(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        String usu = sesion.getAttribute("usuario").toString();
        String mensaje = request.getParameter("Servicio");
        String respuesta = "";
        try{
        switch(mensaje){
            case "Registro":
                resp.sendRedirect("http://localhost:8084/VenusProject/Perfil/altas.jsp");
                break;
            case "Ingreso": 
                
                break;
            case "ModificarD":
                respuesta=mensaje(mensaje, "192.168.9.144", 5000);
                if(respuesta.equals("ModificarD")){
                resp.sendRedirect("http://localhost:8084/VenusProject/Perfil/ModificarDatos.html");
                }else
                    resp.sendRedirect("http://localhost:8080/VenusProject/Plantillas/Error.html");
                break;
            default: 
                
                break;
        }
        }
        catch(Exception e){}
    }
    
    private String mensaje(String mensaje, String address, int puerto) throws Exception{
        Hilo hilo = new Hilo(mensaje,address,puerto);
        Thread h = new Thread(hilo);
        h.start();
        String msj="";
        msj = hilo.parar();
        return msj;
    }
    
    public String GetIP() {
        String ip="";
        InetAddress address;
            try {
                address = InetAddress.getLocalHost();
                ip=("IP Local :"+address.getHostAddress());
            } catch (UnknownHostException ex) {
            }
        return ip;
    }
}
