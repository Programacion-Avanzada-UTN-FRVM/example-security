package com.example.audit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name = "example_entity")
/**
 * Decorador que vincula funcionalidad de escucha sobre las acciones realizadas
 * en la entidad.
 */
@EntityListeners(AuditingEntityListener.class)
public class ExampleEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * El identificador de la entidad.
     */
    private Long id;

    @Column(name = "name")
    /**
     * El nombre de la entidad.
     */
    private String name;

    @Column(name = "description")
    /**
     * La descripcion de la entidad.
     */
    private String description;

    @Column(name = "created_by")
    /** Este decorador audita el usuario que creo la entidad. */
    @CreatedBy
    /**
     * El usuario que creo la entidad.
     */
    private String createdBy;

    @Column(name = "updated_by", nullable = true)
    /**
     * Este decorador audita el usuario que actualizo la entidad, si es que hubo
     * una.
     */
    @LastModifiedBy
    /**
     * El usuario que actualizo la entidad.
     */
    private String updatedBy;

    @Column(name = "created_at")
    /** Decorador que audita la fecha de creacion. */
    @CreatedDate
    /**
     * La fecha y hora en la que se creo la entidad.
     */
    private String createdAt;

    @Column(name = "updated_at", nullable = true)
    /** Decorador que audita la fecha de actualizacion. */
    @LastModifiedDate
    /**
     * La fecha y hora en la que se actualizo la entidad.
     */
    private String updatedAt;

    @Column(name = "deleted_at", nullable = true)
    /**
     * La fecha y hora en la que se elimino la entidad.
     */
    private String deletedAt;

    @Column(name = "is_deleted")
    /**
     * Indica si la entidad fue eliminada, logicamente.
     */
    private boolean isDeleted;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void delete() {
        // Establecer flag de borrado logico.
        this.isDeleted = true;

        // Establecer fecha actual en formato ISO 8601.
        this.deletedAt = java.time.LocalDateTime.now().toString();
    }
}
