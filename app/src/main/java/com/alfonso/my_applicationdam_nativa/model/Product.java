package com.alfonso.my_applicationdam_nativa.model;

/**
 * Product representa un producto del catálogo de la tienda.
 * Es una clase de datos simple (POJO) que usan los adapters
 * y las pantallas para mostrar información.
 */
public class Product {

    // ¿Para qué sirve?: identificar de forma única el producto.
    // ¿Qué hace?: almacena un entero que diferencia un producto de otro.
    // ¿Cuál es su función en el código?: permite referenciar productos sin ambigüedad
    //             (por ejemplo, al eliminar del carrito o al abrir el detalle).
    private int id;

    // ¿Para qué sirve?: mostrar el nombre del producto en la interfaz.
    // ¿Qué hace?: guarda el texto que se verá en tarjetas y en el detalle.
    // ¿Cuál es su función en el código?: alimenta el TextView del nombre en item_product.xml
    //             y en fragment_product_detail.xml.
    private String name;

    // ¿Para qué sirve?: mostrar el precio del producto.
    // ¿Qué hace?: almacena un valor decimal en pesos colombianos.
    // ¿Cuál es su función en el código?: se usa para calcular totales del carrito
    //             y para mostrar el precio en tarjetas y detalle.
    private double price;

    // ¿Para qué sirve?: describir el producto con más detalle.
    // ¿Qué hace?: guarda un texto extendido para la pantalla de detalle.
    // ¿Cuál es su función en el código?: se muestra solo en fragment_product_detail.xml.
    private String description;

    // ¿Para qué sirve?: identificar la categoría del producto.
    // ¿Qué hace?: almacena un texto como "Artesanías", "Café", "Joyería".
    // ¿Cuál es su función en el código?: permite filtrar en el catálogo.
    private String category;

    /**
     * Constructor mínimo usado por los datos mock.
     */
    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = "";
        this.category = "";
    }

    /**
     * Constructor completo usado cuando se requiere descripción y categoría.
     */
    public Product(int id, String name, double price, String description, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    // Getters y setters: exponen los campos de forma controlada para que
    // adapters y Fragments puedan leerlos o modificarlos sin acceder directamente.
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }

    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setDescription(String description) { this.description = description; }
    public void setCategory(String category) { this.category = category; }
}
