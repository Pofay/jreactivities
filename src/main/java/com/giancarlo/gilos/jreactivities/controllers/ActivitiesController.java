package com.giancarlo.gilos.jreactivities.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.giancarlo.gilos.jreactivities.DTOs.ActivityDto;
import com.giancarlo.gilos.jreactivities.repositories.ActivityRepository;

import nl.michelbijnen.jsonapi.parser.JsonApiConverter;

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
  public ResponseEntity<String> getById(@PathVariable("id") String id) {
    return repo.findById(UUID.fromString(id))
        .map(ActivityDto::fromActivity)
        .map(JsonApiConverter::convert)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

}