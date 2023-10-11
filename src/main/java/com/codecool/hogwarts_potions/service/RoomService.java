package com.codecool.hogwarts_potions.service;

import com.codecool.hogwarts_potions.dao.RoomRepository;
import com.codecool.hogwarts_potions.dto.RoomDTO;
import com.codecool.hogwarts_potions.model.PetType;
import com.codecool.hogwarts_potions.model.Room;
import com.codecool.hogwarts_potions.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoomService {


    private final RoomRepository roomRepository;

    @Autowired//injektál
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {//Meghívja a roomRepository-on található findAll metódust.A findAll metódust általában a Spring Data JPA szolgáltatja, és arra szolgál, hogy az adatbázisból (ebben az esetben a szobák) minden rekordot lekérdezzen, amelyek a Room entitásra vannak leképezve.A metódus egy listát ad vissza Room objektumokkal, amelyek az adatbázisban tárolt összes szobát reprezentálják.
        return this.roomRepository.findAll();
    }

    public void addRoomFromDTO(RoomDTO roomDTO) {
        Room room = new Room();
        room.setCapacity(roomDTO.capacity());

        Set<Student> students = new HashSet<>(roomDTO.residents());
        for (Student student : students) {
            student.setRoom(room); // Beállítjuk a Studentek Room attribútumát
        }

        room.setResidents(students);

        roomRepository.save(room);
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id).get();
    }

    public void updateRoomWithDTO(Long id, RoomDTO roomDTO) {
        Room currentRoom = roomRepository.findById(id).get();
        Set<Student> students = new HashSet<>(roomDTO.residents());
        for (Student student : students) {
            student.setRoom(currentRoom); // Beállítjuk a Studentek Room attribútumát
        }

        currentRoom.setResidents(students);
        currentRoom.setCapacity(roomDTO.capacity());
        roomRepository.save(currentRoom);
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

