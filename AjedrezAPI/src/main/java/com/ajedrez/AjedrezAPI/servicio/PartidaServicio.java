package com.ajedrez.AjedrezAPI.servicio;

import com.ajedrez.AjedrezAPI.exception.Conflicto;
import com.ajedrez.AjedrezAPI.exception.NotFound;
import com.ajedrez.AjedrezAPI.exception.Validacion;
import com.ajedrez.AjedrezAPI.modelo.Partida;
import com.ajedrez.AjedrezAPI.repositorio.JugadorRepositorio;
import com.ajedrez.AjedrezAPI.repositorio.PartidaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartidaServicio {

    private final JugadorRepositorio jugadorRepositorio;
    private final PartidaRepositorio partidaRepositorio;

    public PartidaServicio(PartidaRepositorio partidaRepositorio, JugadorRepositorio jugadorRepositorio) {
        this.partidaRepositorio = new PartidaRepositorio();
        this.jugadorRepositorio = new JugadorRepositorio();

    }

    //PARTIDAS
    public List<Partida> obtenerPartidas(){
        return partidaRepositorio.obtenerPartidas();
    }

    public Partida getPartida(Long id) {
        Partida partida = partidaRepositorio.getPartida(id);
        if (partida == null) throw new NotFound("No se encontró la partida con id: " + id);
        return partida;
    }

    public Partida postPartida(Partida partida) {
        if (jugadorRepositorio.getJugador(partida.getJugadorBlancasId()) == null) {
            throw new NotFound("No se encontró al jugador de blancas");
        }
        if (jugadorRepositorio.getJugador(partida.getJugadorNegrasId()) == null) {
            throw new NotFound("No se encontró al jugador de negras");
        }
        if (partida.getJugadorBlancasId().equals(partida.getJugadorNegrasId())) {
            throw new Validacion("Un jugador no puede jugar contra sí mismo");
        }

        List<String> ritmosValidos = List.of("Bala", "Blitz", "Rapid", "Classic");
        if (!ritmosValidos.contains(partida.getRitmo())) {
            throw new Validacion("Ritmo inválido. Debe ser: Bala, Blitz, Rapid o Classic");
        }

        partida.setEstado("Pendiente");
        return partidaRepositorio.postPartida(partida);
    }

    public boolean deletePartida(Long id) {
        return partidaRepositorio.deletePartida(id);
    }

    public Partida actualizarPartida(Long id, Partida partida) {
        return partidaRepositorio.actualizarPartida(id, partida);
    }

    public Partida finalizarPartida(Long id, String resultado, int numeroJugadas, int tiempoTotal) {
        Partida partida = partidaRepositorio.getPartida(id);
        if (partida == null) throw new NotFound("No se encontró la partida con id: " + id);

        if (!partida.getEstado().equals("En curso")) {
            throw new Validacion("La partida debe estar en estado 'En curso' para poder finalizarse");
        }
        if (numeroJugadas <= 0) {
            throw new Validacion("El número de jugadas debe ser mayor a 0");
        }

        List<String> resultadosValidos = List.of("1-0", "0-1", "½-½");
        if (!resultadosValidos.contains(resultado)) {
            throw new Validacion("Resultado inválido. Debe ser: 1-0, 0-1 o ½-½");
        }

        if (partida.getRitmo().equals("Bala") && tiempoTotal > 3) {
            throw new Validacion("Bala debe ser ≤ 3 min");
        } else if (partida.getRitmo().equals("Blitz") && tiempoTotal > 10) {
            throw new Validacion("Blitz debe ser ≤ 10 min");
        } else if (partida.getRitmo().equals("Rapid") && tiempoTotal > 60) {
            throw new Validacion("Rapid debe ser ≤ 60 min");
        } else if (partida.getRitmo().equals("Classic") && tiempoTotal <= 60) {
            throw new Validacion("Classic debe ser > 60 min");
        }

        partida.setResultado(resultado);
        partida.setNumeroJugadas(numeroJugadas);
        partida.setTiempoTotal(tiempoTotal);
        partida.setEstado("Finalizada");
        return partida;
    }

}
