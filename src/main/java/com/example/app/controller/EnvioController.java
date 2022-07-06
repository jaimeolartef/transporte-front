package com.example.app.controller;

import com.example.app.dto.DetalleEnvio;
import com.example.app.dto.Envio;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

@Controller
public class EnvioController {

    @RequestMapping(value = "/envio")
    public String ver(Model model, RedirectAttributes flash) {
        String urlEnvio = "http://localhost:8080/transporte/envios";
        RestTemplate restTemplate = new RestTemplate();
        Object[] resultado = restTemplate.getForObject(urlEnvio, Object[].class);

        if (Objects.isNull(resultado)) {
            flash.addFlashAttribute("error", "Error al cargar la lista de envio");
            return "redirect:envio";
        }

        ObjectMapper mapper = new ObjectMapper();
        List<Envio> envios = mapper.convertValue(
                resultado,
                new TypeReference<List<Envio>>() { });

        model.addAttribute("envios", envios);
        model.addAttribute("titulo", "Listado de envios");
        return "envio";
    }

    @RequestMapping(value = "/detalle/{idEnvio}")
    public String ver(@PathVariable(value = "idEnvio") Integer idEnvio, Model model, RedirectAttributes flash) {
        String urlEnvio = "http://localhost:8080/transporte/ver-detalle-envio/".concat(idEnvio.toString());
        RestTemplate restTemplate = new RestTemplate();
        Object[] resultado = restTemplate.getForObject(urlEnvio, Object[].class);

        if (Objects.isNull(resultado)) {
            flash.addFlashAttribute("error", "Error al cargar la lista de envio");
            return "redirect:/envio";
        }

        ObjectMapper mapper = new ObjectMapper();
        List<DetalleEnvio> detalleEnvios = mapper.convertValue(
                resultado,
                new TypeReference<List<DetalleEnvio>>() { });

        model.addAttribute("detalles", detalleEnvios);
        model.addAttribute("titulo", "Detalle envio");
        return "detalle";
    }

    @RequestMapping(value = "/envio/eliminar/{idEnvio}")
    public String eliminarCliente(@PathVariable(value = "idEnvio") Integer idEnvio, Model model, RedirectAttributes flash, SessionStatus status) {
        String urlDetalleEnvio = "http://localhost:8080/transporte/eliminar-detalle-envio/".concat(idEnvio.toString());
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(urlDetalleEnvio);

        String urlEnvio = "http://localhost:8080/transporte/eliminar-envio/".concat(idEnvio.toString());
        restTemplate = new RestTemplate();
        restTemplate.delete(urlEnvio);

        flash.addFlashAttribute("success", "El envio se elimino correctamente");
        status.setComplete();

        return "redirect:/envio";
    }
}
