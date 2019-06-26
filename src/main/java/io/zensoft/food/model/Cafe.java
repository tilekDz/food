package io.zensoft.food.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cafes")

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Data
public class Cafe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "cafe", orphanRemoval = true)
    @JsonIgnore
    private List<Dish> dishes = new ArrayList<>();
}
