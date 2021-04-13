package com.vanderleks.hrworker.controller;

import com.vanderleks.hrworker.entities.Worker;
import com.vanderleks.hrworker.repositories.WorkerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;

@RestController
@RequestMapping("/workers")
public class WorkerController {
    
    @Autowired
    private WorkerRepository workerRepository;

    @GetMapping
    public ResponseEntity<Page<Worker>> findAll(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "5") int size,
        @RequestParam(value = "direction", defaultValue = "ASC") String direction,
        @RequestParam(value = "sort", defaultValue = "name") String sort
        ){
        PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), sort);
        return ResponseEntity.ok(workerRepository.findAll(pageRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> findById(@PathVariable Long id) throws NotFoundException{
        Worker worker = workerRepository.findById(id).get();
        return ResponseEntity.ok().body(worker);
    }
}
