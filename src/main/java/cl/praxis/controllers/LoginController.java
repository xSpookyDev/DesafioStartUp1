package cl.praxis.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.praxis.models.dao.UsuarioDAO;
import cl.praxis.models.dao.UsuarioDAOImpl;
import cl.praxis.models.dto.Usuario;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validación básica de entrada
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/views/login.jsp?error=empty");
            return;
        }

        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        try {
            Usuario usuario = usuarioDAO.getUsuarioByCorreoAndPassword(username, password);
            if (usuario != null) {
                request.getSession().setAttribute("usuario", usuario.getNombre());
                request.getSession().setAttribute("correo", usuario.getCorreo());
                System.out.println("Hasta aqui oK?");
                response.sendRedirect(request.getContextPath() + "/views/home.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/views/login.jsp?error=invalid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/views/login.jsp?error=db");
        }
    }

}
