package com.ajedrez.AjedrezAPI.servicio;

import com.ajedrez.AjedrezAPI.exception.Conflicto;
import com.ajedrez.AjedrezAPI.exception.NotFound;
import com.ajedrez.AjedrezAPI.exception.Validacion;
import com.ajedrez.AjedrezAPI.modelo.Jugador;
import com.ajedrez.AjedrezAPI.modelo.Partida;
import com.ajedrez.AjedrezAPI.repositorio.JugadorRepositorio;
import com.ajedrez.AjedrezAPI.repositorio.PartidaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JugadorServicio {

    private final JugadorRepositorio jugadorRepositorio;
    private final PartidaRepositorio partidaRepositorio;

    public JugadorServicio(JugadorRepositorio jugadorRepositorio, PartidaRepositorio partidaRepositorio) {
        this.jugadorRepositorio = jugadorRepositorio;
        this.partidaRepositorio = partidaRepositorio;
    }

    //JUGADORES
    public List<Jugador> obtenerJugadores(){
        return jugadorRepositorio.obtenerJugadores();
    }

    public Jugador getJugador(Long id) {
        Jugador jugador = jugadorRepositorio.getJugador(id);
        //validacion de que el jugador exista
        if (jugador == null) throw new NotFound("No se encontró al jugador con id: " + id);
        return jugador;
    }

    public Jugador postJugador(Jugador jugador) {
        //validacion de que el nombre completo no sea nulo ni este vacio
        if (jugador.getNombreCompleto() == null || jugador.getNombreCompleto().isBlank()) {
            throw new Validacion("El nombre completo no puede estar vacío");
        }
        //validacion de que la nacionalidad no sea nula
        if (jugador.getNacionalidad() == null) {
            throw new Validacion("La nacionalidad no puede ser nula");
        }
        //validacion de que el Elo sea mayor a 0
        if (jugador.getElo() < 0) {
            throw new Validacion("El elo no puede ser negativo");
        }
        return jugadorRepositorio.postJugador(jugador);
    }

    public boolean deleteJugador(Long id) {
        if (jugadorRepositorio.getJugador(id) == null) {
            throw new NotFound("No se encontró al jugador con id: " + id);
        }
        for (Partida partida : partidaRepositorio.obtenerPartidas()) {
            boolean esJugadorDeLaPartida = partida.getJugadorBlancasId().equals(id)
                    || partida.getJugadorNegrasId().equals(id);
            boolean partidaActiva = partida.getEstado().equals("Pendiente")
                    || partida.getEstado().equals("En curso");

            if (esJugadorDeLaPartida && partidaActiva) {
                throw new Conflicto("El jugador tiene partidas activas y no puede eliminarse");
            }
        }

        return jugadorRepositorio.deleteJugador(id);
    }
    public Jugador actualizarJugador(Long id, Jugador jugador) {
        //validar que el jugador no sea nulo
        if (jugadorRepositorio.getJugador(id) == null) {
            throw new NotFound("No se encontró al jugador con id: " + id);
        }
        //validar que el nombre completo no sea nulo ni este vacio
        if (jugador.getNombreCompleto() == null || jugador.getNombreCompleto().isBlank()) {
            throw new Validacion("El nombre completo no puede quedar vacío");
        }
        //validar que el elo no sea menor a 0, no puede ser negativo
        if (jugador.getElo() < 0) {
            throw new Validacion("El elo no puede ser negativo");
        }
        return jugadorRepositorio.actualizarJugador(id, jugador);
    }

}
