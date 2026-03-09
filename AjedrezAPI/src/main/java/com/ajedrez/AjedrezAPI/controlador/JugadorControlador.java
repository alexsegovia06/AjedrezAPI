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
        Jugador jugador = jugadorServicio.getJugador(jugadorId);
        if (jugador == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(jugador, HttpStatus.OK);
    }


    //publicar o anadir
    @PostMapping
    public ResponseEntity<Jugador> postJugador(@RequestBody Jugador jugador) {
        return new ResponseEntity<>(jugadorServicio.postJugador(jugador), HttpStatus.CREATED);
    }

    //eliminar
    @DeleteMapping("{jugadorId}")
    public ResponseEntity<String> deleteArtista(@PathVariable Long jugadorId) {
        boolean eliminado = jugadorServicio.deleteJugador(jugadorId);
        if (!eliminado) return new ResponseEntity<>("No se encontró al jugador", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>("Se elimino al jugador", HttpStatus.NO_CONTENT);
    }

    //actualizar
    @PutMapping("{jugadorId}")
    public ResponseEntity<Jugador> actualizarJugador(@PathVariable Long jugadorId, @RequestBody Jugador jugador) {
        Jugador actualizado = jugadorServicio.actualizarJugador(jugadorId, jugador);
        if (actualizado == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }

}
