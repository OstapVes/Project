package ua.lviv.iot.models.converter.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;



@Data
@Entity
@Table(name = "country")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Override
    public String toString() {
        return String.format("Country id: %2d, Country: %s", id, name);
    }
}
