package com.aacademy.moonlight.runner.hotel;

import com.aacademy.moonlight.entity.hotel.Room;
import com.aacademy.moonlight.entity.hotel.RoomFacility;
import com.aacademy.moonlight.entity.hotel.RoomType;
import com.aacademy.moonlight.entity.hotel.RoomView;
import com.aacademy.moonlight.repository.hotel.RoomFacilityRepository;
import com.aacademy.moonlight.repository.hotel.RoomRepository;
import com.aacademy.moonlight.service.hotel.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class RoomDataInitializer implements CommandLineRunner {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoomFacilityRepository roomFacilityRepository;

    private final RoomService roomService;

    @Autowired
    public RoomDataInitializer(RoomRepository roomRepository, RoomFacilityRepository roomFacilityRepository, RoomService roomService) {
        this.roomRepository = roomRepository;
        this.roomFacilityRepository = roomFacilityRepository;
        this.roomService = roomService;
    }

    @Override
    public void run(String... args) throws Exception {
//TODO FIX FACILITY DUPLICATE
        if (roomRepository.count() > 0 && roomFacilityRepository.count() > 0) {
            System.out.println("Data already exists. Skipping initialization.");
            return;
        }

        // Create facilities only once
        RoomFacility lounge = createFacility("Lounge area");
        RoomFacility TV = createFacility("Flat screen TV");
        RoomFacility phone = createFacility("Telephone");
        RoomFacility toiletries = createFacility("Toiletries");
        RoomFacility kettle = createFacility("Electric kettle");
        RoomFacility internet = createFacility("Wired and wireless internet");
        RoomFacility mattress = createFacility("Comfortable mattress");
        RoomFacility minibar = createFacility("Minibar");
        RoomFacility safe = createFacility("Safe");
        RoomFacility hairDryer = createFacility("HairDryer");
        RoomFacility terrace = createFacility("Terrace");
        RoomFacility spaAccessories = createFacility("Bathrobe and spa slippers");
        RoomFacility sofaBed = createFacility("Sofa bed");

        // Save facilities to the database
        roomFacilityRepository.saveAll(List.of(lounge, TV, phone, toiletries, kettle, internet, mattress, minibar, safe, hairDryer, terrace, spaAccessories, sofaBed));

        List<Long> standardRoomFacilities =  List.of(TV.getId(), phone.getId(), toiletries.getId(), kettle.getId(), internet.getId(),
                mattress.getId(), minibar.getId(), safe.getId(), hairDryer.getId(), spaAccessories.getId());
        List<Long> studioRoomFacilities = List.of(TV.getId(), phone.getId(), toiletries.getId(), kettle.getId(), internet.getId(),
                mattress.getId(), minibar.getId(), safe.getId(), hairDryer.getId(), spaAccessories.getId(), sofaBed.getId());
        List<Long> apartmentRoomFacilities = List.of(lounge.getId(), TV.getId(), phone.getId(), toiletries.getId(), kettle.getId(), internet.getId(),
                mattress.getId(), minibar.getId(), safe.getId(), hairDryer.getId(), terrace.getId(), spaAccessories.getId());
        // Create and save standard rooms
        createAndSaveRooms(1, 2, 220.0, 24, RoomType.STANDARD,
                standardRoomFacilities, RoomView.SEA);
        createAndSaveRooms(3, 4, 220.0, 24, RoomType.STANDARD,
                standardRoomFacilities, RoomView.POOL);
        createAndSaveRooms(5, 8, 220.0, 24, RoomType.STANDARD,
                standardRoomFacilities, RoomView.GARDEN);

        // Create and save studio rooms
        createAndSaveRooms(9, 10, 320.0, 34, RoomType.STUDIO,
                studioRoomFacilities,
                RoomView.SEA);
        createAndSaveRooms(11, 12, 320.0, 34, RoomType.STUDIO,
                studioRoomFacilities,
                RoomView.POOL);
        createAndSaveRooms(13, 14, 320.0, 34, RoomType.STUDIO,
                studioRoomFacilities,
                RoomView.GARDEN);

        // Create and save apartment rooms
        createAndSaveRooms(15, 16, 520.0, 56, RoomType.APARTMENT,
                apartmentRoomFacilities,
                RoomView.SEA);
        createAndSaveRooms(17, 17, 520.0, 56, RoomType.APARTMENT,
                apartmentRoomFacilities,
                RoomView.POOL);
    }

    private RoomFacility createFacility(String facilityName) {
        return RoomFacility.builder()
                .facility(facilityName)
                .build();
    }

    private void createAndSaveRooms(int startRoomNumber, int endRoomNumber, double price, int area, RoomType roomType, List<Long> facilityIds, RoomView view) {
        for (int i = startRoomNumber; i <= endRoomNumber; i++) {
            Room room = Room.builder()
                    .roomNumber(i)
                    .price(price)
                    .area(area)
                    .type(roomType)
                    .facilities(roomFacilityRepository.findAllById(facilityIds))
                    .view(view)
                    .build();

            Room foundRoom = roomService.findByNumber(room.getRoomNumber());
            if (Objects.isNull(foundRoom)){
                roomRepository.save(room);
            }
        }
    }
}
