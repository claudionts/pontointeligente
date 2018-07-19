package com.estudo.pontointeligente.repositories

import com.estudo.pontointeligente.documents.Empresa
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.repository.MongoRepository

interface EmpresaRepository : MongoRepository<Empresa, String> {

    fun findByCnpj(cnpj: String): Empresa

    fun findById(id: String): Empresa?

}