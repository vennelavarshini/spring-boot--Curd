package com.example.demo.service;

import com.example.demo.entity.Courses;
import java.util.Optional;

public interface CourseService {
    Courses saveCourse(Courses course);
    Optional<Courses> getCourseById(Integer id);
    void delete(Integer id);
}

