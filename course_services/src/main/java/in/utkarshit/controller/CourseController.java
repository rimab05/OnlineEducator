package in.utkarshit.controller;


import in.utkarshit.dto.CourseRequestDTO;
import in.utkarshit.dto.CourseResponseDTO;
import in.utkarshit.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    public CourseController(CourseService courseService){ this.courseService = courseService; }

    @PostMapping("/add")
    public CourseResponseDTO addCourse(@RequestBody CourseRequestDTO dto){
        return courseService.addCourse(dto);
    }

    @PutMapping("/update/{id}")
    public CourseResponseDTO updateCourse(@PathVariable Long id, @RequestBody CourseRequestDTO dto){
        return courseService.updateCourse(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);
        return "Course deleted successfully";
    }

    @GetMapping("/{id}")
    public CourseResponseDTO getCourse(@PathVariable Long id){
        return courseService.getCourseById(id);
    }

    @GetMapping("/all")
    public List<CourseResponseDTO> getAllCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping("/instructor")
    public List<CourseResponseDTO> getCoursesByInstructor(@RequestParam String email){
        return courseService.getCoursesByInstructor(email);
    }
}
