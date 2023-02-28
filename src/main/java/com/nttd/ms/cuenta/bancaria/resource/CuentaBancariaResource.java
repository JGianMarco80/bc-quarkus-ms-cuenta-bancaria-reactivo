package com.nttd.ms.cuenta.bancaria.resource;

import com.nttd.ms.cuenta.bancaria.dto.CuentaBancariaMovimiento;
import com.nttd.ms.cuenta.bancaria.service.CuentaBancariaService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
    public Uni<CuentaBancariaMovimiento> movimientoCuentaBancaria(@QueryParam("numeroCuenta") String numeroCuenta) {
        return cuentaBancariaService.movimientoCuentaBancaria(numeroCuenta);
    }

    @PUT
    @Transactional
    @Path("/actualizar-saldo")
    public Uni<Double> emitirRecibirPAgo(@QueryParam("numeroTarjeta") String numeroTarjeta,
                                         @QueryParam("operacion") String operacion,
                                         @QueryParam("monto")  Double monto) {
        return cuentaBancariaService.emitirRecibirPAgo(numeroTarjeta, operacion, monto);
    }

    @PUT
    @Transactional
    @Path("/actualizar-saldo-cb")
    public Uni<Double> emitirRecibirPagoCB(@QueryParam("numeroCuenta") String numeroCuenta,
                                         @QueryParam("operacion") String operacion,
                                         @QueryParam("monto")  Double monto) {
        return cuentaBancariaService.emitirRecibirPagoCB(numeroCuenta, operacion, monto);
    }

}
