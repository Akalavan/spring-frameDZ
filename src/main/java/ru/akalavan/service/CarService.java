package ru.akalavan.service;

import ru.akalavan.objects.CarJpaRelease;
import ru.akalavan.objects.RentJpaRelease;

import java.util.List;

public interface CarService {

    List<CarJpaRelease> findAllCar();

    CarJpaRelease findById(Object id);

    CarJpaRelease create(CarJpaRelease car);

    CarJpaRelease update(CarJpaRelease car);

    void delete(Object id);
}
