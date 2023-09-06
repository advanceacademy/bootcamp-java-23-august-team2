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
                .type(RoomType.STANDARD)
                .facilities(standardRoomFacilities);

        Room.RoomBuilder studioRoomBuilder = Room.builder()
                .price(320.0)
                .type(RoomType.STUDIO)
                .facilities(studioRoomFacilities);

        Room.RoomBuilder apartmentRoomBuilder = Room.builder()
                .price(520.0)
                .type(RoomType.APARTMENT)
                .facilities(apartmentRoomFacilities);

        //standard rooms
        Room standardRoom1 = standardRoomBuilder
                .roomNumber(1)
                .view(RoomView.SEA)
                .build();

        Room standardRoom2 = standardRoomBuilder
                .roomNumber(2)
                .view(RoomView.SEA)
                .build();

        Room standardRoom3 = standardRoomBuilder
                .roomNumber(3)
                .view(RoomView.POOL)
                .build();

        Room standardRoom4 = standardRoomBuilder
                .roomNumber(4)
                .view(RoomView.POOL)
                .build();

        Room standardRoom5 = standardRoomBuilder
                .roomNumber(5)
                .view(RoomView.GARDEN)
                .build();

        Room standardRoom6 = standardRoomBuilder
                .roomNumber(6)
                .view(RoomView.GARDEN)
                .build();

        Room standardRoom7 = standardRoomBuilder
                .roomNumber(7)
                .view(RoomView.GARDEN)
                .build();

        Room standardRoom8 = standardRoomBuilder
                .roomNumber(8)
                .view(RoomView.GARDEN)
                .build();

        //studio rooms
        Room studioRoom1 = studioRoomBuilder
                .roomNumber(9)
                .view(RoomView.SEA)
                .build();

        Room studioRoom2 = studioRoomBuilder
                .roomNumber(10)
                .view(RoomView.SEA)
                .build();

        Room studioRoom3 = studioRoomBuilder
                .roomNumber(11)
                .view(RoomView.POOL)
                .build();

        Room studioRoom4 = studioRoomBuilder
                .roomNumber(12)
                .view(RoomView.POOL)
                .build();

        Room studioRoom5 = studioRoomBuilder
                .roomNumber(13)
                .view(RoomView.GARDEN)
                .build();

        Room studioRoom6 = studioRoomBuilder
                .roomNumber(14)
                .view(RoomView.GARDEN)
                .build();

        //apartments
        Room apartment1 = apartmentRoomBuilder
                .roomNumber(15)
                .view(RoomView.SEA)
                .build();

        Room apartment2 = apartmentRoomBuilder
                .roomNumber(16)
                .view(RoomView.SEA)
                .build();

        Room apartment3 = apartmentRoomBuilder
                .roomNumber(17)
                .view(RoomView.POOL)
                .build();

        List<Room> standardRooms = List.of(standardRoom1, standardRoom2, standardRoom3, standardRoom4, standardRoom5, standardRoom6, standardRoom7, standardRoom8);
        List<Room> studioRooms = List.of(studioRoom1, studioRoom2, studioRoom3, studioRoom4, studioRoom5, studioRoom6);
        List<Room> apartmentRooms = List.of(apartment1, apartment2, apartment3);

        roomRepository.saveAll(standardRooms);
        roomRepository.saveAll(studioRooms);
        roomRepository.saveAll(apartmentRooms);

    }
}
