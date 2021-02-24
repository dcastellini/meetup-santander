package com.santander.meetup.web.controller;


import com.santander.meetup.domain.Meetup;
import com.santander.meetup.domain.ResponseController;
import com.santander.meetup.domain.Usuario;
import com.santander.meetup.exception.DateException;
import com.santander.meetup.exception.MeetupException;
import com.santander.meetup.exception.UsuarioException;
import com.santander.meetup.service.CervezaService;
import com.santander.meetup.service.MeetupService;
import com.santander.meetup.service.UsuarioService;
import com.santander.meetup.service.WeatherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@RequestMapping("/meetups")
public class MeetupController {

    public static final int MINIMA_CANTIDAD_DE_PACKS = 1;

    public static final String ESTADO_OK = "200";

    @Autowired
    private MeetupService meetupService;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/crear_meetup")
    @ApiOperation("Crear una nueva Meetup")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "No se pudo crear la meetup")
    })
    public ResponseEntity<Meetup> crearMeetup(@RequestBody Meetup meetup) throws DateException, IOException {
        try{
            usuarioService.validoQueElUsuarioSeaAdministrador(meetup.getIdUsuarioCreador());
            meetupService.validoFechasDeLaMeetup(meetup);
            meetup.setTemperatura(weatherService.getWeatherForDateReceived(meetup));
            meetup.setCantidadDeCerveza(MINIMA_CANTIDAD_DE_PACKS);
            return new ResponseEntity<>(meetupService.save(meetup), HttpStatus.CREATED);
        } catch(DateException | UsuarioException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @GetMapping("/consultar_temperatura/{id}")
    @ApiOperation("Obtener la temperatura de una meetup")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "No se pudo obtener la temperatura")
    })
    public ResponseEntity<ResponseController> consultarTemperaturaDeUnaMeetup(@ApiParam(value="Id de la meetup a consultar", required = true) @PathVariable("id") Integer idMeetup) throws MeetupException {
        try{
            meetupService.validoQueExistaLaMeetup(idMeetup);
            return new ResponseEntity<>(new ResponseController(ESTADO_OK,meetupService.obtengoTemperaturaDeLaMeetup(idMeetup).toString()), HttpStatus.OK);
        } catch (MeetupException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @GetMapping("/consultar_cantidad_cervezas/{id}")
    @ApiOperation("Obtener la cantidad de cervezas que debo comprar para la meetup")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "No se pudo obtener la cantidad de cervezas")
    })
    public ResponseEntity<ResponseController> consultarLaCantidadDeCervezasDeUnaMeetup(@ApiParam(value="Id de la meetup a consultar", required = true) @PathVariable("id") Integer idMeetup, @RequestBody Usuario usuario) throws MeetupException {
        try{
            meetupService.validoQueExistaLaMeetup(idMeetup);
            usuarioService.validoQueElUsuarioSeaAdministrador(usuario.getIdUsuario());
            return new ResponseEntity<>(new ResponseController(ESTADO_OK,meetupService.obtengoCantidadDeCervezaParaUnaMeetup(idMeetup).toString()), HttpStatus.OK);
        } catch (MeetupException  | UsuarioException e ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }


}
