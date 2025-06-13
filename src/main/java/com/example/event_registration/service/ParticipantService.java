package com.example.event_registration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.event_registration.model.Event;
import com.example.event_registration.model.Participant;
import com.example.event_registration.repository.EventRepository;
import com.example.event_registration.repository.ParticipantRepository;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepo;

    @Autowired
    private EventRepository eventRepo;

    public ResponseEntity<?> register(Participant participant) {
        if (participant.getEvent() == null || participant.getEvent().getId() == null) {
            return ResponseEntity.badRequest().body("Event ID is required");
        }

        Event event = eventRepo.findById(participant.getEvent().getId())
                .orElse(null);
        if (event == null) {
            return ResponseEntity.badRequest().body("Event not found");
        }

        participant.setEvent(event);
        return ResponseEntity.ok(participantRepo.save(participant));
    }

    public ResponseEntity<?> getParticipants(Long eventId) {
        return eventRepo.findById(eventId)
                .map(event -> ResponseEntity.ok(participantRepo.findByEvent(event)))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> delete(Long id) {
        participantRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
