package ua.lviv.iot.models.converter.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "machine_service")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MachineService {

    @Id
    @Column(name = "snack_machine_id")
    private Integer machineId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "snack_machine_id")
    private SnackMachine snackMachine;

    @Column(name = "last_load")
    private LocalDate lastLoad;

    @Column(name = "last_cash_gathering")
    private LocalDate lastCashGathering;

    @Column(name = "gathered_cash")
    private Integer gatheredCash;

    @Column(name = "last_coint_load")
    private LocalDate lastCoinLoad;

    @Column(name = "loaded_coins")
    private Integer loadedCoins;


    @Override
    public String toString() {
        return String.format(
                "Machine id: %2d, last load: %s, cash gathering: %s, gathered: %s c, coins load: %s, loaded: %s c",
                machineId,
                (lastLoad != null ? lastLoad.toString() : "-"),
                (lastCashGathering != null ? lastCashGathering.toString() : "-"),
                (gatheredCash != null ? gatheredCash : "0"),
                (lastCoinLoad != null ? lastCoinLoad : "-"),
                (loadedCoins != null ? loadedCoins : "0")

        );
    }
}
