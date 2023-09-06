package com.aacademy.moonlight.runner.hotel;

import com.aacademy.moonlight.entity.hotel.Room;
import com.aacademy.moonlight.entity.hotel.RoomFacility;
import com.aacademy.moonlight.entity.hotel.RoomType;
import com.aacademy.moonlight.entity.hotel.RoomView;
import com.aacademy.moonlight.repository.hotel.RoomFacilityRepository;
import com.aacademy.moonlight.repository.hotel.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomDataInitializer implements CommandLineRunner {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoomFacilityRepository roomFacilityRepository;

    @Autowired
    public RoomDataInitializer(RoomRepository roomRepository, RoomFacilityRepository roomFacilityRepository) {
        this.roomRepository = roomRepository;
        this.roomFacilityRepository = roomFacilityRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (roomRepository.count() > 0 && roomFacilityRepository.count() > 0) {
            System.out.println("Data already exists. Skipping initialization.");
            return;
        }

        RoomFacility lounge = RoomFacility.builder()
                .facility("Lounge area")
                .build();

        RoomFacility TV = RoomFacility.builder()
                .facility("Flat screen TV")
                .build();

        RoomFacility phone = RoomFacility.builder()
                .facility("Telephone")
                .build();

        RoomFacility toiletries = RoomFacility.builder()
                .facility("Toiletries")
                .build();

        RoomFacility kettle = RoomFacility.builder()
                .facility("Electric kettle")
                .build();

        RoomFacility internet = RoomFacility.builder()
                .facility("Wired and wireless internet")
                .build();

        RoomFacility mattress = RoomFacility.builder()
                .facility("Comfortable mattress")
                .build();

        RoomFacility minibar = RoomFacility.builder()
                .facility("Minibar")
                .build();

        RoomFacility safe = RoomFacility.builder()
                .facility("Safe")
                .build();

        RoomFacility hairDryer = RoomFacility.builder()
                .facility("HairDryer")
                .build();

        RoomFacility terrace = RoomFacility.builder()
                .facility("Terrace")
                .build();

        RoomFacility spaAccessories = RoomFacility.builder()
                .facility("Bathrobe and spa slippers")
                .build();

        RoomFacility sofaBed = RoomFacility.builder()
                .facility("Sofa bed")
                .build();

        List<RoomFacility> standardRoomFacilities = List.of(TV, phone, toiletries, kettle, internet, mattress, minibar, safe, hairDryer, spaAccessories);
        List<RoomFacility> studioRoomFacilities = List.of(TV, phone, toiletries, kettle, internet, mattress, minibar, safe, hairDryer, spaAccessories, sofaBed);
        List<RoomFacility> apartmentRoomFacilities = List.of(lounge, TV, phone, toiletries, kettle, internet, mattress, minibar, safe, hairDryer, terrace, spaAccessories);

        roomFacilityRepository.saveAll(standardRoomFacilities);
        roomFacilityRepository.saveAll(studioRoomFacilities);
        roomFacilityRepository.saveAll(apartmentRoomFacilities);

//TODO ADD ROOM SIZES IN ENTITY
        Room.RoomBuilder standardRoomBuilder = Room.builder()
                .price(220.0)
                .area(24)
                .type(RoomType.STANDARD)
                .facilities(standardRoomFacilities);

        Room.RoomBuilder studioRoomBuilder = Room.builder()
                .price(320.0)
                .area(34)
                .type(RoomType.STUDIO)
                .facilities(studioRoomFacilities);

        Room.RoomBuilder apartmentRoomBuilder = Room.builder()
                .price(520.0)
                .area(56)
                .type(RoomType.APARTMENT)
                .facilities(apartmentRoomFacilities);

        //standard rooms
        for (int i = 1; i <= 8; i++) {
            Room standardRoom = standardRoomBuilder
                    .roomNumber(i)
                    .build();
            if (i <= 2) {
                standardRoom.setView(RoomView.SEA);
            } else if (i <= 4) {
                standardRoom.setView(RoomView.POOL);
            } else {
                standardRoom.setView(RoomView.GARDEN);
            }
            roomRepository.save(standardRoom);
        }

        //studio rooms
        for (int i = 9; i <= 14; i++) {
            Room studioRoom = studioRoomBuilder
                    .roomNumber(i)
                    .build();
            if (i <= 10) {
                studioRoom.setView(RoomView.SEA);

            } else if (i <= 12) {
                studioRoom.setView(RoomView.POOL);
            } else {
                studioRoom.setView(RoomView.GARDEN);
            }
            roomRepository.save(studioRoom);
        }

        //apartments
        for (int i = 15; i <= 17; i++) {
            Room apartmentRoom = apartmentRoomBuilder
                    .roomNumber(i)
                    .build();
            if (i <= 16) {
                apartmentRoom.setView(RoomView.SEA);

            } else {
                apartmentRoom.setView(RoomView.POOL);
            }
            roomRepository.save(apartmentRoom);
        }

    }
}
