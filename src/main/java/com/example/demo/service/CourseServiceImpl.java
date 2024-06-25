package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Courses;
import com.example.demo.exception.CourseNotFoundException;
import com.example.demo.repository.CourseRepository;


@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Courses saveCourse(Courses course) {
        return courseRepository.save(course);
    }

    @Override
    public Optional<Courses> getCourseById(Integer id) {
        return courseRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
        } else {
            throw new CourseNotFoundException("Course not found with id: " + id);
        }
    }
}

