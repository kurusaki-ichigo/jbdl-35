package com.example.demo.manytomany.way2;


import lombok.*;

import javax.persistence.*;
import java.time.Duration;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Courses {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long courseId;


    String name;
    Duration duration;
    Long price;


    @OneToMany(mappedBy = "courses")
    Set<CourseRating> ratingSet;


}
