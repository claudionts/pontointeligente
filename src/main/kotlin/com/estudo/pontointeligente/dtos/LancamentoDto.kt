package com.estudo.pontointeligente.dtos

import org.hibernate.validator.constraints.NotEmpty

data class LancamentoDTO (
        @get:NotEmpty(message = "Data não pode ser vazia")
        val data: String? = null,

        @get:NotEmpty(message = "Tipo não pode ser vazio")
        val tipo: String? = null,
        val funcionarioId:String? = null,

        val descricao:String? = null,
        val localizacao:String? = null,

        var id:String? = null
)