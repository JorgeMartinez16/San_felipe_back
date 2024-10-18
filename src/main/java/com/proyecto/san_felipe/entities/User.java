package com.proyecto.san_felipe.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users") // Nombre de la colección en MongoDB
public class User {

    @Id
    private String id; // ID como String, MongoDB usa ObjectId por defecto

    private String username;
    private String password;
    private String role; // Añade este atributo

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role; // Añade este método
    }

    public void setRole(String role) {
        this.role = role; // Añade este método
    }
}
