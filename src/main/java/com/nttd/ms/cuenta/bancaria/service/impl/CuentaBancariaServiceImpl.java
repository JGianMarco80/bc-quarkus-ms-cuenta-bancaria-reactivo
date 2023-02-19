package com.nttd.ms.cuenta.bancaria.service.impl;

import com.nttd.ms.cuenta.bancaria.client.CBMovimientoClient;
import com.nttd.ms.cuenta.bancaria.client.model.CBMovimiento;
import com.nttd.ms.cuenta.bancaria.dto.CuentaBancariaMovimiento;
import com.nttd.ms.cuenta.bancaria.dto.Movimiento;
import com.nttd.ms.cuenta.bancaria.entity.CuentaBancaria;
import com.nttd.ms.cuenta.bancaria.repository.CuentaBancariaRepository;
import com.nttd.ms.cuenta.bancaria.service.CuentaBancariaService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import java.util.List;

@ApplicationScoped
public class CuentaBancariaServiceImpl implements CuentaBancariaService {

    @Inject
    CuentaBancariaRepository repository;

    @RestClient
    CBMovimientoClient cbmMovimientoClient;

    @Override
    public Uni<CuentaBancariaMovimiento> movimientoCuentaBancaria(String numeroCuenta) {
        return this.buscarMovimientos(numeroCuenta);
    }

    private Multi<CuentaBancaria> findAllActive(){
        return repository.listAll().onItem()
                .<CuentaBancaria>disjoint().map(cuentaBancaria -> {
                    CuentaBancaria cb = new CuentaBancaria();
                    if(cuentaBancaria.getEstado().equals("1")){
                        cb = cuentaBancaria;
                    }
                    return cb;
                });
    }

    private Uni<CuentaBancariaMovimiento> buscarMovimientos(String numeroCuenta) {
        return this.findAllActive().select()
                .when( cb -> Uni.createFrom().item(cb.getNumeroCuenta().equals(numeroCuenta)) )
                .toUni()
                .onItem()
                .transformToUni( cu -> Uni.createFrom().item(new CuentaBancariaMovimiento() )
                        .onItem().transform(cbmt -> {
                            cbmt.setNumeroCuenta(cu.getNumeroCuenta());
                            cbmt.setSaldo(cu.getSaldo());
                            return cbmt;
                        }))
                .call(cue -> this.listaMovimiento(numeroCuenta).map(lm -> {cue.setMovimientos(lm); return lm;}));

    }

    private Uni<List<Movimiento>> listaMovimiento(String numeroCuenta) {
        return cbmMovimientoClient.findByNumeroCuenta(numeroCuenta)
                .onItem().<CBMovimiento>disjoint().map(cbMovimiento -> {
                    Movimiento movimiento = new Movimiento();
                    movimiento.setDescripcion(cbMovimiento.getDescripcion());
                    if (cbMovimiento.getTipoMovimiento().equals("1")) {
                        movimiento.setMonto(cbMovimiento.getMonto());
                    }
                    if (cbMovimiento.getTipoMovimiento().equals("2")) {
                        movimiento.setMonto(cbMovimiento.getMonto() * -1);
                    }
                    movimiento.setFecha(cbMovimiento.getFecha());
                    return movimiento;
                }).collect().asList();
    }
}
