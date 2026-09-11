package com.giancarlo.gilos.jreactivities.DTOs;

import java.time.LocalDateTime;
import java.util.UUID;

import com.giancarlo.gilos.jreactivities.entities.Activity;

import nl.michelbijnen.jsonapi.annotation.JsonApiId;
import nl.michelbijnen.jsonapi.annotation.JsonApiObject;
import nl.michelbijnen.jsonapi.annotation.JsonApiProperty;
import nl.michelbijnen.jsonapi.generator.JsonApiDtoExtendable;

@JsonApiObject("Activity")
public class ActivityDto extends JsonApiDtoExtendable {

    @JsonApiId
    private String id;

    @JsonApiProperty
    private String title;

    @JsonApiProperty
    private String description;

    @JsonApiProperty
    private String category;

    @JsonApiProperty
    private String city;

    @JsonApiProperty
    private String venue;

    @JsonApiProperty
    private LocalDateTime date;

    @JsonApiProperty
    private Boolean isCancelled;

    public static ActivityDto fromActivity(Activity activity) {
        return new ActivityDto(
                activity.getId(),
                activity.getTitle(),
                activity.getDescription(),
                activity.getCategory(),
                activity.getCity(),
                activity.getVenue(),
                activity.getDate(),
                activity.isCancelled());
    }

    private ActivityDto(UUID id, String title, String description, String category, String city, String venue,
            LocalDateTime date, Boolean isCancelled) {
        this.id = id.toString();
        this.title = title;
        this.description = description;
        this.category = category;
        this.city = city;
        this.venue = venue;
        this.date = date;
        this.isCancelled = isCancelled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }   

    public void setTitle(String title) {
        this.title = title;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Boolean getIsCancelled() {
        return isCancelled;
    }   

    public void setIsCancelled(Boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

}
