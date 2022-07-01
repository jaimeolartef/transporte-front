package com.example.app.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoProducto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idTipoProducto;
    private String nombre;
    private List<DetalleEnvio> detalleEnvios;
}
