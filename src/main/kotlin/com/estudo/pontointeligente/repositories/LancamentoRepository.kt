package com.estudo.pontointeligente.repositories

import com.estudo.pontointeligente.documents.Lancamento
import org.springframework.data.domain.AbstractPageRequest
import org.springframework.data.domain.Page
import org.springframework.data.mongodb.repository.MongoRepository

interface LancamentoRepository : MongoRepository<Lancamento, String> {

    fun findByFuncionarioId(funcionarioId: String, pageRequest: AbstractPageRequest): Page<Lancamento>

}