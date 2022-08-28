package com.example.mappings.mappings.entities;

import com.example.mappings.mappings.enums.BookStatus;
//import com.example.mappings.mappings.jpalistener.BookListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
//@EntityListeners(BookListener.class)
public class Books {


    @Version
    long version;

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

    /**
     *
     * Events transmitted around the JPAEntity lifecycle
     *                          (isNew)
     *         pre persist <---/        \----> Post persist
     *
     *                          (updated)
     *         preUpdate<------/        \-----> Post Update
     *         preRemove  <-----/            \------> postremove
     *
     *
     */
    @PrePersist
    private void markStatusAvailable(){
        this.bookStatus = BookStatus.AVAILABLE;
    }



}
