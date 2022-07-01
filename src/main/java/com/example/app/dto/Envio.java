package com.example.app.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Envio implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idEnvio;
    private String numGuia;
    private Date fechaRegistro;
    private Date fechaEntrega;
    private Double subtotal;
    private Double descuento;
    private Double total;
    private Integer idCliente;
    private Integer idDestino;
    private Integer idVehiculo;
    private Vehiculo vehiculo;
    private Cliente cliente;
    private Destino destino;
    private List<DetalleEnvio> detalleEnvios;
}
