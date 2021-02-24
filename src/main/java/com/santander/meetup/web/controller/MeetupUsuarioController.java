package com.santander.meetup.web.controller;


import com.santander.meetup.domain.MeetupUsuario;
import com.santander.meetup.domain.ResponseController;
import com.santander.meetup.domain.Usuario;
import com.santander.meetup.service.CervezaService;
import com.santander.meetup.service.MeetupService;
import com.santander.meetup.service.MeetupUsuarioService;
import com.santander.meetup.service.UsuarioService;
import com.santander.meetup.exception.DateException;
import com.santander.meetup.exception.MeetupException;
import com.santander.meetup.exception.UsuarioException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/meetups_usuarios")
public class MeetupUsuarioController {

    public static final String MENSAJE_INSCRIPCION_OK = "Inscripción a la meetup realizada correctamente.";

    public static final String MENSAJE_CHECKIN_OK = "Check-in realizado correctamente.";

    public static final String ESTADO_OK = "200";

    @Autowired
    private MeetupUsuarioService meetupUsuarioService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MeetupService meetupService;

    @Autowired
    private CervezaService cervezaService;

    @PostMapping("/inscribirme_meetup/{id}")
    @ApiOperation("Inscribirme a una meetup")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "No se pudo inscribir a la meetup.")
    })
    public ResponseEntity<ResponseController> inscripcionAMeetup(@ApiParam(value="Id de la meetup a inscribirme", required = true) @PathVariable("id") Integer idMeetup, @RequestBody Usuario usuario) {
        try {
            meetupService.validoQueExistaLaMeetup(idMeetup);
            usuarioService.validoQueElUsuarioNoSeaAdministrador(usuario.getIdUsuario());
            usuarioService.validoQueNoExistaElUsuario(usuario.getUsuario());
            meetupService.validoQueLaMeetupNoSeaPasada(idMeetup);
            meetupUsuarioService.asignarUsuarioMeetup(idMeetup,usuario.getIdUsuario());
            Integer cantidadDeCervezaActualizada = cervezaService.calcularCantidadDeCervezaParaLaMeetup(idMeetup);
            meetupService.actualizoCantidadDeCervezaParaLaMeetup(cantidadDeCervezaActualizada,idMeetup);
            return new ResponseEntity<>(new ResponseController(ESTADO_OK,MENSAJE_INSCRIPCION_OK),HttpStatus.CREATED);
        } catch (UsuarioException | MeetupException | DateException e)  {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PostMapping("/checkin_meetup/{id}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "No se pudo realizar check-in a la meetup.")
    })
    public ResponseEntity<ResponseController> checkInMeetup(@ApiParam(value="Id de la meetup a realizar el check-in", required = true) @PathVariable("id") Integer idMeetup, @RequestBody Usuario usuario){
        try {
            meetupService.validoQueExistaLaMeetup(idMeetup);
            usuarioService.validoQueNoExistaElUsuario(usuario.getUsuario());
            usuarioService.validoQueElUsuarioNoSeaAdministrador(usuario.getIdUsuario());
            meetupService.validoQueLaMeetupYaHaFinalizado(idMeetup);
            meetupUsuarioService.checkInMeetup(idMeetup,usuario.getIdUsuario());
            return new  ResponseEntity<>(new ResponseController(ESTADO_OK,MENSAJE_CHECKIN_OK), HttpStatus.CREATED);
        } catch (UsuarioException  | MeetupException  | DateException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }





}
