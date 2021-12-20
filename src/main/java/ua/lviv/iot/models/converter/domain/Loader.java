package ua.lviv.iot.models.converter.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "loader")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Loader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "surname", nullable = false, length = 45)
    private String surname;

    @Column(name = "mobile_phone", length = 12, columnDefinition = "char")
    private String mobilePhone;

    @Column(name = "company", length = 45)
    private String company;

    @Column(name = "email", length = 45)
    private String email;

    @ManyToOne
    @JoinColumn(name = "full_adress_id", referencedColumnName = "id")
    private FullAddress fullAddress;

    @Override
    public String toString() {
        return String.format("Id: %2d, Name: %s, Surname: %s, mobile phone: %s, company: %s, email: %s, Address: %s",
                id,
                name,
                surname,
                (mobilePhone != null ? mobilePhone : "-"),
                (company != null ? company : "-"),
                (email != null ? email : "-"),
                (fullAddress != null ? fullAddress.toString() : "-")
        );
    }
}
