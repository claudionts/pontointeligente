package com.estudo.pontointeligente.services.impl

import com.estudo.pontointeligente.documents.Lancamento
import com.estudo.pontointeligente.repositories.LancamentoRepository
import com.estudo.pontointeligente.services.LancamentoService
import org.springframework.data.domain.AbstractPageRequest
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class LancamentoServiceImpl(val lancamentoRepository: LancamentoRepository)  : LancamentoService {
    override fun buscarPorFuncionarioId(funcionarioId: String, pageRequest: AbstractPageRequest): Page<Lancamento> =
            lancamentoRepository.findByFuncionarioId(funcionarioId, pageRequest)

    override fun buscarPorId(id: String): Lancamento? = lancamentoRepository.findOne(id)

    override fun persistir(lancamento: Lancamento): Lancamento = lancamentoRepository.save(lancamento)

    override fun remover(id: String) = lancamentoRepository.delete(id)

}