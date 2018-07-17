package com.estudo.pontointeligente.documents

import com.estudo.pontointeligente.enums.TipoEnum
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class Lancamento(
        val data: Date,
        val tipo: TipoEnum,
        val funcionarioId: String,
        val descricap: String? = "",
        val localizacao: String? = "",
        @Id val id: String? = null
)