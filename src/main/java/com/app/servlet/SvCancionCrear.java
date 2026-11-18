
package com.app.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.app.logica.Cancion;
import com.app.logica.ControladoraLogica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SvCancionCrear")
public class SvCancionCrear extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ControladoraLogica controladora = new ControladoraLogica();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            String titulo = request.getParameter("titulo");
            float duracion = Float.parseFloat(
                request.getParameter("duracion")
            );
            int anio = Integer.parseInt(request.getParameter("anio"));

            Cancion cancion = new Cancion(titulo, duracion, anio);
            controladora.crearCancion(cancion);

            response.sendRedirect(
                request.getContextPath() + "/SvCancionListar"
            );

        } catch (NumberFormatException e) {
        	
        	String msg = URLEncoder.encode("La duracion debe ser un número", StandardCharsets.UTF_8);
        	response.sendRedirect("SvArtistaListar?error=" + msg);
        	} catch (Exception e) {
        	String msg = URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
        	response.sendRedirect("SvArtistaListar?error=" + msg);
        	};
        }
    }

