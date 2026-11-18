package com.app.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.app.logica.Artista;
import com.app.logica.ControladoraLogica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SvArtistaEditar")
public class SvArtistaEditar extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ControladoraLogica controladora = new ControladoraLogica();

    private void volverConError(HttpServletRequest request,
                                HttpServletResponse response,
                                String mensaje) throws IOException {
        response.sendRedirect(request.getContextPath()
            + "/SvArtistaListar?error="
            + URLEncoder.encode(mensaje, StandardCharsets.UTF_8));
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Artista artista = controladora.buscarUnArtista(id);

            if (artista == null) {
                volverConError(request, response,
                    "No existe un artista con ese ID.");
                return;
            }

            request.setAttribute("artista", artista);
            request.getRequestDispatcher(
                "/WEB-INF/vistas/editarArtista.jsp"
            ).forward(request, response);

        } catch (Exception e) {
            volverConError(request, response,
                e.getMessage() == null
                    ? "No se pudo buscar el artista."
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

            Artista artista = controladora.buscarUnArtista(id);

            if (artista == null) {
                volverConError(request, response,
                    "No existe un artista con ese ID.");
                return;
            }

            artista.setNombre(request.getParameter("nombre"));
            artista.setGeneroMusical(
                request.getParameter("generoMusical"));
            artista.setNacionalidad(
                request.getParameter("nacionalidad"));
            artista.setEdad(
                Integer.parseInt(request.getParameter("edad")));

            controladora.editarArtista(artista);

            response.sendRedirect(
                request.getContextPath() + "/SvArtistaListar");

        } catch (Exception e) {
            volverConError(request, response,
                e.getMessage() == null
                    ? "Error al editar el artista."
                    : e.getMessage());
        }
    }
}