package ru.akalavan.service;

import ru.akalavan.objects.RentJpaRelease;

import java.util.List;

public interface RentService {

    List<RentJpaRelease> findAllRent();

    RentJpaRelease findById(Object id);

    RentJpaRelease create(RentJpaRelease rent);

    RentJpaRelease update(RentJpaRelease rent);

    void delete(Object id);
}
