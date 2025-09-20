package in.utkarshit.service;



import in.utkarshit.dto.CourseRequestDTO;
import in.utkarshit.dto.CourseResponseDTO;

import java.util.List;

public interface CourseService {
    CourseResponseDTO addCourse(CourseRequestDTO dto);
    CourseResponseDTO updateCourse(Long id, CourseRequestDTO dto);
    void deleteCourse(Long id);
    CourseResponseDTO getCourseById(Long id);
    List<CourseResponseDTO> getAllCourses();
    List<CourseResponseDTO> getCoursesByInstructor(String email);
}

