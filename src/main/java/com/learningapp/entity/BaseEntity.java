package com.learningapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Base entity class that provides common fields and functionality for all entities in the application.
 * This abstract class includes common fields like id, creation timestamp, update timestamp, and soft delete functionality.
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    /**
     * Unique identifier for the entity
     */
    @Id
    private String id;

    /**
     * Timestamp when the entity was created
     */
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Timestamp when the entity was last updated
     */
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * Timestamp when the entity was soft deleted
     */
    @Column(name = "delete_time")
    private LocalDateTime deleteTime;

    /**
     * Flag indicating whether the entity is deleted (0 = not deleted, 1 = deleted)
     */
    @Column(name = "is_delete", nullable = false)
    private Integer isDelete = 0;

    /**
     * Generates a UUID for the entity if one is not already set
     * This method is called automatically before persisting the entity
     */
    @PrePersist
    private void generateId() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }
}