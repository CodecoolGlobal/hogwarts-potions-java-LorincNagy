package com.codecool.hogwarts_potions.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer capacity;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Student> residents = new HashSet<>();
}
//cascade = CascadeType.ALL: Ez a rész azt jelenti, hogy a változtatások a Room entitáson (például a terem törlése vagy módosítása) automatikusan kiterjednek a hozzárendelt Student entitásokra is. Azaz, ha törölsz egy termet, akkor az összes hozzárendelt diák is törölve lesz. Ezzel könnyebb kezelni az adatok konzisztenciáját az adatbázisban.