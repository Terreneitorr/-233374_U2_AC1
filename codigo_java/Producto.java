public class Producto {
    private String nombre;
    private double precio;

    public Producto(String nombre, double precio) {
        setNombre(nombre);
        setPrecio(precio);
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede ser nulo o vacio.");
        }
        this.nombre = nombre.trim();
    }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) {
        if (Double.isNaN(precio) || Double.isInfinite(precio) || precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser un numero valido mayor que cero.");
        }
        this.precio = precio;
    }

    @Override
    public String toString() {
        return String.format("%-20s $%.2f", nombre, precio);
    }
}
