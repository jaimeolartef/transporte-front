package com.example.app.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vehiculo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idVehiculo;
    private String nombre;
    private String marca;
    private String modelo;
    private String identificacionVehiculo;
    private Integer idTipoVehiculo;
    private List<Envio> envios;
}
