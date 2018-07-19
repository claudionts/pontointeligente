package com.estudo.pontointeligente.services

import com.estudo.pontointeligente.documents.Empresa
import org.springframework.data.annotation.Id

interface EmpresaService {

    fun buscarPorCnpj(cnpj: String): Empresa?

    fun findById(id: String): Empresa?

    fun persistir(empresa: Empresa): Empresa

}