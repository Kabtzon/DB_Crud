package SegundoParcial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CRUDProductos {

    // Configuración de la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/BDMunshi";
    private static final String USER = "root";
    private static final String PASSWORD = "KabeTzon17/";

    // Método para conectar a la base de datos
    public static Connection conectar() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
        return conexion;
    }

    // Método para insertar un producto
    public static void insertarProducto(String codigo, String nombre, double precio, int cantidad, String fecha) {
        String query = "INSERT INTO producto (CodigoProducto, NombreProducto, PrecioUnitario, CantidadProducto, FechaVencimiento) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = CRUDProductos.conectar(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, codigo);
            pst.setString(2, nombre);
            pst.setDouble(3, precio);
            pst.setInt(4, cantidad);
            pst.setDate(5, java.sql.Date.valueOf(fecha));
            pst.executeUpdate();
            System.out.println("Producto insertado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al insertar producto: " + e.getMessage());
        }
    }

    // Método para listar productos
    public static void listarProductos() {
        String query = "SELECT * FROM producto";
        try (Connection con = CRUDProductos.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(query)) {
            boolean hayResultados = false;
            while (rs.next()) {
                hayResultados = true;
                System.out.println("Código: " + rs.getString("CodigoProducto"));
                System.out.println("Nombre: " + rs.getString("NombreProducto"));
                System.out.println("Precio: " + rs.getDouble("PrecioUnitario"));
                System.out.println("Cantidad: " + rs.getInt("CantidadProducto"));
                System.out.println("Fecha de Vencimiento: " + rs.getDate("FechaVencimiento"));
                System.out.println("");
            }
            if (!hayResultados) {
                System.out.println("No hay productos disponibles.");
            }
        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }
    }

    // Método para buscar un producto por su código
    public static void buscarProducto(String codigoProducto) {
        String query = "SELECT * FROM producto WHERE CodigoProducto = ?";
        try (Connection con = CRUDProductos.conectar(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, codigoProducto);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Código: " + rs.getString("CodigoProducto"));
                    System.out.println("Nombre: " + rs.getString("NombreProducto"));
                    System.out.println("Precio: " + rs.getDouble("PrecioUnitario"));
                    System.out.println("Cantidad: " + rs.getInt("CantidadProducto"));
                    System.out.println("Fecha de Vencimiento: " + rs.getDate("FechaVencimiento"));
                } else {
                    System.out.println("Producto no encontrado.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar producto: " + e.getMessage());
        }
    }

    // Método para actualizar un producto
    public static void actualizarProducto(String codigoProducto, String nombre, double precio) {
        String query = "UPDATE producto SET NombreProducto = ?, PrecioUnitario = ? WHERE CodigoProducto = ?";
        try (Connection con = CRUDProductos.conectar(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, nombre);
            pst.setDouble(2, precio);
            pst.setString(3, codigoProducto);
            pst.executeUpdate();
            System.out.println("Producto actualizado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
        }
    }

    // Método para eliminar un producto
    public static void eliminarProducto(String codigoProducto) {
        String query = "DELETE FROM producto WHERE CodigoProducto = ?";
        try (Connection con = CRUDProductos.conectar(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, codigoProducto);
            pst.executeUpdate();
            System.out.println("Producto eliminado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }
    }

    // Método para mostrar el menú principal
    public static void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("********** Menú principal **********");
            System.out.println("1.....Ingresar producto");
            System.out.println("2.....Mostrar productos");
            System.out.println("3.....Buscar producto");
            System.out.println("4.....Modificar producto");
            System.out.println("5.....Eliminar producto");
            System.out.println("6.....Salir del menú principal");
            System.out.print("Seleccione una opción del menú: ");
            opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Código del producto: ");
                    String codigo = scanner.nextLine();
                    System.out.print("Nombre del producto: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Precio del producto: ");
                    double precio = scanner.nextDouble();
                    System.out.print("Cantidad del producto: ");
                    int cantidad = scanner.nextInt();
                    scanner.nextLine();  // Limpiar buffer
                    System.out.print("Fecha de vencimiento (YYYY-MM-DD): ");
                    String fecha = scanner.nextLine();
                    insertarProducto(codigo, nombre, precio, cantidad, fecha);
                    break;
                case 2:
                    listarProductos();
                    break;
                case 3:
                    System.out.print("Ingrese el código del producto a buscar: ");
                    String codigoBusqueda = scanner.nextLine();
                    buscarProducto(codigoBusqueda);
                    break;
                case 4:
                    System.out.print("Código del producto a modificar: ");
                    String codigoMod = scanner.nextLine();
                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = scanner.nextLine();
                    System.out.print("Nuevo precio: ");
                    double nuevoPrecio = scanner.nextDouble();
                    actualizarProducto(codigoMod, nuevoNombre, nuevoPrecio);
                    break;
                case 5:
                    System.out.print("Código del producto a eliminar: ");
                    String codigoEliminar = scanner.nextLine();
                    eliminarProducto(codigoEliminar);
                    break;
                case 6:
                    System.out.println("Saliendo del menú...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 6);

        scanner.close();
    }

    // Método principal
    public static void main(String[] args) {
        mostrarMenu();
    }
}
