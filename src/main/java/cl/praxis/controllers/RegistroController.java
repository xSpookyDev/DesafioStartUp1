package cl.praxis.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.praxis.models.dao.UsuarioDAO;
import cl.praxis.models.dao.UsuarioDAOImpl;
import cl.praxis.models.dto.Usuario;


@WebServlet("/registro")
public class RegistroController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/registro.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String correo = request.getParameter("correo");
        String nick = request.getParameter("nick");
        String nombre = request.getParameter("nombre");
        String password = request.getParameter("password");
        String pesoStr = request.getParameter("peso");

        float peso = pesoStr.isEmpty() ? 0 : Float.parseFloat(pesoStr);

        Usuario usuario = new Usuario();
        usuario.setCorreo(correo);
        usuario.setNick(nick);
        usuario.setNombre(nombre);
        usuario.setPassword(password);
        usuario.setPeso(peso);

        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        try {
            if (!usuarioDAO.usuarioExists(correo)) {
                boolean added = usuarioDAO.addUsuario(usuario);
                if (added) {
                    response.sendRedirect(request.getContextPath()+"/views/index.jsp");
                } else {
                    response.getWriter().println("Error al registrar usuario.");
                }
            } else {
                response.getWriter().println("Usuario ya registrado con este correo.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error al registrar usuario.");
        }
    }
    }

