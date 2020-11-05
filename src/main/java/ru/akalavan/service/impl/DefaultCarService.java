package ru.akalavan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akalavan.exceptions.EntityAlreadyExistsException;
import ru.akalavan.exceptions.EntityHasDetailsException;
import ru.akalavan.exceptions.EntityIllegalArgumentException;
import ru.akalavan.exceptions.EntityNotFoundException;
import ru.akalavan.jpa.CarRepository;
import ru.akalavan.jpa.RentJpaRepository;
import ru.akalavan.objects.CarJpaRelease;
import ru.akalavan.objects.RentJpaRelease;
import ru.akalavan.service.CarService;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultCarService implements CarService {

    private final CarRepository carRepository;
    private final RentJpaRepository rentRepository;

    public DefaultCarService(CarRepository carRepository, RentJpaRepository rentRepository) {
        this.carRepository = carRepository;
        this.rentRepository = rentRepository;
    }

    public List<CarJpaRelease> findAllCar() {
        return carRepository.findAll();
    }

    public CarJpaRelease findById(Object id){
        Optional<CarJpaRelease> car;
        if (id == null)
            throw new EntityIllegalArgumentException("id объекта не может быть null");
        Integer carId;

        try {
            carId = Integer.valueOf(String.valueOf(id));
        } catch (NumberFormatException e) {
            throw new EntityIllegalArgumentException(String.format("Не удалось преобразовать id к нужному типу," +
                    " текст ошибки: %s", e));
        }

        car = carRepository.findById(carId);
        if (!car.isPresent())
            throw new EntityNotFoundException(CarJpaRelease.TYPE_NAME, carId);

        return car.get();
    }

    public CarJpaRelease create(CarJpaRelease car) {
        if (car == null)
            throw new EntityIllegalArgumentException("Создаваемый объект не может быть null");
        if (car.getId() == 0)
            throw new EntityIllegalArgumentException("id объекта не может быть равен 0");
        Optional<CarJpaRelease> existedCar = carRepository.findById(car.getId());
        if (existedCar.isPresent())
            throw new EntityAlreadyExistsException(CarJpaRelease.TYPE_NAME, car.getId());

        return carRepository.save(car);
    }

    @Override
    public CarJpaRelease update(CarJpaRelease car) {
        if (car == null)
            throw new EntityIllegalArgumentException("Создаваемый объект не может быть null");
        if (car.getId() == 0)
            throw new EntityIllegalArgumentException("id объекта не может быть равен 0");
        Optional<CarJpaRelease> existedCar = carRepository.findById(car.getId());
        if (!existedCar.isPresent())
            throw new EntityNotFoundException(CarJpaRelease.TYPE_NAME, car.getId());
        return carRepository.save(car);
    }

    public void delete(Object id) {
        CarJpaRelease car = findById(id);
        List<RentJpaRelease> rents = rentRepository.findByRentCar(car);
        if (rents.size() > 0)
            throw new EntityHasDetailsException(RentJpaRelease.TYPE_NAME, car.getId());
        carRepository.delete(car);
    }
}
