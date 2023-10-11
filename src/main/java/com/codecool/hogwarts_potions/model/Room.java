package com.codecool.hogwarts_potions.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
    @JsonManagedReference // A szülő entitásban használd ezt az annotációt
    private Set<Student> residents = new HashSet<>();
}
//cascade = CascadeType.ALL: Ez a rész azt jelenti, hogy a változtatások a Room entitáson (például a terem törlése vagy módosítása) automatikusan kiterjednek a hozzárendelt Student entitásokra is. Azaz, ha törölsz egy termet, akkor az összes hozzárendelt diák is törölve lesz. Ezzel könnyebb kezelni az adatok konzisztenciáját az adatbázisban.
//Az @JsonBackReference annotációt használhatod az entitás egyik oldalán, hogy jelezd, hogy a kapcsolat "visszafelé" mutat egy másik entitásra, és azt akarod, hogy ne szerializálja a kapcsolatot. Az @JsonManagedReference annotációt pedig a kapcsolat másik oldalán használhatod, hogy megmondja a Jacksonnak, hogy szerializálja a kapcsolatot.