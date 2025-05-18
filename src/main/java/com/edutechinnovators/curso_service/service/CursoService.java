package com.edutechinnovators.curso_service.service;

import com.edutechinnovators.curso_service.model.Curso;
import com.edutechinnovators.curso_service.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> getAllCursos() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> getCursoById(Integer id) {
        return cursoRepository.findById(id);
    }

    public Curso createCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso updateCurso(Integer id, Curso curso) {
        Optional<Curso> cursoExistente = cursoRepository.findById(id);
        if (cursoExistente.isPresent()) {
            Curso cursoActualizado = cursoExistente.get();
            cursoActualizado.setNombre(curso.getNombre());
            cursoActualizado.setDescripcion(curso.getDescripcion());
            cursoActualizado.setFechaInicio(curso.getFechaInicio());
            cursoActualizado.setFechaFin(curso.getFechaFin());
            cursoActualizado.setInstructor(curso.getInstructor());
            cursoActualizado.setPrecio(curso.getPrecio());
            cursoActualizado.setEstado(curso.getEstado());
            return cursoRepository.save(cursoActualizado);
        } else {
            return null; // Manejar el caso de curso no encontrado
        }
    }

    public void deleteCurso(Integer id) {
        cursoRepository.deleteById(id);
    }

    public List<Curso> findByNombreContaining(String nombre) {
        return cursoRepository.findByNombreContaining(nombre);
    }

    public List<Curso> findByInstructor(String instructor) {
        return cursoRepository.findByInstructor(instructor);
    }

    public List<Curso> findByEstado(String estado) {
        return cursoRepository.findByEstado(estado);
    }
}