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

    public static String TYPE_NAME = "Машина";

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

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CarJpaRelease{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
