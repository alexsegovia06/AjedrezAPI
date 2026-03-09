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

    //METODOS PARA PARTIDAS

    //obtener
    @GetMapping
    public ResponseEntity<List<Partida>> getPartidas() {
        return new ResponseEntity<>(partidaServicio.obtenerPartidas(), HttpStatus.OK);
    }

    //obtener especifico
    @GetMapping("{partidaId}")
    public ResponseEntity<Partida> getPartida(@PathVariable Long partidaId) {
        Partida partida = partidaServicio.getPartida(partidaId);
        if (partida == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(partida, HttpStatus.OK);
    }


    //publicar o anadir
    @PostMapping
    public ResponseEntity<Partida> postPartida(@RequestBody Partida partida) {
        return new ResponseEntity<>(partidaServicio.postPartida(partida), HttpStatus.CREATED);
    }

    //eliminar
    @DeleteMapping("{jugadorId}")
    public ResponseEntity<String> deletePartida(@PathVariable Long partidaId) {
        boolean eliminado = partidaServicio.deletePartida(partidaId);
        if (!eliminado) return new ResponseEntity<>("No se encontró la partida", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>("Se elimino la partida del sistema", HttpStatus.NO_CONTENT);
    }

    //actualizar
    @PutMapping("{jugadorId}")
    public ResponseEntity<Partida> actualizarPartida(@PathVariable Long partidaId, @RequestBody Partida partida) {
        Partida actualizado = partidaServicio.actualizarPartida(partidaId, partida);
        if (actualizado == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }



}
