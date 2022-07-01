package com.example.app.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idDepartamento;
    private String nombre;
    private Integer idPais;
    private List<Ciudad> ciudades;
    
}
