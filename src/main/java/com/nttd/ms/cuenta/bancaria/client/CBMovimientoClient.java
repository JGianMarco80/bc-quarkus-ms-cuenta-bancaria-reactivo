package com.nttd.ms.cuenta.bancaria.client;

import com.nttd.ms.cuenta.bancaria.client.model.CBMovimiento;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient
@Path("/cuenta-bancaria-movimiento")
public interface CBMovimientoClient {

    @GET
    Uni<List<CBMovimiento>> findByNumeroCuenta(@QueryParam("numeroCuenta") String numeroCuenta);
}
