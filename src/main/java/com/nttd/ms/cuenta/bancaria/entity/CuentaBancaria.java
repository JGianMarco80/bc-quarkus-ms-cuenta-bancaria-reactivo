package com.nttd.ms.cuenta.bancaria.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@MongoEntity(collection = "cuenta_bancaria")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaBancaria {

    private ObjectId id;

    private String numeroCuenta;

    private String cci;

    //1: cuenta principal
    //2: cuenta secundaria
    private String tipoCuenta;

    //1: soles
    //2: dolares
    private String tipoMoneda;

    private Double saldo;

    private String clienteId;

    private String tarjetaId;

    private String estado;

    private String numeroTarjeta;
}
