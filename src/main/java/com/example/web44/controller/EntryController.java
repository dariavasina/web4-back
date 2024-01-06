package com.example.web44.controller;


//import com.example.web44.model.Coordinates;
import com.example.web44.model.Coordinates;
import com.example.web44.model.Entry;
import com.example.web44.repository.EntryRepository;
//import com.example.web44.utils.AreaChecker;
//import com.example.web44.utils.CoordinatesValidator;
import com.example.web44.utils.AreaChecker;
import com.example.web44.utils.CoordinatesValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EntryController {

    @Autowired
    private EntryRepository entryRepository;

    // get all entries
    @GetMapping("/entries")
    public List<Entry> getAllEntries() {
        return entryRepository.findAll();
    }

    // create entry rest api
    @PostMapping("/entries")
    public Entry createEntry(@RequestBody Entry entry) {
        long startTime = System.currentTimeMillis();
        Coordinates coordinates = new Coordinates(entry.getX(), entry.getY(), entry.getR());
        boolean validInput = CoordinatesValidator.validate(coordinates);
        if (validInput) {
            boolean isHit = AreaChecker.isHit(coordinates);
            entry.setHit(isHit);
            long endTime = System.currentTimeMillis(); // Сохраняем время окончания операции
            long responseTime = endTime - startTime;
            entry.setResponseTime(responseTime);
            return entryRepository.save(entry);
        }
        long endTime = System.currentTimeMillis();
        long responseTime = endTime - startTime;
        return new Entry(-100, -100, -100, false, responseTime);

    }
}
