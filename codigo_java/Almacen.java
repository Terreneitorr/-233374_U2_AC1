import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Almacen {
    private final LinkedList<Producto> listaProductos = new LinkedList<>();

    public void agregarProducto(Producto producto) {
        if (producto == null) throw new IllegalArgumentException("El producto no puede ser nulo.");
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede ser nulo o vacio.");
        }
        if (Double.isNaN(producto.getPrecio()) || Double.isInfinite(producto.getPrecio()) || producto.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio del producto debe ser un valor valido mayor que cero.");
        }
        listaProductos.add(producto);
    }

    public void ordenarProductos() {
        listaProductos.sort(Comparator.comparingDouble(Producto::getPrecio));
    }

    public void visualizarListaProductos() {
        if (listaProductos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }
        System.out.println("\n--- Lista de productos ---");
        for (Producto p : listaProductos) System.out.println("  " + p);
    }

    public Producto buscarProducto(double precioBuscado) {
        if (listaProductos.isEmpty() || Double.isNaN(precioBuscado) || Double.isInfinite(precioBuscado)) {
            return null;
        }

        Producto[] arreglo = listaProductos.stream()
                .sorted(Comparator.comparingDouble(Producto::getPrecio))
                .toArray(Producto[]::new);
        return busquedaBinariaRecursiva(arreglo, precioBuscado, 0, arreglo.length - 1);
    }

    private Producto busquedaBinariaRecursiva(Producto[] arreglo, double precioBuscado,
                                               int inicio, int fin) {
        if (inicio > fin) return null;
        int medio = inicio + (fin - inicio) / 2;
        int comparacion = Double.compare(arreglo[medio].getPrecio(), precioBuscado);
        if (comparacion == 0) return arreglo[medio];
        if (comparacion > 0)
            return busquedaBinariaRecursiva(arreglo, precioBuscado, inicio, medio - 1);
        return busquedaBinariaRecursiva(arreglo, precioBuscado, medio + 1, fin);
    }

    public boolean estaVacio() { return listaProductos.isEmpty(); }
    public List<Producto> getProductos() { return new LinkedList<>(listaProductos); }
}
