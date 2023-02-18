package com.nttd.ms.cuenta.bancaria.client.model;

import lombok.Data;
import org.bson.types.ObjectId;
import java.time.LocalDate;

@Data
public class CBMovimiento {

    private ObjectId id;

    private String numeroCuenta;

    private String tipoMovimiento;

    private String descripcion;

    private Double monto;

    private LocalDate fecha;

}
