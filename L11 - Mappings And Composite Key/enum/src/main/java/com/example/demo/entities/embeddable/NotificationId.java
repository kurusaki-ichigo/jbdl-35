package com.example.demo.entities.embeddable;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Generate Equals and hashCode
 * Implements serializable
 *
 */

@EqualsAndHashCode
@ToString
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class NotificationId implements Serializable {

    private static final long serialVersionUID = 5706988877334359930L;
    String idempotencyKey;
    String source;
}
