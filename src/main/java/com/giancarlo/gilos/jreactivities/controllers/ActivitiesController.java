package com.giancarlo.gilos.jreactivities.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.giancarlo.gilos.jreactivities.entities.Activity;
import com.giancarlo.gilos.jreactivities.repositories.ActivityRepository;

@RestController
public class ActivitiesController {

  private final ActivityRepository repo;

  public ActivitiesController(ActivityRepository repo) {
    this.repo = repo;
  }


  @GetMapping("/api/activities")
  public List<Activity> getAll() {
    return repo.findAll();
  }

  @GetMapping("/api/activities/{id}")
  public Activity getById(@PathVariable("id") String id) {
    return repo.findById(UUID.fromString(id)).orElse(null);
  }

}