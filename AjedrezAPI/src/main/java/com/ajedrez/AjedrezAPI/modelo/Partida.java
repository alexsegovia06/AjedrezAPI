package com.ajedrez.AjedrezAPI.modelo;

public class Partida {

    private Long partidaId;
    private String ritmo;
    private Long jugadorBlancasId;
    private Long jugadorNegrasId;
    private String apertura;
    private int numeroJugadas;
    private String estado;
    private String resultado;
    private int tiempoTotal;

    public Partida(Long partidaId, String ritmo, Long jugadorBlancasId, Long jugadorNegrasId, String apertura, int numeroJugadas, String estado, int tiempoTotal) {
        this.partidaId = partidaId;
        this.ritmo = ritmo;
        this.jugadorBlancasId = jugadorBlancasId;
        this.jugadorNegrasId = jugadorNegrasId;
        this.apertura = apertura;
        this.numeroJugadas = numeroJugadas;
        this.estado = estado;
        this.tiempoTotal = tiempoTotal;
    }

    public Partida(){}

    public int getTiempoTotal() {
        return tiempoTotal;
    }

    public String getResultado() {
        return resultado;
    }

    public String getEstado() {
        return estado;
    }

    public int getNumeroJugadas() {
        return numeroJugadas;
    }

    public Long getPartidaId() {
        return partidaId;
    }

    public String getRitmo() {
        return ritmo;
    }

    public Long getJugadorBlancasId() {
        return jugadorBlancasId;
    }

    public Long getJugadorNegrasId() {
        return jugadorNegrasId;
    }

    public String getApertura() {
        return apertura;
    }

    public void setPartidaId(Long partidaId) {
        this.partidaId = partidaId;
    }

    public void setRitmo(String ritmo) {
        this.ritmo = ritmo;
    }

    public void setJugadorBlancasId(Long jugadorBlancasId) {
        this.jugadorBlancasId = jugadorBlancasId;
    }

    public void setJugadorNegrasId(Long jugadorNegrasId) {
        this.jugadorNegrasId = jugadorNegrasId;
    }

    public void setApertura(String apertura) {
        this.apertura = apertura;
    }

    public void setNumeroJugadas(int numeroJugadas) {
        this.numeroJugadas = numeroJugadas;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public void setTiempoTotal(int tiempoTotal) {
        this.tiempoTotal = tiempoTotal;
    }

}
