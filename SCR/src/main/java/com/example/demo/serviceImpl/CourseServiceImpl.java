package com.example.demo.serviceImpl;





import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Course;
import com.example.demo.repo.CourseRepo;
import com.example.demo.serviceInterface.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	
	

	    @Autowired
	    private CourseRepo courseRepo;

	    @Override
	    public List<Course> getAllCourses() {
	        return courseRepo.findAll();
	    }

	    @Override
	    public Course getCourseById(Long id) {
	        return courseRepo.findById(id).orElse(null);
	    }

	    @Override
	    public Course saveCourse(Course course) {
	        return courseRepo.save(course);
	    }

	    @Override
	    public void deleteCourse(Long id) {
	        courseRepo.deleteById(id);
	    }

	    @Override
	    public List<Course> findCoursesWithinDateRange(LocalDate startDate, LocalDate endDate) {
	        return courseRepo.findCoursesWithinDateRange(startDate, endDate);
	    }
	}