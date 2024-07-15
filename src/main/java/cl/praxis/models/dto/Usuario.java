package cl.praxis.models.dto;

import java.sql.Timestamp;

public class Usuario {
    private int id;
    private String correo;
    private Timestamp createdAt;
    private String nick;
    private String nombre;
    private String password;
    private float peso;
    private Timestamp updatedAt;

    
    public Usuario() {}

   
    public Usuario(int id, String correo, Timestamp createdAt, String nick, String nombre, String password, float peso, Timestamp updatedAt) {
        this.id = id;
        this.correo = correo;
        this.createdAt = createdAt;
        this.nick = nick;
        this.nombre = nombre;
        this.password = password;
        this.peso = peso;
        this.updatedAt = updatedAt;
    }

  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
