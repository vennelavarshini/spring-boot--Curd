package com.example.demo.controller;

import com.example.demo.entity.Courses;
import com.example.demo.exception.CourseNotFoundException;
import com.example.demo.service.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
public class CoursesController {

	@Autowired
    private final CourseService courseService;

    public CoursesController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/courses")
    public ResponseEntity<Courses> saveCourse(@RequestBody Courses course) {
        Courses savedCourse = courseService.saveCourse(course);
        return ResponseEntity.ok(savedCourse);
    }

    @GetMapping("/getcoursebyId/{id}")
    public ResponseEntity<Optional<Courses>> getCourseById(@PathVariable Integer id) {
        Optional<Courses> course = courseService.getCourseById(id);
        if (course.isPresent()) {
            return ResponseEntity.ok(course);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    

    @DeleteMapping("/deletecoursebyId/{id}")
    public ResponseEntity<?> deleteCourseById(@PathVariable Integer id) {
        try {
            courseService.delete(id);
            return ResponseEntity.ok().build();
        } catch (CourseNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    
    }
  
    @PutMapping("/courseId/{id}")
    public ResponseEntity<Courses> update(@PathVariable("id") int id, @RequestBody Courses course) {
        Optional<Courses> exCourse = courseService.getCourseById(id);
        if (!exCourse.isPresent()) {
            throw new CourseNotFoundException("Course not found with id: " + id);
        }

        Courses existingCourse = exCourse.get();
        existingCourse.setName(course.getName());
        existingCourse.setDescription(course.getDescription());

        courseService.saveCourse(existingCourse);

        return ResponseEntity.ok(existingCourse);
    }
   }





