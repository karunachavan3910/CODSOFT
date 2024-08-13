package com.example.demo.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Course;
import com.example.demo.serviceInterface.CourseService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }
    

    @GetMapping("/schedule")
    public List<Course> findCoursesWithinDateRange(
        @RequestParam LocalDate startDate,
        @RequestParam LocalDate endDate
    ) {
        return courseService.findCoursesWithinDateRange(startDate, endDate);
    }
    
    @GetMapping("/available")
    public List<Course> getAvailableCourses() {
        return courseService.getAllCourses().stream()
                .filter(course -> course.getStudents().size() < course.getCapacity())
                .collect(Collectors.toList());
    }

}
