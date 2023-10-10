package com.codecool.hogwarts_potions.service;

import com.codecool.hogwarts_potions.dao.RoomRepository;
import com.codecool.hogwarts_potions.model.PetType;
import com.codecool.hogwarts_potions.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Optional<Room> optionalRoom = roomRepository.findById(id);

        if (optionalRoom.isPresent()) {
            Room currentRoom = optionalRoom.get();

            // Frissítsd a kapacitást
            currentRoom.setCapacity(updatedRoom.getCapacity());


            // Adj hozzá új diákokat
            currentRoom.getResidents().addAll(updatedRoom.getResidents());

            roomRepository.save(currentRoom);
        } else {
            throw new NotFoundException("A megadott szoba nem található.");
        }
    }

    public void deleteRoomById(Long id) {

        roomRepository.deleteById(id);

    }


    public List<Room> getRoomsForRatOwners() {
        return roomRepository.findAll().stream()
                .filter(room -> room.getResidents().stream()
                        .allMatch(student -> student.getPetType() != PetType.CAT && student.getPetType() != PetType.OWL))
                .collect(Collectors.toList());
    }
}

