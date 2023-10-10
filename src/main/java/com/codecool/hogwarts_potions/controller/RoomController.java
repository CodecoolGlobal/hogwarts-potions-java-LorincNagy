package com.codecool.hogwarts_potions.controller;

import com.codecool.hogwarts_potions.dto.RoomDTO;
import com.codecool.hogwarts_potions.model.Room;
import com.codecool.hogwarts_potions.model.Student;
import com.codecool.hogwarts_potions.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/room")
public class RoomController {

    RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @PostMapping
    public void addRoom(@RequestBody RoomDTO roomDTO) {

        Room room = new Room();
        room.setCapacity(roomDTO.capacity());

        Set<Student> students = new HashSet<>();


        for (Student student : roomDTO.residents()) {

            student.setRoom(room);

            students.add(student);
        }

        room.getResidents().addAll(students);

        roomService.addRoom(room);
    }

    @GetMapping("/{id}")
    public Optional<Room> getRoomById(@PathVariable("id") Long id) {
        return roomService.getRoomById(id);
    }

    @PutMapping("/{id}")
    public void updateRoomById(@PathVariable("id") Long id, @RequestBody RoomDTO roomDTO) {
        Optional<Room> optionalRoom = roomService.getRoomById(id);

        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            room.setCapacity(roomDTO.capacity());

            for (Student studentDTO : roomDTO.residents()) {
                studentDTO.setRoom(room);
            }

            room.getResidents().clear();
            room.getResidents().addAll(roomDTO.residents());

            roomService.updateRoomById(id, room);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteRoomById(@PathVariable("id") Long id) {
        roomService.deleteRoomById(id);
    }

    //Get rooms where no cat or owl lives
    @GetMapping("/rat-owners")
    public List<Room> getRoomsForRatOwners() {
        return roomService.getRoomsForRatOwners();
    }
}
