package com.fiap.parkmongoapi.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ticket")
@Tag(name = "Ticket", description = "API para gerenciar park tickets")
public class TicketController {
}
