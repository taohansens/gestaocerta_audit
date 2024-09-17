package com.gestaocerta.microaudit.controller;

import com.gestaocerta.microaudit.entities.LogMessage;
import com.gestaocerta.microaudit.services.LogConsumer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class ConsumerController {
    private final LogConsumer service;

    @Operation(summary = "Retorna a lista de mensagens do tipo LOG salvas no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a lista de Logs", content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = LogMessage.class)))})})
    @GetMapping(value = "/logs")
    public ResponseEntity<List<LogMessage>> getAllLog(){
        List<LogMessage> messages = service.getAllLogMessages();
        return ResponseEntity.ok().body(messages);
    }

    @Operation(summary = "Retorna a lista de registros do tipo Auditoria salvas no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a lista de registros de ações registradas", content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = LogMessage.class)))})})
    @GetMapping(value = "/audits")
    public ResponseEntity<List<LogMessage>> getAllAudit(){
        List<LogMessage> messages = service.getAllAuditMessages();
        return ResponseEntity.ok().body(messages);
    }

}
