package com.estudo.pontointeligente.controllers

import com.estudo.pontointeligente.services.EmpresaService
import com.estudo.pontointeligente.documents.Empresa
import com.estudo.pontointeligente.dtos.EmpresaDto
import com.estudo.pontointeligente.response.Response
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/empresas")
class EmpresaController(val empresaService: EmpresaService) {

    @GetMapping(value = "/{id}")
    fun buscarPorId(@PathVariable("id") id: String): ResponseEntity<Response<EmpresaDto>> {
        val response: Response<EmpresaDto> = Response<EmpresaDto>()
        val empresa: Empresa? = empresaService.findById(id)

        if (empresa == null) {
            response.erros.add("Empresa não econtrada para o ID ${id}")
            return ResponseEntity.badRequest().body(response)
        }

        response.data = converterEmpresaDto(empresa)
        return ResponseEntity.ok(response)
    }

    @GetMapping(value = "/cnpj/{cnpj}")
    fun buscarPorCnpj(@PathVariable("cnpj") cnpj: String): ResponseEntity<Response<EmpresaDto>> {
        val response: Response<EmpresaDto> = Response<EmpresaDto>()
        val empresa: Empresa? = empresaService.buscarPorCnpj(cnpj)

        if (empresa == null) {
            response.erros.add("Empresa não econtrada para o CNPJ ${cnpj}")
            return ResponseEntity.badRequest().body(response)
        }

        response.data = converterEmpresaDto(empresa)
        return ResponseEntity.ok(response)
    }

    private fun converterEmpresaDto(empresa: Empresa): EmpresaDto =
            EmpresaDto(empresa.cnpj, empresa.razaoSocial, empresa.id)
}