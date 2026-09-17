package com.giancarlo.gilos.jreactivities.seeder;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.giancarlo.gilos.jreactivities.entities.Activity;
import com.giancarlo.gilos.jreactivities.repositories.ActivityRepository;

@Component
public class Seeder implements CommandLineRunner {

    private final ActivityRepository activityRepository;

    public Seeder(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedActivities();
    }

    public void seedActivities() {
        System.out.println("Deleting all existing activities...");
        
        activityRepository.deleteAll();

        System.out.println("All existing activities deleted.");

        System.out.println("Seeding activities...");

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

        activityRepository.saveAll(activities);
        
        System.out.println("Finished seeding activities.");
    }

}
