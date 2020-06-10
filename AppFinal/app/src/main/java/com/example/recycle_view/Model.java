package com.example.recycle_view;

public class Model {
    private String Nombres;
    private String Apellidos;
    private int imgEmpleado;
    private String Identificacion;
    private String Cargo;
    private String AreaDeTrabajo;
    private String Telefono;

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        this.Telefono = telefono;
    }

    public String getIdentificacion() {
        return Identificacion;
    }

    public void setIdentificacion(String identificacion) {
        Identificacion = identificacion;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String cargo) {
        this.Cargo = cargo;
    }

    public String getAreaDeTrabajo() {
        return AreaDeTrabajo;
    }

    public void setAreaDeTrabajo(String areaDeTrabajo) {
        this.AreaDeTrabajo = areaDeTrabajo;
    }

    public String getNombres(){
        return Nombres;
    }
    public void setNombres(String nombres){
        this.Nombres = nombres;
    }
    public String getApellidos(){
        return Apellidos;
    }
    public void setApellidos(String apellidos){
        this.Apellidos = apellidos;
    }
    public int getImgEmpleado(){
        return imgEmpleado;
    }
    public void setImgEmpleado(int imgEmpleado){
        this.imgEmpleado = imgEmpleado;
    }
}
