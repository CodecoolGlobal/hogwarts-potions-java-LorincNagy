package com.codecool.hogwarts_potions.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@EqualsAndHashCode
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Recipe> recipes;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Potion> potions;
//@ManyToMany annotációval jelölt kapcsolat lehetővé teszi, hogy több összetevő több recepthez is tartozhasson, és több recepthez is tartozhasson több összetevő. Az ingredients mező a Recipe entitáson azt jelzi, hogy mely összetevők vannak hozzárendelve az adott recepthez, míg a recipes mező az Ingredient entitáson azt jelzi, hogy mely receptek tartalmazzák az adott összetevőt.
    //A JPA (Java Persistence API) segítségével lehetőség van a kapcsolatok kezelésére az entitások között anélkül, hogy expliciten definiálnánk a köztes táblát. Az @ManyToMany annotáció ebben az esetben egy köztes táblát hoz létre az adatbázisban, és kezeli a kapcsolatokat a háttérben.
    //
    //A kódban az ingredients és recipes mezők közötti kapcsolatot az @ManyToMany annotáció jelzi, de az adatbázisban a Hibernate létrehozza a köztes táblát, és kezeli a kapcsolatokat azon keresztül. Nem szükséges külön mezőket definiálni a köztes táblában, mivel a Hibernate ezt automatikusan elvégzi.
}