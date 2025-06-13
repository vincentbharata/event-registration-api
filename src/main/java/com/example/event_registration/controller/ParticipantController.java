package com.example.event_registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.event_registration.model.Participant;
import com.example.event_registration.service.ParticipantService;

import jakarta.validation.Valid;

@RestController
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @PostMapping("/participants")
    public ResponseEntity<?> register(@Valid @RequestBody Participant participant) {
        return participantService.register(participant);
    }

    @GetMapping("/events/{id}/participants")
    public ResponseEntity<?> getParticipants(@PathVariable Long id) {
        return participantService.getParticipants(id);
    }

    @DeleteMapping("/participants/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return participantService.delete(id);
    }
}
