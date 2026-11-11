package com.alfonso.my_applicationdam_nativa.model;

/**
 * Order representa un pedido recibido por el vendedor.
 * Contiene los datos necesarios para listarlo en la pantalla de pedidos.
 */
public class Order {

    // ¿Para qué sirve?: identificar de forma única el pedido.
    // ¿Qué hace?: almacena un entero que diferencia un pedido de otro.
    // ¿Cuál es su función en el código?: permite referenciar pedidos al cambiar su estado.
    private int id;

    // ¿Para qué sirve?: mostrar quién hizo el pedido.
    // ¿Qué hace?: guarda el nombre del comprador.
    // ¿Cuál es su función en el código?: se muestra en la celda del pedido.
    private String buyerName;

    // ¿Para qué sirve?: describir qué se compró.
    // ¿Qué hace?: almacena un resumen del producto o productos.
    // ¿Cuál es su función en el código?: se muestra como subtítulo en la celda.
    private String productSummary;

    // ¿Para qué sirve?: mostrar el monto total del pedido.
    // ¿Qué hace?: guarda el valor en pesos colombianos.
    // ¿Cuál es su función en el código?: se muestra en la celda y en el detalle.
    private double total;

    // ¿Para qué sirve?: representar el estado del pedido.
    // ¿Qué hace?: almacena un texto como "Pendiente", "Enviado", "Entregado" o "Cancelado".
    // ¿Cuál es su función en el código?: determina el color del badge y la acción de cambio de estado.
    private String status;

    public Order(int id, String buyerName, String productSummary, double total, String status) {
        this.id = id;
        this.buyerName = buyerName;
        this.productSummary = productSummary;
        this.total = total;
        this.status = status;
    }

    public int getId() { return id; }
    public String getBuyerName() { return buyerName; }
    public String getProductSummary() { return productSummary; }
    public double getTotal() { return total; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}
