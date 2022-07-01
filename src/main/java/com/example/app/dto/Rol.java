package com.example.app.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rol implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idRol;
	private String nombre;
	private List<Usuario> usuarios;

}
