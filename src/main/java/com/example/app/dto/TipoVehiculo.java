package com.example.app.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoVehiculo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idTipoVehiculo;
    private String nombre;
    private List<Vehiculo> vehiculos;

}
