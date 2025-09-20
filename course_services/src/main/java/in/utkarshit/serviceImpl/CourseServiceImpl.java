package in.utkarshit.serviceImpl;


import in.utkarshit.dto.CourseRequestDTO;
import in.utkarshit.dto.CourseResponseDTO;
import in.utkarshit.entity.Course;
import in.utkarshit.exception.CourseNotFoundException;
import in.utkarshit.repository.CourseRepository;
import in.utkarshit.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseResponseDTO addCourse(CourseRequestDTO dto){
        Course course = new Course();
        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setInstructorEmail(dto.getInstructorEmail());
        course.setPrice(dto.getPrice());
        courseRepository.save(course);
        return mapToResponse(course);
    }

    @Override
    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO dto){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));
        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setPrice(dto.getPrice());
        courseRepository.save(course);
        return mapToResponse(course);
    }

    @Override
    public void deleteCourse(Long id){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));
        courseRepository.delete(course);
    }

    @Override
    public CourseResponseDTO getCourseById(Long id){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));
        return mapToResponse(course);
    }

    @Override
    public List<CourseResponseDTO> getAllCourses(){
        return courseRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public List<CourseResponseDTO> getCoursesByInstructor(String email){
        return courseRepository.findByInstructorEmail(email)
                .stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private CourseResponseDTO mapToResponse(Course course){
        CourseResponseDTO res = new CourseResponseDTO();
        res.setId(course.getId());
        res.setTitle(course.getTitle());
        res.setDescription(course.getDescription());
        res.setInstructorEmail(course.getInstructorEmail());
        res.setPrice(course.getPrice());
        return res;
    }
}

