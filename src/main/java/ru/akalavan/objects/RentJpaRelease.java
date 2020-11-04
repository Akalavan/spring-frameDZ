package ru.akalavan.objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rent")
@Setter
@Getter
public class RentJpaRelease {

    public static String TYPE_NAME = "Аренда машины";

    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "price")
    private int price;
    @Column(name = "date_from")
    private Date dateFrom;
    @Column(name = "date_to")
    private Date dateTo;
    @OneToOne
    @JoinColumn(name = "rent_car", referencedColumnName = "id", nullable = false)
    private CarJpaRelease rentCar;

    public RentJpaRelease() {
    }

    public RentJpaRelease(long id, int price, Date dateFrom, Date dateTo, CarJpaRelease rentCar) {
        this.id = id;
        this.price = price;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.rentCar = rentCar;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public CarJpaRelease getRentCar() {
        return rentCar;
    }

    public void setRentCar(CarJpaRelease rentCar) {
        this.rentCar = rentCar;
    }
}
