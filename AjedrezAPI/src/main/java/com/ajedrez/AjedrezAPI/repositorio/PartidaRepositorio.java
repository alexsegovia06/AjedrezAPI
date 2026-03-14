package com.ajedrez.AjedrezAPI.repositorio;

import com.ajedrez.AjedrezAPI.modelo.Partida;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartidaRepositorio {

    private final List<Partida> partidas = new ArrayList<>();

    private Long partidaId = 1L;

    //Metodos para partidas
    public List<Partida> obtenerPartidas() {
        return partidas;
    }

    //buscar la partida con un ciclo y devolverlo si esta, si no se devuelve null
    public Partida getPartida(Long id) {
        for(Partida partida : partidas) {
            if(partida.getPartidaId().equals(id)) {
                return partida;
            }
        }
        return null;
    }

    //para crear la partida se suma 1 al id para que sea unico y se anade a la lista
    public Partida postPartida(Partida partida) {
        partida.setPartidaId(partidaId++);
        partidas.add(partida);
        return partida;
    }

    //borrar un la partida
    public boolean deletePartida(Long id) {
        for (int i = 0; i < partidas.size(); i++) {
            if (partidas.get(i).getPartidaId().equals(id)) {
                partidas.remove(i);
                return true;
            }
        }
        return false;
    }

    //con un ciclo se busca la partida por el nombre para cambiarle los datos y finalizar la partida
    public Partida actualizarPartida(Long id, Partida nuevaPartida) {
        for(Partida partida : partidas) {
            if(partida.getPartidaId().equals(id)) {
                partida.setApertura(nuevaPartida.getApertura());
                partida.setEstado(nuevaPartida.getEstado());
                partida.setJugadorBlancasId(nuevaPartida.getJugadorBlancasId());
                partida.setJugadorNegrasId(nuevaPartida.getJugadorNegrasId());
                partida.setNumeroJugadas(nuevaPartida.getNumeroJugadas());
                partida.setResultado(nuevaPartida.getResultado());
                partida.setRitmo(nuevaPartida.getRitmo());
                partida.setTiempoTotal(nuevaPartida.getTiempoTotal());
                return partida;
            }
        }
        return null;
    }

    public Partida finalizarPartida(Long id, Partida partidaFinalizada) {
        for (Partida partida : partidas) {
            if (partida.getPartidaId().equals(id)) {
                partida.setResultado(partidaFinalizada.getResultado());
                partida.setNumeroJugadas(partidaFinalizada.getNumeroJugadas());
                partida.setTiempoTotal(partidaFinalizada.getTiempoTotal());
                partida.setEstado("Finalizada");
                return partida;
            }
        }
        return null;
    }

}
