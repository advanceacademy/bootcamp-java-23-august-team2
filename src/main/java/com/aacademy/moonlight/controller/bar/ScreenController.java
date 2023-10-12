package com.aacademy.moonlight.controller.bar;
import com.aacademy.moonlight.entity.bar.BarScreen;
import com.aacademy.moonlight.service.bar.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/screen")
public class ScreenController {
    private final ScreenService screenService;
    @Autowired
    public ScreenController(ScreenService screenService)
    {this.screenService = screenService;}
    @GetMapping("/{id}")
    public ResponseEntity<BarScreen> getScreen(@PathVariable Long id ) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.status(HttpStatus.FOUND).body(screenService.getScreenById(id));
    }
    @GetMapping("/all")
    public ResponseEntity<List<BarScreen>> getAllScreens(){
        return ResponseEntity.status(HttpStatus.FOUND).body(screenService.getAllScreens());
    }
}
