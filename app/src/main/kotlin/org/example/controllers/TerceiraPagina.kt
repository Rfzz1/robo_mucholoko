package org.example.controllers

import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.Node
import javafx.stage.Stage
import org.example.model.Sessao
import org.example.utils.Navegador
import java.io.File

class TerceiraPagina {

    @FXML
    fun irParaLocalizarSalas(event: ActionEvent) {
        val sufixo = Navegador.obterSufixoIdioma(padrao = "_port", ing = "_ing", esp = "_esp")
        Navegador.trocarTela(event, "/salas/Localizar_salas_G$sufixo.fxml")
    }

    @FXML
    fun abrirSenai(event: ActionEvent) {
        val sufixo = Navegador.obterSufixoIdioma(padrao = "_PORT", ing = "_ing", esp = "_esp")
        Navegador.trocarTela(event, "/senai/SENAI_G$sufixo.fxml")
    }

    @FXML
    fun abrirSaida(event: ActionEvent) {
        val sufixo = Navegador.obterSufixoIdioma(padrao = "_port", ing = "_ing", esp = "_esp")
        Navegador.trocarTela(event, "/saidasdeemergencia/saidas${sufixo}_G.fxml")
    }

    @FXML
    fun voltarParaRosto(event: ActionEvent) {
        Navegador.trocarTela(event, "/rosto/Rosto_robo_GRANDE.fxml")
        Sessao.controllerBarriga?.carregarPaginaHtml("/telarobo/index.html")
    }

    @FXML
    fun abrirFinalizar(event: ActionEvent) {
        Sessao.controllerBarriga?.carregarPaginaHtml("/telarobo/index.html")
        val palcoAtual = (event.source as Node).scene.window as Stage
        palcoAtual.close()

        Sessao.palcoBarriga?.show()
        Sessao.palcoBarriga?.isFullScreen = true
        Sessao.palcoBarriga?.requestFocus()
    }

    // ==========================================
    // CARREGAMENTOS DE HTML (BARRIGA)
    // ==========================================

    @FXML
    fun abrirProgramacaoDiaria(event: ActionEvent) {
        val nome = Navegador.obterSufixoIdioma(padrao = "programacao_diaria", ing = "programacao_diaria_ingles", esp = "programacao_diaria_espanhol")
        Sessao.controllerBarriga?.carregarPaginaHtml("/htmls/programacao_diaria/programacaodia.html")
    }

    @FXML
    fun abrirBemEstar(event: ActionEvent) {
        val nome = Navegador.obterSufixoIdioma(padrao = "bem_estar", ing = "bem_estar_ingles", esp = "bem_estar_espanhol")
        Sessao.controllerBarriga?.carregarPaginaHtml("/htmls/bem_estar/bem_estar.html")
    }

    @FXML
    fun abrirEduca(event: ActionEvent) {
        val pasta = Navegador.obterSufixoIdioma(padrao = "educaport", ing = "educaing", esp = "educaesp")
        Sessao.controllerBarriga?.carregarPaginaHtml("/htmls/educa/educaing/index.html")
    }
    
    @FXML
    fun abrirCeit(event: ActionEvent) {
        // Ajuste os nomes das pastas conforme os arquivos reais que você tem
        val pasta = Navegador.obterSufixoIdioma(padrao = "ceitport", ing = "ceiting", esp = "ceitesp")
        Sessao.controllerBarriga?.carregarPaginaHtml("/htmls/robo/ceit.html")
    }


    @FXML
    fun abrirPet(event: ActionEvent) {
        val pasta = Navegador.obterSufixoIdioma(padrao = "petport", ing = "peting", esp = "petesp")
        Sessao.controllerBarriga?.carregarPaginaHtml("/htmls/robo/pet.html")
    }

    @FXML
    fun abrirAtf(event: ActionEvent) {
        val pasta = Navegador.obterSufixoIdioma(padrao = "atfport", ing = "atfing", esp = "atfesp")
        Sessao.controllerBarriga?.carregarPaginaHtml("/htmls/robo/atf.html")
    }

    @FXML
    fun abrirAcessibilidade(event: ActionEvent) {
        val pasta = Navegador.obterSufixoIdioma(padrao = "acessibilidadeport", ing = "acessibilidadeing", esp = "acessibilidadeesp")
        Sessao.controllerBarriga?.carregarPaginaHtml("/htmls/robo/acessibilidade.html")
    }

    @FXML
    fun iniciarJogo(event: ActionEvent) {
        try {
            var arquivoExe = File(System.getProperty("user.dir"), "jogo/WorkBot.exe")
            
            // Tentativas de fallback para o ambiente de desenvolvimento
            if (!arquivoExe.exists()) {
                arquivoExe = File("app/src/main/roast/WorkBot.exe")
            }
            if (!arquivoExe.exists()) {
                arquivoExe = File("src/main/roast/WorkBot.exe") 
            }

            if (arquivoExe.exists()) {
                val pastaDoJogo = arquivoExe.parentFile
                println("Tentando abrir diretamente o executável: ${arquivoExe.absolutePath}")
                
                // Esconde a Barriga
                Sessao.palcoBarriga?.hide()
                
                Thread {
                    try {
                        val processo = ProcessBuilder(arquivoExe.absolutePath)
                        processo.directory(pastaDoJogo) 
                        
                        val env = processo.environment()
                        env.remove("JAVA_HOME")
                        env.remove("JAVA_TOOL_OPTIONS")
                        env.remove("_JAVA_OPTIONS")
                        
                        val logCrash = File(pastaDoJogo, "log_crash_jogo.txt")
                        processo.redirectErrorStream(true)
                        processo.redirectOutput(logCrash) 
                        
                        val processoAtivo = processo.start()
                        println("O arquivo .exe foi disparado e isolado!")
                        
                        processoAtivo.waitFor() 
                        
                        if (logCrash.exists() && logCrash.length() > 0) {
                            println("O jogo fechou e deixou o seguinte aviso no log:")
                            println(logCrash.readText())
                        }
                        
                        Platform.runLater {
                            println("Restaurando a tela da Barriga...")
                            Sessao.palcoBarriga?.show()
                            Sessao.palcoBarriga?.isFullScreen = true
                            Sessao.palcoBarriga?.requestFocus()
                        }
                    } catch (e: Exception) {
                        println("Erro interno ao disparar a Thread: ${e.message}")
                    }
                }.start()
                
            } else {
                println("ERRO CRÍTICO: O arquivo .exe não foi encontrado em lugar nenhum!")
            }
        } catch (e: Exception) {
            println("Erro geral no botão: ${e.message}")
            e.printStackTrace()
        }
    }
}