package com.example.app.controller;

import com.example.app.dto.Cliente;
import com.example.app.dto.TipoProducto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@SessionAttributes("tipoproducto")
public class TipoProductoController {

    @RequestMapping(value = "/tipoproducto/ver")
    public String ver(Model model, RedirectAttributes flash) {
        String urlTipoProducto = "http://localhost:8080/transporte/tipo-productos";
        RestTemplate restTemplate = new RestTemplate();
        Object[] resultado = restTemplate.getForObject(urlTipoProducto, Object[].class);

        if (Objects.isNull(resultado)) {
            flash.addFlashAttribute("error", "Error al cargar la lista de envio");
            return "redirect:envio";
        }

        ObjectMapper mapper = new ObjectMapper();
        List<TipoProducto> tipoProductos = mapper.convertValue(
                resultado,
                new TypeReference<List<TipoProducto>>() { });

        model.addAttribute("tipoproductos", tipoProductos);
        model.addAttribute("titulo", "Listado de tipo productos");
        return "/tipoproducto/ver";
    }

    @RequestMapping(value = "/tipoproducto/crear")
    public String crear(Map<String, Object> model) {

        TipoProducto tipoProducto = new TipoProducto();
        model.put("tipoproducto", tipoProducto);
        model.put("titulo", "Formulario de tipo producto");
        return "/tipoproducto/crear";
    }

    @PostMapping(value = "/tipoproducto/guardar")
    public String crearTipoProducto(@Validated @ModelAttribute("tipoproducto") TipoProducto tipoProducto, Model model, RedirectAttributes flash, SessionStatus status) {
        String urlTipoProducto = "http://localhost:8080/transporte/guardar-tipo-producto";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resultado = restTemplate.postForEntity(urlTipoProducto, tipoProducto, String.class);

        if (Objects.isNull(resultado)) {
            flash.addFlashAttribute("error", "Error al cargar la lista de tipo de producto");
            return "redirect:/tipoproducto/ver";
        }

        if (resultado.getStatusCode().is2xxSuccessful()) {
            flash.addFlashAttribute("success", "El cliente se guardo correctamente");
            status.setComplete();
        }

        return "redirect:/tipoproducto/ver";
    }

    @RequestMapping(value = "/tipoproducto/eliminar/{idTipoProducto}")
    public String eliminarTipoProducto(@PathVariable(value = "idTipoProducto") Integer idTipoProducto, Model model, RedirectAttributes flash, SessionStatus status) {
        String urlTipoProducto = "http://localhost:8080/transporte/eliminar-tipo-producto/".concat(idTipoProducto.toString());
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(urlTipoProducto);

        flash.addFlashAttribute("success", "El tipo producto se elimino correctamente");
        status.setComplete();

        return "redirect:/tipoproducto/ver";
    }
}
