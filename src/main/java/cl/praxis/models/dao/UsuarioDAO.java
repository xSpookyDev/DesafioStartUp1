package cl.praxis.models.dao;

import java.sql.SQLException;
import cl.praxis.models.dto.Usuario;

public interface UsuarioDAO {
    Usuario getUsuarioByCorreoAndPassword(String correo, String password) throws SQLException;
    boolean addUsuario(Usuario usuario) throws SQLException;
    boolean usuarioExists(String correo) throws SQLException;
}
