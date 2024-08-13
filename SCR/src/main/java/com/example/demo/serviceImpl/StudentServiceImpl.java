package com.example.demo.serviceImpl;







import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import com.example.demo.repo.CourseRepo;
import com.example.demo.repo.StudentRepo;

import com.example.demo.serviceInterface.StudentService;

@Service
public class StudentServiceImpl implements StudentService{


	
	    @Autowired
	    private StudentRepo studentRepository;

	    @Autowired
	    private CourseRepo courseRepository;

	    @Override
	    public List<Student> getAllStudents() {
	        return studentRepository.findAll();
	    }

	    @Override
	    public Student getStudentById(Long id) {
	        return studentRepository.findById(id).orElse(null);
	    }

	    @Override
	    public Student saveStudent(Student student) {
	        return studentRepository.save(student);
	    }

	    @Override
	    public void deleteStudent(Long id) {
	        studentRepository.deleteById(id);
	    }

//	    @Override
//	    public Student registerCourse(Long studentId, Long courseId) {
//	        Student student = studentRepository.findById(studentId).orElse(null);
//	        Course course = courseRepository.findById(courseId).orElse(null);
//
//	        if (student != null && course != null && course.getStudents().size() < course.getCapacity()) {
//	            student.getCourses().add(course);
//	            course.getStudents().add(student);
//	            studentRepository.save(student);
//	            courseRepository.save(course);
//	        }
//
//	        return student;
//	    }
	    @Override
	    public String registerCourse(Long studentId, Long courseId) {
	        Student student = studentRepository.findById(studentId).orElse(null);
	        Course course = courseRepository.findById(courseId).orElse(null);

	        if (student != null && course != null) {
	            if (course.getStudents().size() >= course.getCapacity()) {
	                return "Course is full";
	            }
	            student.getCourses().add(course);
	            course.getStudents().add(student);
	            studentRepository.save(student);
	            courseRepository.save(course);
	            return "Registered successfully";
	        }

	        return "Student or Course not found";
	    }


	    @Override
	    public Student dropCourse(Long studentId, Long courseId) {
	        Student student = studentRepository.findById(studentId).orElse(null);
	        Course course = courseRepository.findById(courseId).orElse(null);

	        if (student != null && course != null) {
	            student.getCourses().remove(course);
	            course.getStudents().remove(student);
	            studentRepository.save(student);
	            courseRepository.save(course);
	        }

	        return student;
	    }
	}