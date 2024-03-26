package com.jobapp.reviewms.review.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ReviewMessage {

    private Long Id;
    private String title;
    private String description;
    private double rating;
    Long companyId;
}
