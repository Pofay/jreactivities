package com.giancarlo.gilos.jreactivities.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giancarlo.gilos.jreactivities.entities.Activity;

public interface ActivityRepository extends JpaRepository<Activity, UUID> {}
