package com.giancarlo.gilos.jreactivities.controllers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import com.giancarlo.gilos.jreactivities.entities.Activity;
import com.giancarlo.gilos.jreactivities.repositories.ActivityRepository;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ActivitiesControllerTest {

  private static final String basePath = "/api/activities";

  @LocalServerPort
  private Integer port;

  static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
      "postgres:16-alpine");

  @BeforeAll
  static void beforeAll() {
    postgres.start();
  }

  @AfterAll
  static void afterAll() {
    postgres.stop();
  }

  @DynamicPropertySource
  static void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgres::getJdbcUrl);
    registry.add("spring.datasource.username", postgres::getUsername);
    registry.add("spring.datasource.password", postgres::getPassword);
  }

  @Autowired
  ActivityRepository activitiesRepository;

  @BeforeEach
  void setUp() {
    RestAssured.baseURI = String.format("http://localhost:%d", port);
    activitiesRepository.deleteAll();
  }

  @Test
  void shouldGetAllActivities() {
    final var activities = List.of(
        new Activity(
            UUID.fromString("50c3d35f-965f-455e-9f2a-39cc822dcec0"), "Future Activity 1",
            LocalDateTime.now().plusMonths(1), "1 month in future", "music", "Cebu City", "SM Grand Arena"),
        new Activity(
            UUID.fromString("e60d1d06-2f80-4931-8585-80e1c23115c0"), "Future Activity 2",
            LocalDateTime.now().plusMonths(2), "2 months in future", "meetup", "Cebu City", "SM Seaside"));

    for (final var activity : activities) {
      activity.reactivateActivity();
    }

    activitiesRepository.saveAll(activities);

    given()
        .contentType(ContentType.JSON)
        .when()
        .get(basePath)
        .then()
        .statusCode(200)
        .body("data.[0].id", equalTo("50c3d35f-965f-455e-9f2a-39cc822dcec0"))
        .body("data.[1].id", equalTo("e60d1d06-2f80-4931-8585-80e1c23115c0"))
        .body("data", hasSize(2));

  }

  @Test
  public void shouldGetActivityById() {
    final var activityId = UUID.fromString("e60d1d17-2f80-4931-8585-80e1c23115c0");
    final var path = String.format("%s/%s", basePath, activityId);
    final var activity = new Activity(
        activityId, "Convention something",
        LocalDateTime.now().plusMonths(2), "2 months in future", "meetup", "Cebu City", "SM Seaside");

    activity.reactivateActivity();

    activitiesRepository.save(activity);

    given()
        .contentType(ContentType.JSON)
        .when()
        .get(path)
        .then()
        .statusCode(200)
        .body("data.id", equalTo(activityId.toString()))
        .body("data.attributes.title", equalTo("Convention something"))
        .body("data.attributes.description", equalTo("2 months in future"));
  }

  @Test
  public void shouldReturnErrorIfActivityNotFound() {
    final var nonexistentId = UUID.fromString("00000000-0000-0000-0000-000000000000");
    final var path = String.format("%s/%s", basePath, nonexistentId);

    given()
        .contentType(ContentType.JSON)
        .when()
        .get(path)
        .then()
        .statusCode(404)
        .body("errors[0].status", equalTo("404"))
        .body("errors[0].source", equalTo("/data/attributes/id"))
        .body("errors[0].title", equalTo("Activity not found"))
        .body("errors[0].detail", equalTo("Activity with id: 00000000-0000-0000-0000-000000000000 not found"));
  }
}
