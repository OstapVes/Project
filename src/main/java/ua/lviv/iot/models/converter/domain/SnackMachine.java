package ua.lviv.iot.models.converter.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "snack_machine")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SnackMachine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne(mappedBy = "snackMachine", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private MachineService machineService;

    @ManyToOne
    @JoinColumn(name = "full_adress_id", referencedColumnName = "id")
    private FullAddress fullAddress;

    @ManyToOne
    @JoinColumn(name = "machine_producer_id", referencedColumnName = "id", nullable = false)
    private MachineProducer machineProducer;

    @ManyToOne
    @JoinColumn(name = "machine_model", referencedColumnName = "model", nullable = false)
    private MachineModel machineModel;

    @Override
    public String toString() {
        return String.format("Machine id: %2d, model: %s, producer: %s, Address: %s",
                id,
                machineModel.getModel(),
                machineProducer.getName(),
                fullAddress.toString()
        );
    }
}
