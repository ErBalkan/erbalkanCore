package com.erbalkan.domain;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@MappedSuperclass
// Bu sınıfın veritabanında tablo karşılığı yoktur, 
// ama alt sınıflar bu alanları miras alır.
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long _id;

    @Column(nullable=false,updatable=false)
    private Instant _createdAt;

    @Column(nullable=false)
    private Instant _updatedAt;

    @Column(nullable=false)
    private boolean _isDeleted = false;

    @PrePersist
    protected void onCreate(){
        Instant now = Instant.now();
        _createdAt = now;
        _updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate(){
        _updatedAt = Instant.now();
    }

    // Getters and Setters
    public Long getId(){return _id;}
    public Instant getCreatedAt(){return _createdAt;}
    public Instant getUpdatedAt(){return _updatedAt;}
    public boolean isDeleted(){return _isDeleted;}
    public void setDeleted(boolean deleted){
        _isDeleted = deleted;
    }
}
