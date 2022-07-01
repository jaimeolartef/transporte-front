package com.example.app.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Destino implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idDestino;
    private String nombre;
    private String direccion;
    private Integer idCiudad;
    private Integer idTipoDestino;
    private Ciudad ciudad;
    private TipoDestino tipoDestino;
    private List<Envio> envios;
    private List<TipoDestino> tipoDestinos;
    private List<Ciudad> ciudades;
}
