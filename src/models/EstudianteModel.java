package models;

public class EstudianteModel {
    private String nombre;
    private String apellido;
    private String dni;

    public EstudianteModel(String nombre, String apellido, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }


    @Override
    public String toString() {
        return "EstudianteModel{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                '}';
    }

    public String toFile() {
        return nombre+";"+apellido+";"+dni;
    }

    public EstudianteModel(String alumnoCodificao) {
        //SOLUCION DE EDU
        this.nombre = alumnoCodificao.split(";")[0];
        this.apellido= alumnoCodificao.split(";")[1];
        this.dni = alumnoCodificao.split(";")[2];
    }
}
