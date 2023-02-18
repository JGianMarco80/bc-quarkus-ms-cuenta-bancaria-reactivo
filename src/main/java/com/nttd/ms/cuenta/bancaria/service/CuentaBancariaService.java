package com.nttd.ms.cuenta.bancaria.service;

import com.nttd.ms.cuenta.bancaria.dto.CuentaBancariaMovimiento;

public interface CuentaBancariaService {

    CuentaBancariaMovimiento movimientoCuentaBancaria(String numeroCuenta);

}
