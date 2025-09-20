package in.utkarshit.dto;

import lombok.Data;

@Data
public class CourseRequestDTO {
    private String title;
    private String description;
    private String instructorEmail;
    private double price;
}

