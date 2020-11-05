package service;

import config.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.akalavan.exceptions.*;
import ru.akalavan.jpa.CarRepository;
import ru.akalavan.jpa.RentJpaRepository;
import ru.akalavan.objects.CarJpaRelease;
import ru.akalavan.objects.RentJpaRelease;
import ru.akalavan.service.impl.DefaultRentService;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class DefaultRentServiceTest {

    @Autowired
    private DefaultRentService defaultRentService;
    @Autowired
    private RentJpaRepository rentJpaRepository;
    @Autowired
    private CarRepository carRepository;

    @Test
    public void findAll() {
        defaultRentService.findAllRent();
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findByNullId() {
        defaultRentService.findById(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findByIdCrashType() {
        defaultRentService.findById(new float[1]);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByIdNotFound() {
        defaultRentService.findById(20);
    }

    @Test
    public void findById() {
        defaultRentService.findById(2);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNullRentException() {
        defaultRentService.create(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNullIdRentException() {
        RentJpaRelease rent = new RentJpaRelease();
        defaultRentService.create(rent);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNullCarException() {
        RentJpaRelease rent = new RentJpaRelease(8, 2000, new Date(), new Date(), null);
        defaultRentService.create(rent);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNullDateFromException() {
        RentJpaRelease rent = new RentJpaRelease(8, 2000, null, new Date(), new CarJpaRelease());
        defaultRentService.create(rent);
    }

    @Test(expected = EntityAlreadyExistsException.class)
    public void createAlreadyRentException() {
        RentJpaRelease rent = new RentJpaRelease(1, 2000, new Date(), new Date(), new CarJpaRelease());
        defaultRentService.create(rent);
    }

    @Test(expected = EntityConflictException.class)
    public void createDateToNulAndCarException() {
        Optional<CarJpaRelease> car = carRepository.findById(2);
        Assert.assertTrue(car.isPresent());
        RentJpaRelease rent = new RentJpaRelease(8, 200, new Date(), new Date(), car.get());
        defaultRentService.create(rent);
    }

    @Test
    public void create() {
        Optional<CarJpaRelease> car = carRepository.findById(1);
        Assert.assertTrue(car.isPresent());
        RentJpaRelease rent = new RentJpaRelease(8, 200, new Date(), new Date(), car.get());
        defaultRentService.create(rent);
        Optional<RentJpaRelease> rentTest = rentJpaRepository.findById(8L);
        Assert.assertTrue(rentTest.isPresent());
        Assert.assertEquals(rentTest.get().getId(), 8);
        defaultRentService.delete(rent.getId());
    }

    @Test
    public void delete() {
        defaultRentService.delete(5);
    }
}
