package ru.akalavan.objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rent_car")
@NoArgsConstructor
@Getter
@Setter
public class CarJpaRelease {

    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "name")
    private String name;
}
