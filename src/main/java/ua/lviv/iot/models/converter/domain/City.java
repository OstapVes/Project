package ua.lviv.iot.models.converter.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "city")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class City {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "region", nullable = false, length = 45)
    private String region;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id", nullable = false)
    private Country country;

    @Override
    public String toString() {
        return String.format("City id: %2d, City: %s, Region: %s, %s", id, name, region, country.toString());
    }
}
