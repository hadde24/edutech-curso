package com.edutechinnovators.curso_service.repository;

import com.edutechinnovators.curso_service.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
    List<Curso> findByNombreContaining(String nombre);
    List<Curso> findByInstructor(String instructor);
    List<Curso> findByEstado(String estado);
}
