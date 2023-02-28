package com.example.mapa;

public class Concierto {

    private String titol;
    private String imagen;
    private String descripcio;
    private String fecha;

    public Concierto(String titol, String imagen, String fecha) {
        this.titol = titol;
        this.imagen = imagen;
        this.fecha = fecha;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
