package ru.akalavan.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.akalavan.objects.RentJdbcRelease;
import ru.akalavan.objects.RentJpaRelease;

import java.util.List;

@Repository
public interface RentJpaRepository extends JpaRepository<RentJpaRelease, Long> {

    @Query(value = "select max(price) from rent where rent_car = :rent_carId", nativeQuery = true)
    Integer getMaxPriceByRentCarId(@Param("rent_carId") long rent_carId);

    List<RentJpaRelease> findByDateToIsNull();
    List<RentJpaRelease> findByRentCarName(String name);

}
