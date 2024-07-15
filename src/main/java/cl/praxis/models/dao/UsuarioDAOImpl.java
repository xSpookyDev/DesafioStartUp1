package cl.praxis.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cl.praxis.models.dto.Usuario;
import cl.praxis.models.db.Db;

public class UsuarioDAOImpl implements UsuarioDAO {

    private Connection conn;

    public UsuarioDAOImpl() {
        conn = Db.getConnect();
    }

    public Usuario getUsuarioByCorreoAndPassword(String correo, String password) throws SQLException {
        Usuario usuario = null;
        
   
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND password = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, correo);
            ps.setString(2, password);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("correo"),
                        rs.getTimestamp("created_At"),
                        rs.getString("nick"),
                        rs.getString("nombre"),
                        rs.getString("password"),
                        rs.getFloat("peso"),
                        rs.getTimestamp("updated_At")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        }
        
        return usuario;
    }

    @Override
    public boolean addUsuario(Usuario usuario) throws SQLException {
        boolean added = false;
        String sql = "INSERT INTO usuarios (correo, created_at, nick, nombre, password, peso, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario.getCorreo());
            ps.setTimestamp(2, usuario.getCreatedAt());
            ps.setString(3, usuario.getNick());
            ps.setString(4, usuario.getNombre());
            ps.setString(5, usuario.getPassword());
            ps.setFloat(6, usuario.getPeso());
            ps.setTimestamp(7, usuario.getUpdatedAt());
            int rowsAffected = ps.executeUpdate();
            added = (rowsAffected > 0);
        }
        return added;
    }

    @Override
    public boolean usuarioExists(String correo) throws SQLException {
        boolean exists = false;
        String sql = "SELECT COUNT(*) AS count FROM usuarios WHERE correo = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("count");
                    exists = (count > 0);
                }
            }
        }
        return exists;
    }
}
