package service.mock;

import org.springframework.stereotype.Service;
import ru.akalavan.objects.CarJpaRelease;
import ru.akalavan.service.CarService;

import java.util.ArrayList;
import java.util.List;

@Service
public class MockCarService implements CarService {

    @Override
    public List<CarJpaRelease> findAllCar() {
        return new ArrayList<>();
    }

    @Override
    public CarJpaRelease findById(Object id) {
        return new CarJpaRelease(Integer.parseInt(String.valueOf(id)), "TestCar");
    }

    @Override
    public CarJpaRelease create(CarJpaRelease car) {
        return car;
    }

    @Override
    public CarJpaRelease update(CarJpaRelease car) {
        return car;
    }

    @Override
    public void delete(Object id) {

    }
}
