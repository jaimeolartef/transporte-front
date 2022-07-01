package com.example.app.controller;

import com.example.app.dto.Cliente;
import com.example.app.dto.TipoDocumento;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Controller
public class ClienteController {

    @RequestMapping(value = "/cliente/ver")
    public String ver(Model model, RedirectAttributes flash) {
        String urlCliente = "http://localhost:8080/transporte/clientes";
        RestTemplate restTemplate = new RestTemplate();
        Object[] resultado = restTemplate.getForObject(urlCliente, Object[].class);

        if (Objects.isNull(resultado)) {
            flash.addFlashAttribute("error", "Error al cargar la lista de clientes");
            return "redirect:/envio";
        }

        ObjectMapper mapper = new ObjectMapper();
        List<Cliente> clientes = mapper.convertValue(
                resultado,
                new TypeReference<List<Cliente>>() { });

        model.addAttribute("clientes", clientes);
        model.addAttribute("titulo", "Listado de clientes");
        return "/cliente/ver";
    }

    @RequestMapping(value = "/crearcliente")
    public String crear(Map<String, Object> model) {

        Cliente cliente = new Cliente();
        cliente.setTipoDocumentos(tipoDocumentos());
        model.put("cliente", cliente);
        model.put("titulo", "Formulario de Cliente");
        return "/cliente/crear";
    }

    private List<TipoDocumento> tipoDocumentos() {
        String urlTipoDestino = "http://localhost:8080/transporte/tipo-documentos";
        RestTemplate restTemplate = new RestTemplate();
        Object[] resultado = restTemplate.getForObject(urlTipoDestino, Object[].class);

        if (Objects.isNull(resultado)) {
            return new ArrayList<>();
        }

        ObjectMapper mapper = new ObjectMapper();
        List<TipoDocumento> tipoDocumentos = mapper.convertValue(
                resultado,
                new TypeReference<List<TipoDocumento>>() { });
        return tipoDocumentos;
    }

    @PostMapping(value = "/cliente/guardar")
    public String crearCliente(@Validated @ModelAttribute("cliente") Cliente cliente, Model model, RedirectAttributes flash, SessionStatus status) {
        String urlCliente = "http://localhost:8080/transporte/guardar-cliente";
        cliente.setNombre("a");
        cliente.setTelefono("123");
        cliente.setDireccion("calle");
        cliente.setNumDocumento("434343");
        cliente.setIdTipoDocumento(1);
        RestTemplate restTemplate = new RestTemplate();
        Object[] resultado = restTemplate.postForObject(urlCliente, cliente, Object[].class);

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
