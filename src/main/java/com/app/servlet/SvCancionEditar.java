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

@WebServlet("/SvCancionEditar")
public class SvCancionEditar extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ControladoraLogica controladora = new ControladoraLogica();

    private void volverConError(HttpServletRequest request,
                                HttpServletResponse response,
                                String mensaje) throws IOException {
        response.sendRedirect(request.getContextPath()
            + "/SvCancionListar?error="
            + URLEncoder.encode(mensaje, StandardCharsets.UTF_8));
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Cancion cancion = controladora.buscarUnaCancion(id);

            if (cancion == null) {
                volverConError(request, response,
                    "No existe una canción con ese ID.");
                return;
            }

            request.setAttribute("cancion", cancion);
            request.getRequestDispatcher(
                "/WEB-INF/vistas/editarCancion.jsp"
            ).forward(request, response);

        } catch (Exception e) {
            volverConError(request, response,
                e.getMessage() == null
                    ? "No se pudo buscar la canción."
                    : e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            Cancion cancion = controladora.buscarUnaCancion(id);

            if (cancion == null) {
                volverConError(request, response,
                    "No existe una canción con ese ID.");
                return;
            }

            cancion.setTitulo(request.getParameter("titulo"));
            cancion.setDuracion(
                Float.parseFloat(request.getParameter("duracion")));
            cancion.setAnio(
                Integer.parseInt(request.getParameter("anio")));

            controladora.editarCancion(cancion);

            response.sendRedirect(
                request.getContextPath() + "/SvCancionListar");

        } catch (Exception e) {
            volverConError(request, response,
                e.getMessage() == null
                    ? "Error al editar la canción."
                    : e.getMessage());
        }
    }
}