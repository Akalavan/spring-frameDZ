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
import ru.akalavan.service.impl.DefaultCarService;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class DefaultCarServiceTest {

    @Autowired
    private DefaultCarService defaultCarService;
    @Autowired
    private CarRepository carRepository;

    @Test
    public void findAll() {
        List<CarJpaRelease> cars = defaultCarService.findAllCar();
        Assert.assertEquals(cars.size(), 2);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findByNullId() {
        defaultCarService.findById(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findByIdCrashType() {
        defaultCarService.findById(new float[1]);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByIdNotFound() {
        defaultCarService.findById(3);
    }

    @Test
    public void findById() {
        defaultCarService.findById(2);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNullCarException() {
        defaultCarService.create(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNullIdCarException() {
        CarJpaRelease car = new CarJpaRelease(0, "granta");
        defaultCarService.create(car);
    }

    @Test(expected = EntityAlreadyExistsException.class)
    public void createAlreadyCarException() {
        CarJpaRelease car = new CarJpaRelease(1, "granta");
        defaultCarService.create(car);
    }

    @Test
    public void create() {
        CarJpaRelease car = new CarJpaRelease(3, "granta");
        defaultCarService.create(car);
        Optional<CarJpaRelease> carTest = carRepository.findById(3);
        Assert.assertTrue(carTest.isPresent());
        Assert.assertEquals(carTest.get().getName(), "granta");
        defaultCarService.delete(car.getId());
    }

    @Test(expected = EntityHasDetailsException.class)
    public void deleteHasDetailsExceptions() {
        defaultCarService.delete(2);
    }

    @Test
    public void delete() {
        CarJpaRelease car = new CarJpaRelease(3, "granta");
        defaultCarService.create(car);
        defaultCarService.delete(3);
    }





}
