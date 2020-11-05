package service.mock;

import org.springframework.stereotype.Service;
import ru.akalavan.objects.CarJpaRelease;
import ru.akalavan.objects.RentJpaRelease;
import ru.akalavan.service.RentService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MockRentService implements RentService {

    @Override
    public List<RentJpaRelease> findAllRent() {
        return new ArrayList<>();
    }

    @Override
    public RentJpaRelease findById(Object id) {
        return new RentJpaRelease(8, 2000, null, new Date(), new CarJpaRelease());
    }

    @Override
    public RentJpaRelease create(RentJpaRelease rent) {
        return rent;
    }

    @Override
    public RentJpaRelease update(RentJpaRelease rent) {
        return rent;
    }

    @Override
    public void delete(Object id) {

    }
}
