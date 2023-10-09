package com.codecool.hogwarts_potions.service;

import com.codecool.hogwarts_potions.dao.RoomRepository;
import com.codecool.hogwarts_potions.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {


    private RoomRepository roomRepository;

    @Autowired//injektál
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {//Meghívja a roomRepository-on található findAll metódust.A findAll metódust általában a Spring Data JPA szolgáltatja, és arra szolgál, hogy az adatbázisból (ebben az esetben a szobák) minden rekordot lekérdezzen, amelyek a Room entitásra vannak leképezve.A metódus egy listát ad vissza Room objektumokkal, amelyek az adatbázisban tárolt összes szobát reprezentálják.
        return this.roomRepository.findAll();
    }

    public void addRoom(Room room) {
        roomRepository.save(room);
    }

    public Optional<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    public void updateRoomById(Long id, Room updatedRoom) {
        roomRepository.save(updatedRoom);
    }

    public void deleteRoomById(Long id) {
        //TODO
    }

    public List<Room> getRoomsForRatOwners() {
        //TODO
        return null;
    }
}
