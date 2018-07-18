package com.estudo.pontointeligente

import com.estudo.pontointeligente.documents.Empresa
import com.estudo.pontointeligente.documents.Funcionario
import com.estudo.pontointeligente.enums.PerfilEnum
import com.estudo.pontointeligente.repositories.EmpresaRepository
import com.estudo.pontointeligente.repositories.FuncionarioRepository
import com.estudo.pontointeligente.repositories.LancamentoRepository
import com.estudo.pontointeligente.utils.SenhaUtils
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class PontointeligenteApplication(val empresaRepository: EmpresaRepository,
                                  val funcionarioRepository: FuncionarioRepository,
                                  val lancamentoRepository: LancamentoRepository) : CommandLineRunner {

    override fun run(vararg args: String?) {
        empresaRepository.deleteAll()
        funcionarioRepository.deleteAll()
        lancamentoRepository.deleteAll()

        val empresa: Empresa = Empresa("Empresa", "10443887000146")
        empresaRepository.save(empresa)

        val admin: Funcionario = Funcionario("Admin", "admin@empresa.com",
                SenhaUtils().gerarBcrypt("123456"), "25708317000",
                PerfilEnum.ROLE_ADMIN, empresa.id!!)
        funcionarioRepository.save(admin)

        val funcionario: Funcionario = Funcionario("Funciario",
                "funcionario@empresa.com", SenhaUtils().gerarBcrypt("123456"),
                "44325441557", PerfilEnum.ROLE_USUARIO, empresa.id!!)
        funcionarioRepository.save(funcionario)

        println("Empresa ID: " + empresa.id)
        println("Admin ID: " + admin.id)
        println("Funcionario ID: " + funcionario.id)
    }

}



fun main(args: Array<String>) {
    SpringApplication.run(PontointeligenteApplication::class.java, *args)
}
