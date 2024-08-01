package com.example.audit.repository;

import com.example.audit.entity.ExampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExampleEntityRepository extends JpaRepository<ExampleEntity, Long> {

    /**
     * Busca todas las entidades que no han sido borradas.
     *
     * @return Lista de entidades no borradas.
     */
    List<ExampleEntity> findByIsDeletedFalse();

    /**
     * Busca una entidad por su identificador y que no haya sido borrada.
     *
     * @param id El identificador de la entidad.
     * 
     * @return La entidad encontrada.
     */
    Optional<ExampleEntity> findByIdAndIsDeletedFalse(Long id);
}