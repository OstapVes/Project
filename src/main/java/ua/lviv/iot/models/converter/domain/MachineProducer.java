package ua.lviv.iot.models.converter.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "machine_producer")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MachineProducer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Column(name = "mobile_phone", columnDefinition = "char", length = 12, nullable = false)
    private String mobilePhone;

    @ManyToOne
    @JoinColumn(name = "full_adress_id", referencedColumnName = "id", nullable = false)
    private FullAddress fullAddress;

    @Override
    public String toString() {
        return String.format("Id: %2d, Producer: %s, email: %s, mobile phone: %s, Address: %s",
                id,
                name,
                email,
                mobilePhone,
                fullAddress.toString()
        );
    }
}
