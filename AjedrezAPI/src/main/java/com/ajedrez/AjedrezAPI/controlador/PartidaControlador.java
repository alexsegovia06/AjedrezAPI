package com.ajedrez.AjedrezAPI.controlador;

import com.ajedrez.AjedrezAPI.modelo.Partida;
import com.ajedrez.AjedrezAPI.servicio.PartidaServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partidas")
public class PartidaControlador {

    private final PartidaServicio partidaServicio;

    public PartidaControlador(PartidaServicio partidaServicio){
        this.partidaServicio = partidaServicio;
    }

    //obtener (extra)
    @GetMapping
    public ResponseEntity<List<Partida>> getPartidas() {
        return new ResponseEntity<>(partidaServicio.obtenerPartidas(), HttpStatus.OK);
    }

    //obtener especifico
    @GetMapping("{partidaId}")
    public ResponseEntity<Partida> getPartida(@PathVariable Long partidaId) {
        return new ResponseEntity<>(partidaServicio.getPartida(partidaId), HttpStatus.OK);
    }


    //publicar o anadir
    @PostMapping
    public ResponseEntity<Partida> postPartida(@RequestBody Partida partida) {
        return new ResponseEntity<>(partidaServicio.postPartida(partida), HttpStatus.CREATED);
    }

    //eliminar
    @DeleteMapping("{partidaId}")
    public ResponseEntity<Void> deletePartida(@PathVariable Long partidaId) {
        partidaServicio.deletePartida(partidaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //actualizar-finalizar
    @PutMapping("{partidaId}")
    public ResponseEntity<Partida> actualizarPartida(@PathVariable Long partidaId, @RequestBody Partida partida) {
        return new ResponseEntity<>(partidaServicio.actualizarPartida(partidaId, partida), HttpStatus.OK);
    }

    @PutMapping("{partidaId}/finalizar")
    public ResponseEntity<Partida> finalizarPartida(@PathVariable Long partidaId, @RequestBody Partida partida) {
        return new ResponseEntity<>(partidaServicio.finalizarPartida(partidaId, partida.getResultado(), partida.getNumeroJugadas(), partida.getTiempoTotal()), HttpStatus.OK);
    }

}
