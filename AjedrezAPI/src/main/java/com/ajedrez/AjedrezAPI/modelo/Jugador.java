package com.ajedrez.AjedrezAPI.modelo;

public class Jugador {

    private Long jugadorId;
    private String nombreCompleto;
    private char genero;
    private int edad;
    private int elo;
    private String nacionalidad;

    public Jugador(Long jugadorId, String nombreCompleto, char genero, int edad, int elo, String nacionalidad) {
        this.jugadorId = jugadorId;
        this.nombreCompleto = nombreCompleto;
        this.genero = genero;
        this.edad = edad;
        this.elo = elo;
        this.nacionalidad = nacionalidad;
    }

    public Long getJugadorId() {
        return jugadorId;
    }
    public void setJugadorId(Long jugadorId) {
        this.jugadorId = jugadorId;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public char getGenero() {
        return genero;
    }
    public void setGenero(char genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getElo() {
        return elo;
    }
    public void setElo(int elo) {
        this.elo = elo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }


}
