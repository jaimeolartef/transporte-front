package com.example.app.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {
    private static final long serialVersionUID = 1L;

    private Integer idusuario;
    private String nombre;
    private String clave;
    private boolean habilitado;
    private Integer idRol;
}
