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
import ru.akalavan.service.RentService;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class RentServiceTest {

    @Autowired
    private RentService rentService;
    @Autowired
    private RentJpaRepository rentJpaRepository;
    @Autowired
    private CarRepository carRepository;

    @Test
    public void findAll() {
        rentService.findAllRent();
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findByNullId() {
        rentService.findById(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findByIdCrashType() {
        rentService.findById(new float[1]);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByIdNotFound() {
        rentService.findById(20);
    }

    @Test
    public void findById() {
        rentService.findById(2);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNullRentException() {
        rentService.create(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNullIdRentException() {
        RentJpaRelease rent = new RentJpaRelease();
        rentService.create(rent);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNullCarException() {
        RentJpaRelease rent = new RentJpaRelease(8, 2000, new Date(), new Date(), null);
        rentService.create(rent);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNullDateFromException() {
        RentJpaRelease rent = new RentJpaRelease(8, 2000, null, new Date(), new CarJpaRelease());
        rentService.create(rent);
    }

    @Test(expected = EntityAlreadyExistsException.class)
    public void createAlreadyRentException() {
        RentJpaRelease rent = new RentJpaRelease(1, 2000, new Date(), new Date(), new CarJpaRelease());
        rentService.create(rent);
    }

    @Test(expected = EntityConflictException.class)
    public void createDateToNulAndCarException() {
        Optional<CarJpaRelease> car = carRepository.findById(2);
        Assert.assertTrue(car.isPresent());
        RentJpaRelease rent = new RentJpaRelease(8, 200, new Date(), new Date(), car.get());
        rentService.create(rent);
    }

    @Test
    public void create() {
        Optional<CarJpaRelease> car = carRepository.findById(1);
        Assert.assertTrue(car.isPresent());
        RentJpaRelease rent = new RentJpaRelease(8, 200, new Date(), new Date(), car.get());
        rentService.create(rent);
        Optional<RentJpaRelease> rentTest = rentJpaRepository.findById(8L);
        Assert.assertTrue(rentTest.isPresent());
        Assert.assertEquals(rentTest.get().getId(), 8);
        rentService.delete(rent.getId());
    }

    @Test
    public void delete() {
        rentService.delete(5);
    }
}
