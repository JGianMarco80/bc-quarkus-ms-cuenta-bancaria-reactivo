package com.nttd.ms.cuenta.bancaria.repository;

import com.nttd.ms.cuenta.bancaria.entity.CuentaBancaria;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CuentaBancariaRepository implements ReactivePanacheMongoRepository<CuentaBancaria>  {
}
