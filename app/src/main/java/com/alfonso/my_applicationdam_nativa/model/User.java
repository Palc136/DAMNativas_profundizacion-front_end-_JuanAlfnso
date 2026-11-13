package com.alfonso.my_applicationdam_nativa.model;

/**
 * User representa un usuario del sistema para el panel de administración.
 * Incluye rol y estado para listarlos y gestionarlos.
 */
public class User {

    // ¿Para qué sirve?: identificar de forma única al usuario.
    // ¿Qué hace?: almacena un entero que diferencia un usuario de otro.
    // ¿Cuál es su función en el código?: permite editar o eliminar un usuario específico.
    private int id;

    // ¿Para qué sirve?: mostrar el nombre completo del usuario.
    private String name;

    // ¿Para qué sirve?: mostrar el correo de contacto / login.
    private String email;

    // ¿Para qué sirve?: clasificar al usuario por rol dentro del sistema.
    // ¿Qué hace?: almacena un texto como "Comprador", "Vendedor" o "Administrador".
    // ¿Cuál es su función en el código?: define el badge de color y las acciones disponibles.
    private String role;

    // ¿Para qué sirve?: saber si la cuenta está activa o suspendida.
    private boolean active;

    public User(int id, String name, String email, String role, boolean active) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.active = active;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public boolean isActive() { return active; }

    public void setRole(String role) { this.role = role; }
    public void setActive(boolean active) { this.active = active; }
}
