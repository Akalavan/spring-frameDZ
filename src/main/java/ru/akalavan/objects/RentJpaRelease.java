package ru.akalavan.objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rent")
@NoArgsConstructor
@Setter
@Getter
public class RentJpaRelease {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rent_id_seq")
    @SequenceGenerator(name = "rent_id_seq", sequenceName = "rent_id_seq", allocationSize = 1)
    private long id;
    @Column(name = "price")
    private int price;
    @Column(name = "date_from")
    private Date dateFrom;
    @Column(name = "date_to")
    private Date dateTo;
    @OneToOne
    @JoinColumn(name = "rent_car", referencedColumnName = "id", nullable = false)
    private CarJpaRelease rentCar;
}
