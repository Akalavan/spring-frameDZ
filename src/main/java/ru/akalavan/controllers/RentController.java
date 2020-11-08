package ru.akalavan.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.akalavan.objects.RentJpaRelease;
import ru.akalavan.service.RentService;

import java.util.List;

@RestController
@RequestMapping("rent/v1/rents")
public class RentController {

    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @GetMapping
    @PreAuthorize("hasPermission('rents', 'read')")
    public List<RentJpaRelease> findAll() {
        return rentService.findAllRent();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasPermission('rents', 'findById')")
    public RentJpaRelease findById(@PathVariable String id) {
        return rentService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasPermission('rents', 'createRent')")
    public RentJpaRelease create(@RequestBody RentJpaRelease rent) {
        return rentService.create(rent);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasPermission('rents', 'updateRent')")
    public RentJpaRelease update(@RequestBody RentJpaRelease rent) {
        return rentService.update(rent);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasPermission('rents', 'daleteRent')")
    public void delete(@PathVariable String id) {
        rentService.delete(id);
    }


}
