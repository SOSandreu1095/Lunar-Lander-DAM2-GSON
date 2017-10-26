/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class getFileExc extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Get absolute path from root web path /WebContent/*
        ServletContext context = getServletContext();
        String fullPath = context.getRealPath("/data/config.json");

        Gson gson = new Gson();

        Type listType = new TypeToken<List<Configuracion>>() {
        }.getType();

        //Pasamos las configuraciones a una lista de objetos Configuracion
        List<Configuracion> listConf = gson.fromJson(new FileReader(fullPath), listType);

        //Si volem pasar-ho a string
        String jsonInString = gson.toJson(listConf); //a String

        response.setContentType("application/json");
        PrintWriter pw = response.getWriter();
        pw.println(jsonInString);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nombre = request.getParameter("nombre");
            String dif = request.getParameter("dificultad");
            String nav = request.getParameter("modeloNave");
            String lun = request.getParameter("modeloLuna");

            ServletContext context = getServletContext();
            String fullPath = context.getRealPath("/data/config.json");

            Configuracion c = new Configuracion();
            c.setNombre(nombre);
            c.setDificultad(dif);
            c.setModeloNave(nav);
            c.setModeloLuna(lun);

            Gson gson = new Gson();

            Type listType = new TypeToken<List<Configuracion>>() {
            }.getType();

            //Pasamos las configuraciones a una lista de objetos Configuracion
            List<Configuracion> listConf = gson.fromJson(new FileReader(fullPath), listType);
            
            listConf.add(c);
            System.out.println(gson.toJson(listConf));
            //gson.toJson(listConf, new FileWriter(fullPath)); //ESTO PETA
            File f = new File(fullPath);
            FileWriter fw = new FileWriter(f);
            fw.write(gson.toJson(listConf));
            fw.close();

            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println("{\"mess\":\"El fichero ha sido guardado correctamente\"}");

        } catch (Exception e) {
            System.out.println("ERROR: " + e.toString());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println("{\"error\":\"Ha sido imposible guardar los datos\"}");

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
