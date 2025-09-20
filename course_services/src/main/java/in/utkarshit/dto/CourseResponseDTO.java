package in.utkarshit.dto;

import lombok.Data;

@Data
public class CourseResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String instructorEmail;
    private double price;
}
