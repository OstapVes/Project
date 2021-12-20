package ua.lviv.iot.models.converter.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "machine_model")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MachineModel {
    @Id
    @Column(name = "model", length = 50)
    private String model;

    @Column(name = "max_capacity", nullable = false)
    private Integer capacity;

    @Column(name = "electricity_consumption_wh", nullable = false)
    private Integer electricityConsumption;

    @Override
    public String toString() {
        return String.format("Model: %s, capacity: %s, electricity consumption: %s wh",
                model,
                capacity,
                electricityConsumption
        );
    }
}
