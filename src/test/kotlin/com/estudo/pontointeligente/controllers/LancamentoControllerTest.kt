package com.estudo.pontointeligente.controllers

import com.estudo.pontointeligente.documents.Funcionario
import com.estudo.pontointeligente.documents.Lancamento
import com.estudo.pontointeligente.dtos.LancamentoDto
import com.estudo.pontointeligente.enums.PerfilEnum
import com.estudo.pontointeligente.enums.TipoEnum
import com.estudo.pontointeligente.services.FuncionarioService
import com.estudo.pontointeligente.services.LancamentoService
import com.estudo.pontointeligente.utils.SenhaUtils
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.text.SimpleDateFormat
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class LancamentoControllerTest {

    @Autowired private val mockMvc: MockMvc? = null


    @MockBean
    private val lancamentoService: LancamentoService? = null

    @MockBean
    private val funcionarioService: FuncionarioService? = null

    private val urlBase: String = "/api/lancamentos/"
    private val idFuncionario: String = "1"
    private val idLancamento: String = "1"
    private val tipo: String = TipoEnum.INICIO_TRABALHO.name
    private val data: Date = Date()

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    @Test
    @Throws(Exception::class)
    @WithMockUser
    fun testCadastrarLancamento() {
        val lancamento: Lancamento = obterDadosLancamento()

        BDDMockito.given<Funcionario>(funcionarioService?.buscarPorId(idFuncionario))
                .willReturn(funcionario())
        BDDMockito.given(lancamentoService?.persistir(obterDadosLancamento()))
                .willReturn(lancamento)

        mockMvc!!.perform(MockMvcRequestBuilders.post(urlBase)
                .content(obterJsonRequisicaoPost())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.data.tipo").value(tipo))
                .andExpect(jsonPath("$.data.data").value(dateFormat.format(data)))
                .andExpect(jsonPath("$.data.funcionarioId").value(idFuncionario))
                .andExpect(jsonPath("$.erros").isEmpty)

    }

    @Test
    @Throws(Exception::class)
    @WithMockUser
    fun testRemoverLancamento() {
        BDDMockito.given<Lancamento>(lancamentoService?.buscarPorId(idLancamento))
                .willReturn(obterDadosLancamento())
        mockMvc!!.perform(MockMvcRequestBuilders.delete(urlBase + idLancamento)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
    }


    @Throws(JsonProcessingException::class)
    private fun obterJsonRequisicaoPost(): String {
        val lancamentoDto: LancamentoDto = LancamentoDto(
                dateFormat.format(data), tipo, idFuncionario,
                "Descrição", "1.234,4.234")
        val mapper = ObjectMapper()
        return mapper.writeValueAsString(lancamentoDto)
    }

    private fun obterDadosLancamento(): Lancamento =
            Lancamento(data, TipoEnum.valueOf(tipo), idFuncionario,
                    "Descrição", "1.243,4.345", idLancamento)

    private fun funcionario(): Funcionario =
            Funcionario("Nome", "email@email.com",
                    SenhaUtils().gerarBcrypt("123456"),
                    "23145699876", PerfilEnum.ROLE_USUARIO, idFuncionario)
}