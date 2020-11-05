package ru.akalavan.service.impl;

import org.springframework.stereotype.Service;
import ru.akalavan.exceptions.*;
import ru.akalavan.jpa.CarRepository;
import ru.akalavan.jpa.RentJpaRepository;
import ru.akalavan.objects.CarJpaRelease;
import ru.akalavan.objects.RentJpaRelease;
import ru.akalavan.service.RentService;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultRentService implements RentService {

    private final CarRepository carRepository;
    private final RentJpaRepository rentRepository;

    public DefaultRentService(CarRepository carRepository, RentJpaRepository rentRepository) {
        this.carRepository = carRepository;
        this.rentRepository = rentRepository;
    }

    public List<RentJpaRelease> findAllRent() {
        return rentRepository.findAll();
    }

    public RentJpaRelease findById(Object id){
        Optional<RentJpaRelease> rent;
        if (id == null)
            throw new EntityIllegalArgumentException("id объекта не может быть null");

        Long rentId;
        try {
            rentId = Long.valueOf(String.valueOf(id));
        } catch (NumberFormatException e) {
            throw new EntityIllegalArgumentException(String.format("Не удалось преобразовать id к нужному типу," +
                    " текст ошибки: %s", e));
        }

        rent = rentRepository.findById(rentId);
        if (!rent.isPresent())
            throw new EntityNotFoundException(RentJpaRelease.TYPE_NAME, rentId);

        return rent.get();
    }

    public RentJpaRelease create(RentJpaRelease rent) {
        if (rent == null)
            throw new EntityIllegalArgumentException("Создаваемый объект не может быть null");
        if (rent.getId() == 0)
            throw new EntityIllegalArgumentException("id объекта не может быть равен 0");
        if (rent.getRentCar() == null)
            throw new EntityIllegalArgumentException("Car не может быть null");
        if (rent.getDateFrom() == null)
            throw new EntityIllegalArgumentException("Дата начала периода не может быть null");

        Optional<RentJpaRelease> existedCar = rentRepository.findById(rent.getId());
        if (existedCar.isPresent())
            throw new EntityAlreadyExistsException(RentJpaRelease.TYPE_NAME, rent.getId());

        List<RentJpaRelease> rents = rentRepository.findByDateToIsNullAndRentCarId(rent.getRentCar().getId());
        if (rents.size() > 0)
            throw new EntityConflictException(String.format("В системе имеются открытые торговые периоды для машины с id %s",
                    rent.getRentCar().getId()));
        return rentRepository.save(rent);
    }

    @Override
    public RentJpaRelease update(RentJpaRelease rent) {
        if (rent == null)
            throw new EntityIllegalArgumentException("Создаваемый объект не может быть null");
        if (rent.getId() == 0)
            throw new EntityIllegalArgumentException("id объекта не может быть равен 0");
        if (rent.getRentCar() == null)
            throw new EntityIllegalArgumentException("Car не может быть null");
        if (rent.getDateFrom() == null)
            throw new EntityIllegalArgumentException("Дата начала периода не может быть null");

        Optional<RentJpaRelease> existedCar = rentRepository.findById(rent.getId());
        if (!existedCar.isPresent())
            throw new EntityNotFoundException(RentJpaRelease.TYPE_NAME, rent.getId());
        return rentRepository.save(rent);
    }

    public void delete(Object id) {
        RentJpaRelease rent = findById(id);
        rentRepository.delete(rent);
    }
}
