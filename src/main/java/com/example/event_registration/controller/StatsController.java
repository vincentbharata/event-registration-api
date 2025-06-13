package com.example.event_registration.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.event_registration.repository.ParticipantRepository;

@RestController
@RequestMapping("/stats")
public class StatsController {
    @Autowired
    private ParticipantRepository participantRepo;

    @GetMapping("/events")
    public List<Map<String, Object>> getEventStats() {
        return participantRepo.findAll().stream()
            .collect(Collectors.groupingBy(
                p -> p.getEvent().getTitle(),
                Collectors.counting()
            ))
            .entrySet().stream()
            .map(e -> {
                Map<String, Object> map = new HashMap<>();
                map.put("event", e.getKey());
                map.put("participants", e.getValue());
                return map;
            }).toList();
    }
}
