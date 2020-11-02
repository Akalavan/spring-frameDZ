package ru.akalavan.objects;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RentJdbcRelease {

    private long id;
    private Integer price;
    private Date dateFrom;
    private Date dateTo;
    private Integer rentCar;

    public RentJdbcRelease(long id, Integer price, Date dateFrom, Date dateTo, Integer rentCar) {
        this.id = id;
        this.price = price;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.rentCar = rentCar;
    }
}
