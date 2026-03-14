package com.ajedrez.AjedrezAPI.repositorio;

import com.ajedrez.AjedrezAPI.modelo.Jugador;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JugadorRepositorio {

    private final List<Jugador> jugadores = new ArrayList<>();

    private Long judadorId = 1L;

    //Metodos para jugador
    public List<Jugador> obtenerJugadores() {
        return jugadores;
    }

    //buscar al jugador con un ciclo y devolverlo si esta, si no se devuelve null
    public Jugador getJugador(Long id) {
        for(Jugador jugador : jugadores) {
            if(jugador.getJugadorId().equals(id)) {
                return jugador;
            }
        }
        return null;
    }

    //para crear al jugador se suma 1 al id para que sea unico y se anade a la lista
    public Jugador postJugador(Jugador jugador) {
        jugador.setJugadorId(judadorId++);
        jugadores.add(jugador);
        return jugador;
    }

    //borrar un jugador
    public boolean deleteJugador(Long id) {
        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).getJugadorId().equals(id)) {
                jugadores.remove(i);
                return true;
            }
        }
        return false;
    }

    //con un ciclo se busca al jugador por el nombre para cambiarle los datos
    public Jugador actualizarJugador(Long id, Jugador nuevoJugador) {
        for(Jugador jugador : jugadores) {
            if(jugador.getJugadorId().equals(id)) {
                jugador.setEdad(nuevoJugador.getEdad());
                jugador.setElo(nuevoJugador.getElo());
                jugador.setGenero(nuevoJugador.getGenero());
                jugador.setNacionalidad(nuevoJugador.getNacionalidad());
                jugador.setNombreCompleto(nuevoJugador.getNombreCompleto());
                return jugador;
            }
        }
        return null;
    }

}
