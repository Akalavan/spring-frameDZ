package ru.akalavan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.akalavan.objects.CarJpaRelease;
import ru.akalavan.service.CarService;

import java.util.List;

@RestController
@RequestMapping("rent/v1/car")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    @PreAuthorize("hasPermission('car', 'read')")
    public List<CarJpaRelease> findAll() {
        return carService.findAllCar();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasPermission('car', 'findById')")
    public CarJpaRelease findById(@PathVariable String id) {
        return carService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasPermission('car', 'crateCar')")
    public CarJpaRelease create(@RequestBody CarJpaRelease car) {
        return carService.create(car);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasPermission('car', 'updateCar')")
    public CarJpaRelease update(@RequestBody CarJpaRelease car) {
        return carService.update(car);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasPermission('car', 'deleteCar')")
    public void delete(@PathVariable String id) {
        carService.delete(id);
    }


}
