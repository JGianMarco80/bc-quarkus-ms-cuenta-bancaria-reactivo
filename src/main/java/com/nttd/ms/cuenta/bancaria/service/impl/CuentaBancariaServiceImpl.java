package com.nttd.ms.cuenta.bancaria.service.impl;

import com.nttd.ms.cuenta.bancaria.client.CBMovimientoClient;
import com.nttd.ms.cuenta.bancaria.client.model.CBMovimiento;
import com.nttd.ms.cuenta.bancaria.dto.CuentaBancariaMovimiento;
import com.nttd.ms.cuenta.bancaria.dto.Movimiento;
import com.nttd.ms.cuenta.bancaria.entity.CuentaBancaria;
import com.nttd.ms.cuenta.bancaria.repository.CuentaBancariaRepository;
import com.nttd.ms.cuenta.bancaria.service.CuentaBancariaService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CuentaBancariaServiceImpl implements CuentaBancariaService {

    @Inject
    CuentaBancariaRepository repository;

    @RestClient
    CBMovimientoClient cbmMovimientoClient;

    @Override
    public CuentaBancariaMovimiento movimientoCuentaBancaria(String numeroCuenta) {
        //Trater todos las cuentas bancarias
        List<CuentaBancaria> cuentaBancarias = repository.listAll();

        //Filtrar las cuentas bancarias activas
        List<CuentaBancaria> cuentaBancariasActivas = new ArrayList<>();

        //Crear un objeto de CuentaBancariaMovimiento que sera la respuesta
        CuentaBancariaMovimiento cbObtenida = new CuentaBancariaMovimiento();

        //Recorremos la lista de cuentas bancarias y filtamos por estado 1
        for (CuentaBancaria cb: cuentaBancarias) {
            if (cb.getEstado().equals("1")) {
                cuentaBancariasActivas.add(cb);
            }
        }

        //Recorremos la lista cuentaBancariasActivas y obtenemos el objeto de CuentaBancariaMovimiento filtrado por el numeroCuenta
        for (CuentaBancaria cb: cuentaBancariasActivas) {
            if (cb.getNumeroCuenta().equals(numeroCuenta)) {
                List<CBMovimiento> cbmObtenidos = cbmMovimientoClient.findByNumeroCuenta(cb.getNumeroCuenta());
                cbObtenida.setId(cb.getId().toString());
                cbObtenida.setNumeroCuenta(cb.getNumeroCuenta());
                cbObtenida.setSaldo(cb.getSaldo());

                if ( !(cbmObtenidos == null && cbmObtenidos.size() == 0) ) {
                    for (CBMovimiento cbm: cbmObtenidos) {
                        Movimiento m = new Movimiento();
                        m.setDescripcion(cbm.getDescripcion());
                        if(cbm.getTipoMovimiento().equals("1")) {
                            m.setMonto(cbm.getMonto());
                        }
                        if (cbm.getTipoMovimiento().equals("2")) {
                            m.setMonto(cbm.getMonto() - (cbm.getMonto() * 2));
                        }
                        m.setFecha(cbm.getFecha());
                        cbObtenida.getMovimientos().add(m);
                    }
                }
            }
        }

        return cbObtenida;
    }
}
