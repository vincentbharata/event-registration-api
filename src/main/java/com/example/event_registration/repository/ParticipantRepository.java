package com.example.event_registration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.event_registration.model.Event;
import com.example.event_registration.model.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    List<Participant> findByEvent(Event event);
}