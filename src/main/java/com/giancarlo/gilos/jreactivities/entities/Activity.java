package com.giancarlo.gilos.jreactivities.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "activities")
public class Activity {

  @Id
  private UUID id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private LocalDateTime date;

  @Column(nullable = true)
  private String description;

  @Column(nullable = true)
  private String category;

  @Column(nullable = true)
  private String city;  

  @Column(nullable = true)
  private String venue;

  @Column(nullable = false, name = "is_cancelled")
  private Boolean isCancelled = false;

  public Activity() {}

  public Activity(UUID id, String title, LocalDateTime date, String description, String category, String city, String venue) {
    this.id = id;
    this.title = title;
    this.date = date;
    this.description = description;
    this.category = category;
    this.city = city;
    this.venue = venue;
    this.isCancelled = false;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getVenue() {
    return venue;
  }

  public void setVenue(String venue) {
    this.venue = venue;
  }

  @Override 
  public boolean equals(Object o) {
    if(this == o) return true;
    if(o == null || getClass() != o.getClass()) return false;
    
    final var that = (Activity) o;

    if(this.id == null || that.id == null) return false;
    
    return this.id.equals(that.id);
  }

  @Override 
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }

  public void cancelActivity() {
    isCancelled = true;
  }

  public void reactivateActivity() {
    isCancelled = false;
  }

  public boolean isCancelled() {
    return isCancelled;
  }

  public boolean isActive() {
    return !isCancelled;
  }
}
