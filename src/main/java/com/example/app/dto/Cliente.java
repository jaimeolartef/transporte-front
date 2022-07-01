package com.example.app.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idCliente;
    private String numDocumento;
    private String nombre;
    private String telefono;
    private String direccion;
    private Integer idTipoDocumento;
    private TipoDocumento tipoDocumento;
    private List<TipoDocumento> tipoDocumentos;

}
