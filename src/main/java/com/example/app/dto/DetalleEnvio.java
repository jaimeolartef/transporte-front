package com.example.app.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleEnvio implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idDetalleEnvio;
    private Integer idTipoProducto;
    private Integer idEnvio;
    private int cantidad;
    private TipoProducto tipoProducto;
    
}
