package com.aacademy.moonlight.runner.hotel;

import com.aacademy.moonlight.dto.hotel.RoomResponse;
import com.aacademy.moonlight.entity.hotel.Room;
import com.aacademy.moonlight.entity.hotel.RoomFacility;
import com.aacademy.moonlight.entity.hotel.RoomType;
import com.aacademy.moonlight.entity.hotel.RoomView;
import com.aacademy.moonlight.repository.hotel.RoomFacilityRepository;
import com.aacademy.moonlight.repository.hotel.RoomRepository;
import com.aacademy.moonlight.service.hotel.RoomService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class HotelRoomCommandRunner implements CommandLineRunner {

    private final RoomRepository roomRepository;

    private final RoomFacilityRepository roomFacilityRepository;

    private final RoomService roomService;

    public HotelRoomCommandRunner(RoomRepository roomRepository, RoomFacilityRepository roomFacilityRepository, RoomService roomService) {
        this.roomRepository = roomRepository;
        this.roomFacilityRepository = roomFacilityRepository;
        this.roomService = roomService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create facilities
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

        //Create facility groups for different room types
        List<RoomFacility> standardRoomFacilities = List.of(TV, phone, toiletries, kettle, internet,
                mattress, minibar, safe, hairDryer, spaAccessories);
        List<RoomFacility> studioRoomFacilities = List.of(TV, phone, toiletries, kettle, internet,
                mattress, minibar, safe, hairDryer, spaAccessories, sofaBed);
        List<RoomFacility> apartmentRoomFacilities = List.of(lounge, TV, phone, toiletries, kettle, internet,
                mattress, minibar, safe, hairDryer, terrace, spaAccessories);

        // Create and save standard rooms
        createAndSaveRooms(101L, 102L, 220.0, 24, RoomType.STANDARD,
                standardRoomFacilities, RoomView.SEA, 2);
        createAndSaveRooms(103L, 104L, 220.0, 24, RoomType.STANDARD,
                standardRoomFacilities, RoomView.POOL, 2);
        createAndSaveRooms(105L, 108L, 220.0, 24, RoomType.STANDARD,
                standardRoomFacilities, RoomView.GARDEN, 2);

        // Create and save studio rooms
        createAndSaveRooms(201L, 202L, 320.0, 34, RoomType.STUDIO,
                studioRoomFacilities,
                RoomView.SEA, 3);
        createAndSaveRooms(203L, 204L, 320.0, 34, RoomType.STUDIO,
                studioRoomFacilities,
                RoomView.POOL, 3);
        createAndSaveRooms(205L, 206L, 320.0, 34, RoomType.STUDIO,
                studioRoomFacilities,
                RoomView.GARDEN, 3);

        // Create and save apartment rooms
        createAndSaveRooms(301L, 302L, 520.0, 56, RoomType.APARTMENT,
                apartmentRoomFacilities,
                RoomView.SEA, 4);
        createAndSaveRooms(303L, 303L, 520.0, 56, RoomType.APARTMENT,
                apartmentRoomFacilities,
                RoomView.POOL, 4);
    }

    private RoomFacility createFacility(String facilityName) {
        List<RoomFacility> existingFacilities = roomFacilityRepository.findAll();

        for (RoomFacility facility : existingFacilities) {
            if (facilityName.equals(facility.getFacility())) {
                return facility;
            }
        }

        RoomFacility newFacility = RoomFacility.builder()
                .facility(facilityName)
                .build();

        return roomFacilityRepository.save(newFacility);
    }

    private void createAndSaveRooms(Long startRoomNumber, Long endRoomNumber, double price, int area,
                                    RoomType roomType, List<RoomFacility> facilities, RoomView view, Integer roomCapacity) {
        for (Long i = startRoomNumber; i <= endRoomNumber; i++) {
            Room room = Room.builder()
                    .roomNumber(i)
                    .price(price)
                    .area(area)
                    .type(roomType)
                    .facilities(facilities)
                    .view(view)
                    .roomCapacity(roomCapacity)
                    .build();

            List<RoomResponse> foundRoom = roomService.findByRoomNumber(room.getRoomNumber());
            if (foundRoom.isEmpty()) {
                roomRepository.save(room);
            }
        }
    }
}
