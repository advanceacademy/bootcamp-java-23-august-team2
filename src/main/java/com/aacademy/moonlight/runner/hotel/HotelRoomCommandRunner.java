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
public class HotelRoomCommandRunner implements CommandLineRunner {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoomFacilityRepository roomFacilityRepository;

    private final RoomService roomService;

    @Autowired
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
        createAndSaveRooms(101, 102, 220.0, 24, RoomType.STANDARD,
                standardRoomFacilities, RoomView.SEA);
        createAndSaveRooms(103, 104, 220.0, 24, RoomType.STANDARD,
                standardRoomFacilities, RoomView.POOL);
        createAndSaveRooms(105, 108, 220.0, 24, RoomType.STANDARD,
                standardRoomFacilities, RoomView.GARDEN);

        // Create and save studio rooms
        createAndSaveRooms(201, 202, 320.0, 34, RoomType.STUDIO,
                studioRoomFacilities,
                RoomView.SEA);
        createAndSaveRooms(203, 204, 320.0, 34, RoomType.STUDIO,
                studioRoomFacilities,
                RoomView.POOL);
        createAndSaveRooms(205, 206, 320.0, 34, RoomType.STUDIO,
                studioRoomFacilities,
                RoomView.GARDEN);

        // Create and save apartment rooms
        createAndSaveRooms(301, 302, 520.0, 56, RoomType.APARTMENT,
                apartmentRoomFacilities,
                RoomView.SEA);
        createAndSaveRooms(303, 303, 520.0, 56, RoomType.APARTMENT,
                apartmentRoomFacilities,
                RoomView.POOL);
    }

    private RoomFacility createFacility(String facilityName) {
        List<RoomFacility> existingFacilities = roomFacilityRepository.findAll();

        for (RoomFacility facility : existingFacilities){
            if (facilityName.equals(facility.getFacility())){
                return facility;
            }
        }

        RoomFacility newFacility = RoomFacility.builder()
                .facility(facilityName)
                .build();

        return roomFacilityRepository.save(newFacility);
    }

    private void createAndSaveRooms(int startRoomNumber, int endRoomNumber, double price, int area, RoomType roomType, List<RoomFacility> facilities, RoomView view) {
        for (int i = startRoomNumber; i <= endRoomNumber; i++) {
            Room room = Room.builder()
                    .roomNumber(i)
                    .price(price)
                    .area(area)
                    .type(roomType)
                    .facilities(facilities)
                    .view(view)
                    .build();

            Room foundRoom = roomService.findByNumber(room.getRoomNumber());
            if (Objects.isNull(foundRoom)) {
                roomRepository.save(room);
            }
        }
    }
}
