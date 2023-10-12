package com.aacademy.moonlight.controller.bar;

import com.aacademy.moonlight.dto.bar.ScreenEventRequest;
import com.aacademy.moonlight.entity.bar.ScreenEvent;
import com.aacademy.moonlight.entity.user.User;
import com.aacademy.moonlight.service.bar.ScreenEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.CredentialException;

@RestController
@RequestMapping("/api/v1/screen-event")
public class ScreenEventController {

    private final ScreenEventService screenEventService;

    @Autowired
    public ScreenEventController(ScreenEventService screenEventService) {
        this.screenEventService = screenEventService;
    }

    @PostMapping(path = "/create-event")
    public ResponseEntity<ScreenEvent> createEvent(@RequestBody ScreenEventRequest request) throws CredentialException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) auth.getPrincipal();
        if(user.getUserRole().getUserRole().equals("ADMIN")){
            ScreenEvent screenEvent = screenEventService.addScreenEvent(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(screenEvent);
        }
        else {
           throw new AuthorizationServiceException("You have no rights to create event");
        }
    }
}
