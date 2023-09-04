package com.aacademy.moonlight.service.hotel.impl;

import com.aacademy.moonlight.dto.hotel.RoomFacilityRequest;
import com.aacademy.moonlight.entity.hotel.RoomFacility;
import com.aacademy.moonlight.repository.hotel.RoomFacilityRepository;
import com.aacademy.moonlight.service.hotel.RoomFacilityService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RoomFacilityServiceImpl implements RoomFacilityService {

    private final RoomFacilityRepository roomFacilityRepository;

    public RoomFacilityServiceImpl(RoomFacilityRepository roomFacilityRepository) {
        this.roomFacilityRepository = roomFacilityRepository;
    }


    @Override
    @Transactional
    public RoomFacility saveRoomFacility(@Valid RoomFacilityRequest roomFacilityRequest) {
        // Create a new RoomFacility entity from the request
        RoomFacility roomFacility = new RoomFacility();
        roomFacility.setFacility(roomFacilityRequest.getFacility());

        return roomFacilityRepository.save(roomFacility);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RoomFacility> getRoomFacility(Long id) {
        return roomFacilityRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteRoomFacility(Long id) {
        roomFacilityRepository.deleteById(id);
    }
}
