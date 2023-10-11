package com.codecool.hogwarts_potions.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private HouseType houseType;
    private PetType petType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
    @JsonBackReference // A gyermek entitásban használd ezt az annotációt
    private Room room;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")//
    private List<Potion> potions;

    public void addPotion(Potion potion) {
        potions.add(potion);
    }
}
//Student osztály helyesen van definiálva az entitásként való kezeléshez a Spring Data JPA számára. Az osztály tartalmazza az @Entity annotációt, amely jelzi, hogy ez egy entitás, valamint az @Id és @GeneratedValue annotációkat az egyedi azonosítóhoz.
//
//Az @JsonIgnore annotáció segít elrejteni az azonosítót a JSON reprezentációból, amikor az objektumokat JSON formátumba alakítod (például REST API válaszként). A @JsonIgnore azt jelenti, hogy az azonosító nem lesz beleértve a JSON kimenetbe.
//
//A @Getter, @Setter, @Builder, @NoArgsConstructor és @AllArgsConstructor annotációk segítenek a kényelmes getterek, setterek és konstruktorok generálásában, ami hasznos a Spring Data JPA-val való munkához.