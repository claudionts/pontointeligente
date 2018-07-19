package com.estudo.pontointeligente.services.impl

import com.estudo.pontointeligente.documents.Empresa
import com.estudo.pontointeligente.repositories.EmpresaRepository
import com.estudo.pontointeligente.services.EmpresaService
import org.springframework.data.annotation.Id
import org.springframework.stereotype.Service

@Service
class EmpresaServiceImpl(val empresaRepository: EmpresaRepository) : EmpresaService {

    override fun buscarPorCnpj(cnpj: String): Empresa? = empresaRepository.findByCnpj(cnpj)

    override fun persistir(empresa: Empresa): Empresa = empresaRepository.save(empresa)

    override fun findById(id: String): Empresa?  = empresaRepository.findById(id)
}