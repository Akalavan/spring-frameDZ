package ru.akalavan.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.akalavan.objects.CarJpaRelease;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarJpaRelease, Integer> {

    List<CarJpaRelease> findByName(String name);

}
