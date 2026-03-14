package com.ajedrez.AjedrezAPI.controlador;

import com.ajedrez.AjedrezAPI.modelo.Jugador;
import com.ajedrez.AjedrezAPI.servicio.JugadorServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jugadores")
public class JugadorControlador {

    private final JugadorServicio jugadorServicio;

    public JugadorControlador(JugadorServicio jugadorServicio){
        this.jugadorServicio = jugadorServicio;
    }

    //METODOS PARA JUGADORES

    //obtener
    @GetMapping
    public ResponseEntity<List<Jugador>> getJugadores() {
        return new ResponseEntity<>(jugadorServicio.obtenerJugadores(), HttpStatus.OK);
    }

    //obtener especifico
    @GetMapping("{jugadorId}")
    public ResponseEntity<Jugador> getJugador(@PathVariable Long jugadorId) {
        return ResponseEntity.ok(jugadorServicio.getJugador(jugadorId));
    }


    //publicar o anadir
    @PostMapping
    public ResponseEntity<Jugador> postJugador(@RequestBody Jugador jugador) {
        return new ResponseEntity<>(jugadorServicio.postJugador(jugador), HttpStatus.CREATED);
    }

    //eliminar
    @DeleteMapping("{jugadorId}")
    public ResponseEntity<Void> deleteJugador(@PathVariable Long jugadorId) {
        jugadorServicio.deleteJugador(jugadorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //actualizar
    @PutMapping("{jugadorId}")
    public ResponseEntity<Jugador> actualizarJugador(@PathVariable Long jugadorId, @RequestBody Jugador jugador) {
        return new ResponseEntity<>(jugadorServicio.actualizarJugador(jugadorId, jugador), HttpStatus.OK);
    }

}
