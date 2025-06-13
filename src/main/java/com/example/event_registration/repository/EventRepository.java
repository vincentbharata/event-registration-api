package com.example.event_registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.event_registration.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
