package com.example.app.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pais implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idPais;
    private String nombre;
    private List<Departamento> departamentos;

}
