package com.example.demo.manytomany.way2;

import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import java.io.Serializable;

@EqualsAndHashCode
@Embeddable
public class CourseRatingId implements Serializable {


    Long userId;
    Long courseId;
}
