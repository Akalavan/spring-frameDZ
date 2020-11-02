package ru.akalavan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.akalavan.jdbc.RentRepository;
import ru.akalavan.jpa.CarRepository;
import ru.akalavan.jpa.RentJpaRepository;
import ru.akalavan.objects.CarJpaRelease;
import ru.akalavan.objects.RentJdbcRelease;
import ru.akalavan.objects.RentJpaRelease;

import java.util.List;

@RestController
@RequestMapping("rent/v1")
public class Controller {

    private final CarRepository carRepository;
    private final RentRepository rentRepository;
    private final RentJpaRepository rentJpaRepository;

    public Controller(CarRepository carRepository, RentRepository rentRepository, RentJpaRepository rentJpaRepository) {
        this.carRepository = carRepository;
        this.rentRepository = rentRepository;
        this.rentJpaRepository = rentJpaRepository;
    }

    @GetMapping("/allCar")
    public List<CarJpaRelease> getAllCar() {
        return carRepository.findAll();
    }

    @GetMapping("/rents/priceHigher")
    public List<RentJdbcRelease> getRentHigher() {
        return rentRepository.getRentHigher(1300);
    }

    @GetMapping("/rents/count")
    public int getCount() {
        return rentRepository.count();
    }

    @GetMapping("/rents/allRent")
    public List<RentJpaRelease> getAllRent() {
        return rentJpaRepository.findAll();
    }

    @PostMapping("/rents/addRent")
    public RentJpaRelease addRent(@RequestBody RentJpaRelease rentJpaRelease) {
        return rentJpaRepository.save(rentJpaRelease);
    }

    @GetMapping("/rents/active")
    public List<RentJpaRelease> findByDateNull() {
        return rentJpaRepository.findByDateToIsNull();
    }

    @GetMapping("/rents/carName")
    public List<RentJpaRelease> findCarByName() {
        return rentJpaRepository.findByRentCarName("rapid");
    }

    @GetMapping("/rents/maxRent")
    public Integer getMaxPriceRentById() {
       return rentJpaRepository.getMaxPriceByRentCarId(2);
    }
}
