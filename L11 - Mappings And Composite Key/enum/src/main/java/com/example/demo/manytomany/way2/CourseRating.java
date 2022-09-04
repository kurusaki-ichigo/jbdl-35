package com.example.demo.manytomany.way2;


import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRating {



    @EmbeddedId
    CourseRatingId ratingId;

    int ratings;
    String remarks;

    @ManyToOne
    @MapsId(value = "userId")
    Users users;


    @ManyToOne
    @MapsId(value = "courseId")
    Courses courses;


}
