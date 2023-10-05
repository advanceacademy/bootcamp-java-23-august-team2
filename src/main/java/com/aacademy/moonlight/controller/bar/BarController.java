package com.aacademy.moonlight.controller.bar;

import com.aacademy.moonlight.entity.bar.ScreenEvent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/screen")
public class BarController {

//    private final ScreenEventService screenEventService;
//
//    @Autowired
//    public ScController(HotelServiceImpl hotelService) {
//        this.hotelService = hotelService;
//    }
//
//    @PostMapping
//    public ResponseEntity<String> addHotel(@RequestBody HotelRequest request) {
//        HotelResponse hotelResponse = hotelService.saveHotel(request);
//        String response = String.format("Hotel with name %s was created with id %s",
//                hotelResponse.getHotelName(), hotelResponse.getId());
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }
}
