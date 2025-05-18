package com.edutechinnovators.curso_service.controller;

import com.edutechinnovators.curso_service.model.Curso;
import com.edutechinnovators.curso_service.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> getAllCursos() {
        return ResponseEntity.ok(cursoService.getAllCursos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCursoById(@PathVariable Integer id) {
        Optional<Curso> curso = cursoService.getCursoById(id);
        return curso.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Curso> createCurso(@RequestBody Curso curso) {
        Curso nuevoCurso = cursoService.createCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCurso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> updateCurso(@PathVariable Integer id, @RequestBody Curso curso) {
        Curso cursoActualizado = cursoService.updateCurso(id, curso);
        if (cursoActualizado != null) {
            return ResponseEntity.ok(cursoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Integer id) {
        cursoService.deleteCurso(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Curso>> getCursosByNombreContaining(@PathVariable String nombre) {
        return ResponseEntity.ok(cursoService.findByNombreContaining(nombre));
    }

    @GetMapping("/instructor/{instructor}")
    public ResponseEntity<List<Curso>> getCursosByInstructor(@PathVariable String instructor) {
        return ResponseEntity.ok(cursoService.findByInstructor(instructor));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Curso>> getCursosByEstado(@PathVariable String estado) {
        return ResponseEntity.ok(cursoService.findByEstado(estado));
    }
}