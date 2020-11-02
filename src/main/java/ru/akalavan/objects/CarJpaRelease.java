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
@Getter
@Setter
public class CarJpaRelease {

    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "name")
    private String name;

    public CarJpaRelease() {
    }

    public CarJpaRelease(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }
}
