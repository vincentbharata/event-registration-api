package com.example.event_registration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.event_registration.model.Event;
import com.example.event_registration.repository.EventRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepo;

    public ResponseEntity<?> create(Event event) { //bisa diuat kelas masing2 untuik tiap respon
        if (event.getStartDate() != null && event.getEndDate() != null &&
                !event.getStartDate().isBefore(event.getEndDate())) {
            return ResponseEntity.badRequest().body("Start date must be before end date");
        }
        return ResponseEntity.ok(eventRepo.save(event));
    }

    public Page<Event> list(String title, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return eventRepo.findAll(pageable);
    }

    public ResponseEntity<Event> detail(Long id) {
        return eventRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> update(Long id, Event updatedEvent) {
        if (updatedEvent.getStartDate() != null && updatedEvent.getEndDate() != null &&
                !updatedEvent.getStartDate().isBefore(updatedEvent.getEndDate())) {
            return ResponseEntity.badRequest().body("Start date must be before end date");
        }

        return eventRepo.findById(id).map(event -> {
            event.setTitle(updatedEvent.getTitle());
            event.setLocation(updatedEvent.getLocation());
            event.setStartDate(updatedEvent.getStartDate());
            event.setEndDate(updatedEvent.getEndDate());
            return ResponseEntity.ok(eventRepo.save(event));
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> delete(Long id) {
        eventRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
