package repository;

import config.TestConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.akalavan.jpa.CarRepository;
import ru.akalavan.objects.CarJpaRelease;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    public void getAllCars() {
        List<CarJpaRelease> cars = carRepository.findAll();
        Assert.assertNotNull(cars);
        Assert.assertEquals(cars.size(), 3);
    }

    @Test
    public void findCarByName() {
        List<CarJpaRelease> cars = carRepository.findByName("polo_test");
        Assert.assertNotNull(cars);
        Assert.assertEquals(cars.get(0).getName(), "polo_test");
    }

    @Test
    public void findCarById() {
        Optional<CarJpaRelease> cars = carRepository.findById(1);
        Assert.assertNotNull(cars);
        Assert.assertEquals(cars.get().getId(), 1);
    }

    @Before
    public void addCar() {
        CarJpaRelease car = new CarJpaRelease(3, "kaptur_test");
        carRepository.save(car);
        Optional<CarJpaRelease> carTest = carRepository.findById(3);
        Assert.assertNotNull(carTest);
        Assert.assertEquals(carTest.get().getName(), "kaptur_test");
    }

    @After
    public void deleteCar() {
        carRepository.deleteById(3);
        List<CarJpaRelease> cars = carRepository.findAll();
        Assert.assertNotNull(cars);
        Assert.assertEquals(cars.size(), 2);
    }



}
