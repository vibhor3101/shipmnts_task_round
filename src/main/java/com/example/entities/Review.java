package com.example.entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name="review")
@Data
public class Review {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="review_id")
    private Long reviewId;
    @Column(name="rating")
    private double rating;
    @Column(name="book_id")
    private Long bookId;
    @Column(name="comment")
    private String comment;



}