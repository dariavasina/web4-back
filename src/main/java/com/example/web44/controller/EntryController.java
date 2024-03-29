package com.example.web44.controller;


//import com.example.web44.model.Coordinates;
import com.example.web44.model.Coordinates;
import com.example.web44.model.Entry;
import com.example.web44.model.User;
import com.example.web44.repository.EntryRepository;
//import com.example.web44.utils.AreaChecker;
//import com.example.web44.utils.CoordinatesValidator;
import com.example.web44.repository.UserRepository;
import com.example.web44.utils.AreaChecker;
import com.example.web44.utils.CoordinatesValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "https://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EntryController {

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private UserRepository userRepository;

    // get all entries
    @GetMapping("/entries")
    public List<Entry> getAllEntries() {
        return entryRepository.findAll();
    }

    @GetMapping("/entries/{username}")
    public List<Entry> findAllByUsername(@PathVariable String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            Long userId = userOptional.get().getId();
            return entryRepository.findByUserId(userId);
        }
        return Collections.emptyList();
    }


    // create entry rest api
    @PostMapping("/entries/{username}")
    public Entry createEntry(@RequestBody Entry entry, @PathVariable String username) {
        long startTime = System.currentTimeMillis();
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            Coordinates coordinates = new Coordinates(entry.getX(), entry.getY(), entry.getR());
            boolean validInput = CoordinatesValidator.validate(coordinates);
            if (validInput) {
                boolean isHit = AreaChecker.isHit(coordinates);
                entry.setHit(isHit);
                long endTime = System.currentTimeMillis(); // Сохраняем время окончания операции
                long responseTime = endTime - startTime;
                entry.setResponseTime(responseTime);
                entry.setUser(user);
                return entryRepository.save(entry);
            }
            long endTime = System.currentTimeMillis();
            long responseTime = endTime - startTime;
        }

        return new Entry(-100, -100, -100, false, 100, new User("1", "2"));


    }

    @DeleteMapping("/entries")
    @Transactional
    public ResponseEntity<String> clearEntriesForUser(@RequestBody String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            Long userId = userOptional.get().getId();
            entryRepository.deleteByUserId(userId);
            return new ResponseEntity<>("Entries deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }
}
