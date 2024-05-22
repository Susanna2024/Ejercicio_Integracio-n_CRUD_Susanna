package esjava_sql;

public class Material {
    private int idMaterial;
    private String nombre;
    private String teoria;
    private String fecha;

    // Constructor de la clase Material
    public Material(int idMaterial, String nombre, String teoria, String fecha) {
        this.idMaterial = idMaterial;
        this.nombre = nombre;
        this.teoria = teoria;
        this.fecha = fecha;
    }

    // Método getter para el atributo idMaterial
    public int getIdMaterial() {
        return idMaterial;
    }

    // Métodos getter y setter para el atributo nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Métodos getter y setter para el atributo teoria
    public String getTeoria() {
        return teoria;
    }

    public void setTeoria(String teoria) {
        this.teoria = teoria;
    }

    // Métodos getter y setter para el atributo fecha
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
