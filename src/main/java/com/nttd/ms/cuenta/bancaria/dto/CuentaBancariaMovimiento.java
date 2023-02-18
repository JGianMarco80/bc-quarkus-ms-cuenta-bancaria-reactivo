package com.nttd.ms.cuenta.bancaria.dto;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class CuentaBancariaMovimiento {

    private String id;

    private String numeroCuenta;

    private Double saldo;

    //Movimientos
    private List<Movimiento> movimientos = new ArrayList<>();

}
