package repository;

import config.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.akalavan.jpa.RentJpaRepository;
import ru.akalavan.objects.RentJpaRelease;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class RentRepositoryTest {

    @Autowired
    private RentJpaRepository rentJpaRepository;

    @Test
    public void getAllRent() {
        List<RentJpaRelease> rents = rentJpaRepository.findAll();
        Assert.assertNotNull(rents);
        Assert.assertEquals(rents.size(), 7);
    }

    @Test
    public void getMaxPriceById() {
        long maxPrice = rentJpaRepository.getMaxPriceByRentCarId(1);
        Assert.assertEquals(maxPrice, 1600);
    }
}
