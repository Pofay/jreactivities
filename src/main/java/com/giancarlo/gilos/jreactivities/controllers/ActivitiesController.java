package com.giancarlo.gilos.jreactivities.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.giancarlo.gilos.jreactivities.DTOs.ActivityDto;
import com.giancarlo.gilos.jreactivities.DTOs.ErrorContainerDto;
import com.giancarlo.gilos.jreactivities.DTOs.ErrorDto;
import com.giancarlo.gilos.jreactivities.repositories.ActivityRepository;

import nl.michelbijnen.jsonapi.parser.JsonApiConverter;
import tools.jackson.databind.ObjectMapper;

@RestController
public class ActivitiesController {

  private final ActivityRepository repo;

  public ActivitiesController(ActivityRepository repo) {
    this.repo = repo;
  }

  @GetMapping(value = "/api/activities", produces = "application/vnd.api+json")
  public ResponseEntity<String> getAll() {
    final var activities = repo.findAll();
    final var activityDtos = activities.stream().map(ActivityDto::fromActivity).toList();
    return ResponseEntity.ok(JsonApiConverter.convert(activityDtos));
  }

  @GetMapping(value = "/api/activities/{id}", produces = "application/vnd.api+json")
  public ResponseEntity<?> getById(@PathVariable("id") String id) {
    final var activity = repo.findById(UUID.fromString(id))
        .map(ActivityDto::fromActivity);
    if (activity.isPresent()) {
      return ResponseEntity.ok(JsonApiConverter.convert(activity.get()));
    } else {
      return notFound(id);
    }
  }

  private ResponseEntity<?> notFound(String id) {
    final var errorDto = ErrorContainerDto.wrap(
        new ErrorDto("404", "Activity not found", "id", String.format("Activity with id: %s not found", id)));

    return ResponseEntity.status(404).body(errorDto);
  }

}