package com.nttd.ms.cuenta.bancaria.service;

import com.nttd.ms.cuenta.bancaria.dto.CuentaBancariaMovimiento;
import io.smallrye.mutiny.Uni;

public interface CuentaBancariaService {

    Uni<CuentaBancariaMovimiento> movimientoCuentaBancaria(String numeroCuenta);

    Uni<Double> emitirRecibirPAgo(String numeroTarjeta, String operacion, Double monto);

}
