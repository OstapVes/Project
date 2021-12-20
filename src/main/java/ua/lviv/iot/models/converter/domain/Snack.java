package ua.lviv.iot.models.converter.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "snack")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Snack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "trademark", nullable = false, length = 45)
    private String trademark;

    @Column(name = "snack_type", nullable = false, length = 45)
    private String type;

    @Column(name = "price", nullable = false)
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "snack_producer_id", referencedColumnName = "id", nullable = false)
    private SnackProducer snackProducer;

    @Override
    public String toString() {
        return String.format("Id: %2d, Snack: %s, type: %s, price: %sc, producer: %s",
                id,
                trademark,
                type,
                price,
                snackProducer.getName()
        );
    }
}
