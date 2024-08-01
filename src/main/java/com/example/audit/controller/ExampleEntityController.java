package com.example.audit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.example.audit.entity.ExampleEntity;
import com.example.audit.repository.ExampleEntityRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/entities")
public class ExampleEntityController {

    @Autowired
    private ExampleEntityRepository repository;

    @GetMapping
    public ResponseEntity<List<ExampleEntity>> getAllEntities() {
        // Buscar todas las entidades que no han sido borradas.
        List<ExampleEntity> entities = repository.findByIsDeletedFalse();

        // Responder con la lista de entidades.
        return ResponseEntity.ok(entities);
    }
    

    @PostMapping
    public ResponseEntity<ExampleEntity> createEntity(@RequestBody ExampleEntity entity) {
        // Crear la nueva entidad.
        ExampleEntity savedEntity = repository.save(entity);
        return ResponseEntity.ok(savedEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExampleEntity> updateEntity(
            @PathVariable Long id, @RequestBody ExampleEntity entityDetails) {
        // Buscar la entidad a traves del repositorio (por ID).
        Optional<ExampleEntity> optionalEntity = repository.findByIdAndIsDeletedFalse(id);

        // Verificar si la entidad existe.
        if (optionalEntity.isPresent()) {
            ExampleEntity entity = optionalEntity.get();

            // Actualizar los campos de la entidad.
            entity.setName(entityDetails.getName());
            entity.setDescription(entityDetails.getDescription());

            // Guardar la entidad actualizada.
            ExampleEntity updatedEntity = repository.save(entity);

            // Responder con 200 y la entidad actualizada.
            return ResponseEntity.ok(updatedEntity);
        } else {
            // Responder con 404 si la entidad no existe.
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable Long id, Authentication authentication) {
        // Buscar la entidad a traves del repositorio (por ID).
        Optional<ExampleEntity> optionalEntity = repository.findByIdAndIsDeletedFalse(id);

        // Verificar si la entidad existe.
        if (optionalEntity.isPresent()) {
            ExampleEntity entity = optionalEntity.get();

            // Hacer el borrado logico de la entidad.
            entity.delete();

            // Guardar la entidad actualizada.
            repository.save(entity);
            return ResponseEntity.noContent().build();
        } else {
            // Responder con 404 si la entidad no existe.
            return ResponseEntity.notFound().build();
        }
    }
}
