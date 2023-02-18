package com.nttd.ms.cuenta.bancaria.resource;

import com.nttd.ms.cuenta.bancaria.dto.CuentaBancariaMovimiento;
import com.nttd.ms.cuenta.bancaria.service.CuentaBancariaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/cuenta-bancaria")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CuentaBancariaResource {

    @Inject
    CuentaBancariaService cuentaBancariaService;

    @GET
    @Path("/movimiento-cuenta-bancaria")
    public CuentaBancariaMovimiento movimientoCuentaBancaria(@QueryParam("numeroCuenta") String numeroCuenta) {
        return cuentaBancariaService.movimientoCuentaBancaria(numeroCuenta);
    }
}
