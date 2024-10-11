package com.design.patterns.abstract_factory_ecommerce.service;

import com.design.patterns.abstract_factory_ecommerce.domains.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    // Simulamos las "bases de datos" con listas en memoria
    private final List<Product> productosMySQL = new ArrayList<>();
    private final List<Product> productosOracle = new ArrayList<>();
    private String currentDB;

    // Lista de bases de datos disponibles
    private final List<String> databases = new ArrayList<>();

    // Constructor
    public ProductService() {
        databases.add("MySQL");
        databases.add("Oracle");
    }

    // Método para obtener las bases de datos disponibles
    public List<String> obtenerBasesDeDatos() {
        return databases;
    }

    // Configurar la base de datos (simulada)
    public void configureDB(String dbType) {
        if (dbType.equalsIgnoreCase("MySQL")) {
            currentDB = "MySQL";
        } else if (dbType.equalsIgnoreCase("Oracle")) {
            currentDB = "Oracle";
        } else {
            throw new IllegalArgumentException("Tipo de base de datos no soportado: " + dbType);
        }
    }

    // Limpiar la base de datos actual
    public void clearDB() {
        if (currentDB == null) {
            throw new IllegalStateException("Base de datos no configurada.");
        }

        switch (currentDB) {
            case "MySQL" -> productosMySQL.clear();
            case "Oracle" -> productosOracle.clear();
            default -> throw new IllegalStateException("Base de datos desconocida.");
        }
    }

    // Obtener la base de datos actual
    public String getCurrentDB() {
        return currentDB;
    }

    // Agregar producto según la base de datos configurada
    public void agregarProducto(Product producto) {
        if (currentDB == null) {
            throw new IllegalStateException("Base de datos no configurada.");
        }

        switch (currentDB) {
            case "MySQL" -> productosMySQL.add(producto);
            case "Oracle" -> productosOracle.add(producto);
            default -> throw new IllegalStateException("Base de datos desconocida.");
        }
    }

    // Obtener productos según la base de datos configurada
    public List<Product> obtenerProductos() {
        if (currentDB == null) {
            throw new IllegalStateException("Base de datos no configurada.");
        }

        switch (currentDB) {
            case "MySQL" -> {
                return productosMySQL;
            }
            case "Oracle" -> {
                return productosOracle;
            }
            default -> throw new IllegalStateException("Base de datos desconocida.");
        }
    }

    // Actualizar un registro por ID guardado en una base de datos
    public void actualizarProducto(Long id, Product producto) {
        if (currentDB == null) {
            throw new IllegalStateException("Base de datos no configurada.");
        }

        switch (currentDB) {
            case "MySQL" -> productosMySQL.stream().filter(p -> p.getId().equals(id)).forEach(p -> {
                    p.setNombre(producto.getNombre());
                    p.setPrecio(producto.getPrecio());
                });
            case "Oracle" -> productosOracle.stream().filter(p -> p.getId().equals(id)).forEach(p -> {
                    p.setNombre(producto.getNombre());
                    p.setPrecio(producto.getPrecio());
                });
            default -> throw new IllegalStateException("Base de datos desconocida.");
        }
    }

    // Obtener un producto por su ID
    public Product obtenerProductoPorId(int id) {
        if (currentDB == null) {
            throw new IllegalStateException("Base de datos no configurada.");
        }

        switch (currentDB) {
            case "MySQL" -> {
                return productosMySQL.stream().filter(producto -> producto.getId() == id).findFirst().orElse(null);
            }
            case "Oracle" -> {
                return productosOracle.stream().filter(producto -> producto.getId() == id).findFirst().orElse(null);
            }
            default -> throw new IllegalStateException("Base de datos desconocida.");
        }
    }

    // Eliminar todos los registros guardados en una base de datos
    public void eliminarProductos() {
        if (currentDB == null) {
            throw new IllegalStateException("Base de datos no configurada.");
        }

        switch (currentDB) {
            case "MySQL" -> productosMySQL.clear();
            case "Oracle" -> productosOracle.clear();
            default -> throw new IllegalStateException("Base de datos desconocida.");
        }
    }

    // Eliminar un registro guardado en una base de datos
    public void eliminarProducto(Long id) { // Cambia String a Long
        if (currentDB == null) {
            throw new IllegalStateException("Base de datos no configurada.");
        }

        switch (currentDB) {
            case "MySQL" -> productosMySQL.removeIf(producto -> producto.getId().equals(id)); // Cambia == a .equals()
            case "Oracle" -> productosOracle.removeIf(producto -> producto.getId().equals(id)); // Cambia == a .equals()
            default -> throw new IllegalStateException("Base de datos desconocida.");
        }
    }

    // Eliminar los productos de la base de datos cuyos valores esten en null
    public void eliminarProductosNulos() {
        if (currentDB == null) {
            throw new IllegalStateException("Base de datos no configurada.");
        }

        switch (currentDB) {
            case "MySQL" -> productosMySQL.removeIf(producto -> producto.getNombre() == null || producto.getPrecio() == null);
            case "Oracle" -> productosOracle.removeIf(producto -> producto.getNombre() == null || producto.getPrecio() == null);
            default -> throw new IllegalStateException("Base de datos desconocida.");
        }
    }

    public List<Product> getProductosMySQL() {
        return productosMySQL;
    }
}
