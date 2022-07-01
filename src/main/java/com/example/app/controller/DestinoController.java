package com.example.app.controller;

import com.example.app.dto.Ciudad;
import com.example.app.dto.Cliente;
import com.example.app.dto.Destino;
import com.example.app.dto.TipoDestino;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@SessionAttributes("destino")
public class DestinoController {

    @RequestMapping(value = "/destino/ver")
    public String ver(Model model, RedirectAttributes flash) {
        String urlDestino = "http://localhost:8080/transporte/destinos";
        RestTemplate restTemplate = new RestTemplate();
        Object[] resultado = restTemplate.getForObject(urlDestino, Object[].class);

        if (Objects.isNull(resultado)) {
            flash.addFlashAttribute("error", "Error al cargar la lista de destinos");
            return "redirect:/envio";
        }

        ObjectMapper mapper = new ObjectMapper();
        List<Destino> destinos = mapper.convertValue(
                resultado,
                new TypeReference<List<Destino>>() { });

        model.addAttribute("destinos", destinos);
        model.addAttribute("titulo", "Listado de destinos");
        return "/destino/ver";
    }

    @RequestMapping(value = "/destino/crear")
    public String crear(Map<String, Object> model) {

        Destino destino = new Destino();
        destino.setTipoDestinos(tipoDestinos());
        destino.setCiudades(tipoCiudades());
        model.put("destino", destino);
        model.put("titulo", "Formulario de Cliente");
        return "/destino/crear";
    }

    private List<TipoDestino> tipoDestinos() {
        String urlTipoDestino = "http://localhost:8080/transporte/tipo-destinos";
        RestTemplate restTemplate = new RestTemplate();
        Object[] resultado = restTemplate.getForObject(urlTipoDestino, Object[].class);

        if (Objects.isNull(resultado)) {
            return new ArrayList<>();
        }

        ObjectMapper mapper = new ObjectMapper();
        List<TipoDestino> tipoDestinos = mapper.convertValue(
                resultado,
                new TypeReference<List<TipoDestino>>() { });
        return tipoDestinos;
    }

    private List<Ciudad> tipoCiudades() {
        String urlTipoDestino = "http://localhost:8080/transporte/ciudades";
        RestTemplate restTemplate = new RestTemplate();
        Object[] resultado = restTemplate.getForObject(urlTipoDestino, Object[].class);

        if (Objects.isNull(resultado)) {
            return new ArrayList<>();
        }

        ObjectMapper mapper = new ObjectMapper();
        List<Ciudad> tipoCiudades = mapper.convertValue(
                resultado,
                new TypeReference<List<Ciudad>>() { });
        return tipoCiudades;
    }

    @PostMapping(value = "/destino/guardar")
    public String crearCliente(@Validated @ModelAttribute("cliente") Cliente cliente, Model model, RedirectAttributes flash, SessionStatus status) {
        String urlDestino = "http://localhost:8080/transporte/guardar-cliente";
        cliente.setNombre("a");
        cliente.setTelefono("123");
        cliente.setDireccion("calle");
        cliente.setNumDocumento("434343");
        cliente.setIdTipoDocumento(1);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity resultado = restTemplate.postForEntity(urlDestino, cliente, Entity.class);

        if (Objects.isNull(resultado)) {
            flash.addFlashAttribute("error", "Error al cargar la lista de clientes");
            return "redirect:/envio";
        }

        ObjectMapper mapper = new ObjectMapper();
        ResponseEntity responseEntity = mapper.convertValue(
                resultado,
                new TypeReference<ResponseEntity>() { });

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            flash.addFlashAttribute("success", "El cliente se guardo correctamente");
            status.setComplete();
        }

        return "redirect:/cliente/ver";
    }
}
