package service;

import config.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.akalavan.exceptions.EntityAlreadyExistsException;
import ru.akalavan.exceptions.EntityHasDetailsException;
import ru.akalavan.exceptions.EntityIllegalArgumentException;
import ru.akalavan.exceptions.EntityNotFoundException;
import ru.akalavan.jpa.CarRepository;
import ru.akalavan.objects.CarJpaRelease;
import ru.akalavan.service.CarService;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class CarServiceTest {

    @Autowired
    private CarService carService;
    @Autowired
    private CarRepository carRepository;

    @Test
    public void findAll() {
        List<CarJpaRelease> cars = carService.findAllCar();
        Assert.assertEquals(cars.size(), 2);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findByNullId() {
        carService.findById(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findByIdCrashType() {
        carService.findById(new float[1]);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByIdNotFound() {
        carService.findById(3);
    }

    @Test
    public void findById() {
        carService.findById(2);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNullCarException() {
        carService.create(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNullIdCarException() {
        CarJpaRelease car = new CarJpaRelease(0, "granta");
        carService.create(car);
    }

    @Test(expected = EntityAlreadyExistsException.class)
    public void createAlreadyCarException() {
        CarJpaRelease car = new CarJpaRelease(1, "granta");
        carService.create(car);
    }

    @Test
    public void create() {
        CarJpaRelease car = new CarJpaRelease(3, "granta");
        carService.create(car);
        Optional<CarJpaRelease> carTest = carRepository.findById(3);
        Assert.assertTrue(carTest.isPresent());
        Assert.assertEquals(carTest.get().toString(), "granta");
        carService.delete(car.getId());
    }

    @Test(expected = EntityHasDetailsException.class)
    public void deleteHasDetailsExceptions() {
        carService.delete(2);
    }

    @Test
    public void delete() {
        CarJpaRelease car = new CarJpaRelease(3, "granta");
        carService.create(car);
        carService.delete(3);
    }





}
