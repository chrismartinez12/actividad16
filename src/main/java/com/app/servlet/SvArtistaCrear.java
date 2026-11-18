
package com.app.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import com.app.logica.Artista;
import com.app.logica.ControladoraLogica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SvArtistaCrear")
public class SvArtistaCrear extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ControladoraLogica controladora = new ControladoraLogica();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            String nombre = request.getParameter("nombre");
            String genero = request.getParameter("generoMusical");
            String nacionalidad = request.getParameter("nacionalidad");
            int edad = Integer.parseInt(request.getParameter("edad"));

            Artista artista = new Artista(
                nombre, genero, nacionalidad, edad, new Date()
            );

            controladora.crearArtista(artista);

            response.sendRedirect(
                request.getContextPath() + "/SvArtistaListar"
            );

        } catch (NumberFormatException e) {
            String msg = URLEncoder.encode(
                "La edad debe ser un número",
                StandardCharsets.UTF_8
            );

            response.sendRedirect(
                request.getContextPath() + "/SvArtistaListar?error=" + msg
            );

        } catch (Exception e) {
            String mensaje = e.getMessage() == null
                ? "Error al crear artista"
                : e.getMessage();

            String msg = URLEncoder.encode(
                mensaje,
                StandardCharsets.UTF_8
            );

            response.sendRedirect(
                request.getContextPath() + "/SvArtistaListar?error=" + msg
            );
        }
    }
}
