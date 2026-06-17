package ucr.ac.cr.creativeSpace.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.creativeSpace.Model.CreativeSpace;
import ucr.ac.cr.creativeSpace.Model.DTO.CreativeSpaceDTO;
import ucr.ac.cr.creativeSpace.Service.CreativeSpaceService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/spaces")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class CreativeSpaceController {

    @Autowired
    private CreativeSpaceService services;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.services.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIDCreativeSpace(@PathVariable Integer id) {

        CreativeSpaceDTO dto = this.services.findByIDCreativeSpace(id);

        if(dto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el espacio creativo");
        }

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveCreativeSpace(@Validated @RequestBody CreativeSpace creativeSpace,
                                               BindingResult result) {

        if(result.hasErrors()){
            Map<String,String> errors = new HashMap<>();

            for(FieldError error : result.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(errors);
        }

        CreativeSpaceDTO dto = this.services.saveCreativeSpace(creativeSpace);

        if(dto == null){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Espacio creativo ya existente.");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editCreativeSpace(@Validated @PathVariable Integer id,
                                               @RequestBody CreativeSpace creativeSpace,
                                               BindingResult result) {

        if(result.hasErrors()){
            Map<String,String> errors = new HashMap<>();

            for(FieldError error : result.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(errors);
        }

        CreativeSpaceDTO dto = this.services.findByIDCreativeSpace(id);

        if(dto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Espacio creativo no existente");
        }

        return ResponseEntity.ok(
                this.services.editCreativeSpace(id, creativeSpace)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCreativeSpace(@PathVariable Integer id) {

        CreativeSpaceDTO dto = this.services.findByIDCreativeSpace(id);

        if(dto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El espacio creativo no existe");
        }

        this.services.deleteCreativeSpace(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<?> findByType(@PathVariable String type){

        if(this.services.findByType(type).isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("No se encontraron resultados");
        }

        return ResponseEntity.ok(this.services.findByType(type));
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<?> findByLocation(@PathVariable String location){

        if(this.services.findByLocation(location).isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("No se encontraron resultados");
        }

        return ResponseEntity.ok(this.services.findByLocation(location));
    }
}
