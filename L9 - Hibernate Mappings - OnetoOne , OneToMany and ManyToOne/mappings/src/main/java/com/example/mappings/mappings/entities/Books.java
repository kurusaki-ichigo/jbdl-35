package com.example.mappings.mappings.entities;

import com.example.mappings.mappings.enums.BookStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Books {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long bookId;

    String name;
    String isbn;

    @Enumerated(value = EnumType.STRING)
    BookStatus bookStatus;

    double amount;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;


    /**
     *
     *          Book ---- Author
     *          N<--------- 1Author
     *          -----------------
     *
     *
     *
     *
     *
     */
    @ManyToOne
    @JoinColumn
    private Authors associatedAuthor;

    @ManyToOne
    @JoinColumn
    private Orders order;

}
