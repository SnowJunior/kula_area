package com.nyabwana.kula_area;

public class ReviewModel {
    String restaurantName,review,userId, reviewId;
    public ReviewModel(String restaurantName, String review, String userId, String reviewId) {
        this.restaurantName = restaurantName;
        this.review = review;
        this.userId = userId;
        this.reviewId = reviewId;
    }

    public ReviewModel() {

    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }
}
