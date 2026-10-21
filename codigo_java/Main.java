import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final Almacen almacen = new Almacen();

    public static void main(String[] args) {
        cargarProductosDeEjemplo();
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Opcion: ");
            switch (opcion) {
                case 1 -> { almacen.ordenarProductos(); System.out.println("Ordenamiento por precio aplicado."); }
                case 2 -> almacen.visualizarListaProductos();
                case 3 -> buscarProducto();
                case 4 -> agregarProducto();
                case 0 -> System.out.println("Hasta luego.");
                default -> System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);
        sc.close();
    }

    private static void cargarProductosDeEjemplo() {
        almacen.agregarProducto(new Producto("Teclado", 450.00));
        almacen.agregarProducto(new Producto("Mouse", 199.50));
        almacen.agregarProducto(new Producto("Monitor", 3200.00));
        almacen.agregarProducto(new Producto("Audifonos", 699.90));
        almacen.agregarProducto(new Producto("Webcam", 350.00));
    }

    private static void mostrarMenu() {
        System.out.println("\n===== ALMACEN DE PRODUCTOS =====");
        System.out.println("1. Ordenar productos por precio (Comparator)");
        System.out.println("2. Visualizar productos");
        System.out.println("3. Buscar por precio (busqueda binaria)");
        System.out.println("4. Agregar producto");
        System.out.println("0. Salir");
    }

    private static void agregarProducto() {
        String nombre = leerTexto("Nombre del producto: ");
        double precio = leerPrecio("Precio (mayor que cero): ");
        almacen.agregarProducto(new Producto(nombre, precio));
        System.out.println("Producto agregado.");
    }

    private static void buscarProducto() {
        if (almacen.estaVacio()) {
            System.out.println("No hay productos para buscar.");
            return;
        }
        double precio = leerDouble("Precio exacto a buscar: ");
        Producto encontrado = almacen.buscarProducto(precio);
        if (encontrado != null) System.out.println("Producto encontrado: " + encontrado);
        else System.out.println("No se encontro ningun producto con ese precio.");
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextInt()) {
            System.out.print("Ingresa un numero entero valido: ");
            sc.next();
        }
        int valor = sc.nextInt();
        sc.nextLine();
        return valor;
    }

    private static double leerDouble(String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextDouble()) {
            System.out.print("Ingresa un numero valido: ");
            sc.next();
        }
        double valor = sc.nextDouble();
        sc.nextLine();
        return valor;
    }

    private static double leerPrecio(String mensaje) {
        double precio;
        do {
            precio = leerDouble(mensaje);
            if (Double.isNaN(precio) || Double.isInfinite(precio) || precio <= 0) {
                System.out.println("El precio debe ser un numero valido mayor que cero.");
            }
        } while (Double.isNaN(precio) || Double.isInfinite(precio) || precio <= 0);
        return precio;
    }

    private static String leerTexto(String mensaje) {
        String texto;
        do {
            System.out.print(mensaje);
            texto = sc.nextLine().trim();
        } while (texto.isEmpty());
        return texto;
    }
}
