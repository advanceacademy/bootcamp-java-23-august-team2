package com.aacademy.moonlight.entity.hotel;

public enum RoomType {
    STANDARD(2),
    STUDIO(3),
    APARTMENT(4);
    private int capacity;
    RoomType(int capacity) {
        this.capacity = capacity;
    }
    public int getCapacity() {
        return capacity;
    }
}

