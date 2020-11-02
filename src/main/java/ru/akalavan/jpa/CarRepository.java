package ru.akalavan.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.akalavan.objects.CarJpaRelease;

@Repository
public interface CarRepository extends JpaRepository<CarJpaRelease, Integer> {

}
