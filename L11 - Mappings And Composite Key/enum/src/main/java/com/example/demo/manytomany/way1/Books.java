package com.example.demo.manytomany.way1;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Books {


    @Version
    long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long bookId;

    String name;
    String isbn;

    double amount;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;

    @ManyToMany(mappedBy = "likedBooks")
    Set<Users> likedBy;

}
