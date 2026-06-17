package ucr.ac.cr.creativeSpace.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.creativeSpace.Model.DTO.ReservationDTO;
import ucr.ac.cr.creativeSpace.Model.Reservation;
import ucr.ac.cr.creativeSpace.Service.ReservationService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/reservation")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ReservationController {

    @Autowired
    private ReservationService services;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.services.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {

        ReservationDTO dto = this.services.findById(id);

        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la reservación");
        }

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveReservation(@Validated @RequestBody Reservation reservation, BindingResult result) {

        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();

            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(errors);
        }

        ReservationDTO dto = this.services.saveReservation(reservation);

        if (dto == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No se pudo crear la reservación");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editReservation(@Validated @PathVariable Integer id, @RequestBody Reservation reservation, BindingResult result) {

        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();

            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(errors);
        }

        ReservationDTO dto = this.services.findById(id);

        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reservación no existente");
        }

        return ResponseEntity.ok(
                this.services.editReservation(id, reservation)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Integer id) {

        ReservationDTO dto = this.services.findById(id);

        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La reservación no existe");
        }

        this.services.deleteReservation(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getReservationByUser(@PathVariable Integer id) {

        if (this.services.getReservationByUser(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontraron reservaciones");
        }

        return ResponseEntity.ok(
                this.services.getReservationByUser(id)
        );
    }

    @GetMapping("/space/{id}")
    public ResponseEntity<?> getReservationByCreativeSpace(@PathVariable Integer id) {

        if (this.services.getReservationByCreativeSpace(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontraron reservaciones");
        }

        return ResponseEntity.ok(
                this.services.getReservationByCreativeSpace(id)
        );
    }
}
