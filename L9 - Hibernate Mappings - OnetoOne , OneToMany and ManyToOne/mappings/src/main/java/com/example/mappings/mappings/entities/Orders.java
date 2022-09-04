package com.example.mappings.mappings.entities;

import com.example.mappings.mappings.enums.OrderStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long orderId;

    @Enumerated(value = EnumType.STRING)
    OrderStatus orderStatus;

    double amount;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    Users user;

/**
 * Order book
 * 1 Order  may books
 * 1 book can have 1 active order
 *
 *
 *
 * Two types of mapping
 *  - Loosesly couple - one/ uni directional
 *
 *  Tightly Coupled - bi directional
 *
 * OneToMany
 * Bidirectional -- gives more control
 *
 *
 *
 *  Order -> List of Books
 *
 *
 *  order ([B1, B2,  B3])
 *
 *  1)
 *  (order)                     Book
 *  (order1, 100, [B1, B2, B3])      (B1, B1_name, B1_ISBN)
 *                              (B2, B2_name, B2_ISBN)
 *                              (B3, B3_name, B3_ISBN)
 *
 *
 *                              vs
 *
 *    2)
 *
 *    order                     (B1, B1_name, B1_ISBN, Order1)
 *    (order1, 10)              (B2, B2_name, B2_ISBN, Order1)
 *                              (B3, B3_name, B3_ISBN, Order1)
 *
 *                                                                          3)
 *                                                                          Latest
 *                                                                          Extracting the mapping out
 *                                                                              (orderId , BookId) --mapping
 *
 *  books [Or1]


 * 	1) 15 -- Best Design
 *	(B1, b1_isb1 , b1_name1 , A1 (foreign key) -- @JoinColumn)
 *	(B2, b2_isb1 , b2_name1, A2)				(A1 , A1_name , A1_email)
 *	(B3, b3_isb1 , b3_name1, A1)				(A2 , A2_name , A2_email)
 *
 *
 *
 * 2)   2
 *	(B1, b1_isb1 , b1_name1 )
 *	(B2, b2_isb1 , b2_name1)				(A1 , A1_name , A1_email, [B1, B3])
 *	(B3, b3_isb1 , b3_name1)				(A2 , A2_name , A2_email, B2)


 *
 *
 */

    @OneToMany(mappedBy = "order")
    List<Books> booksList;


}
