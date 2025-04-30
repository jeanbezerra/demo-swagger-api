package com.jeanbezerra.calculadora.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/date-example")
@Tag(name = "Date Example API", description = "API para demonstrar retornos com diferentes tipos e formatações de datas")
public class DateExampleController {

    @GetMapping("/localdate")
    @Operation(
        summary = "Retorna a data atual (LocalDate)",
        description = "Retorna a data atual no formato ISO (yyyy-MM-dd)."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Data atual em LocalDate",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
    )
    public Map<String, String> getCurrentLocalDate() {
        LocalDate localDate = LocalDate.now();
        Map<String, String> response = new HashMap<>();
        response.put("localDate", localDate.toString());
        return response;
    }

    @GetMapping("/localtime")
    @Operation(
        summary = "Retorna o horário atual (LocalTime)",
        description = "Retorna o horário atual no formato padrão (HH:mm:ss)."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Horário atual em LocalTime",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
    )
    public Map<String, String> getCurrentLocalTime() {
        LocalTime localTime = LocalTime.now();
        Map<String, String> response = new HashMap<>();
        response.put("localTime", localTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        return response;
    }

    @GetMapping("/localdatetime")
    @Operation(
        summary = "Retorna a data e hora atual (LocalDateTime)",
        description = "Retorna a data e hora atual no formato ISO (yyyy-MM-dd'T'HH:mm:ss)."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Data e hora atual em LocalDateTime",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
    )
    public Map<String, String> getCurrentLocalDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Map<String, String> response = new HashMap<>();
        response.put("localDateTime", localDateTime.toString());
        return response;
    }

    @GetMapping("/offsetdatetime")
    @Operation(
        summary = "Retorna a data e hora atual com offset (OffsetDateTime)",
        description = "Retorna a data e hora atual com offset do sistema no formato ISO (yyyy-MM-dd'T'HH:mm:ssXXX)."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Data e hora atual com offset",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
    )
    public Map<String, String> getCurrentOffsetDateTime() {
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        Map<String, String> response = new HashMap<>();
        response.put("offsetDateTime", offsetDateTime.toString());
        return response;
    }

    @GetMapping("/date")
    @Operation(
        summary = "Retorna a data atual (java.util.Date)",
        description = "Retorna a data atual usando java.util.Date formatada no padrão RFC_1123 (EEE, dd MMM yyyy HH:mm:ss z)."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Data atual formatada com java.util.Date",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
    )
    public Map<String, String> getCurrentUtilDate() {
        Date date = new Date();
        String formattedDate = DateTimeFormatter.RFC_1123_DATE_TIME
                .format(date.toInstant().atZone(ZoneId.systemDefault()));
        Map<String, String> response = new HashMap<>();
        response.put("date", formattedDate);
        return response;
    }

    @GetMapping("/custom")
    @Operation(
        summary = "Retorna a data atual com formatação customizada",
        description = "Retorna a data e hora atual no formato customizado 'dd/MM/yyyy HH:mm:ss'."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Data e hora atual formatada customizadamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
    )
    public Map<String, String> getCustomFormattedDate() {
        LocalDateTime now = LocalDateTime.now();
        String formatted = now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        Map<String, String> response = new HashMap<>();
        response.put("customDate", formatted);
        return response;
    }
}
