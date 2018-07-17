package com.estudo.pontointeligente.services

import com.estudo.pontointeligente.documents.Lancamento
import org.springframework.data.domain.AbstractPageRequest
import org.springframework.data.domain.Page

interface LancamentoService {

    fun buscarPorFuncionarioId(funcionarioId: String, pageRequest: AbstractPageRequest): Page<Lancamento>

    fun buscarPorId(id:String): Lancamento?

    fun persistir(lancamento: Lancamento): Lancamento

    fun remover(id: String)

}