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
            // 1. Descobre o caminho relativo
            val arquivoExe = File(System.getProperty("user.dir"), "jogo/WorkBot.exe")

            if (arquivoExe.exists()) {
                // 2. Minimiza a tela da barriga usando safe call (?)
                Sessao.palcoBarriga?.isIconified = true

                // 3. Inicia o processo do jogo
                val processo = ProcessBuilder(arquivoExe.absolutePath)
                processo.directory(arquivoExe.parentFile)
                val procAtivo = processo.start()

                // 4. Cria uma thread em segundo plano para esperar o jogo fechar
                Thread {
                    procAtivo.waitFor()

                    // 5. Quando o jogo fechar, volta a exibir a barriga em tela cheia usando safe call (?)
                    Platform.runLater {
                        Sessao.palcoBarriga?.isIconified = false
                        Sessao.palcoBarriga?.isFullScreen = true
                        Sessao.palcoBarriga?.requestFocus()
                    }
                }.start()

            } else {
                val alertaErro = javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR)
                alertaErro.title = "Erro"
                alertaErro.headerText = "Jogo não encontrado"
                alertaErro.contentText = "Não foi possível encontrar o arquivo em:\n${arquivoExe.absolutePath}"
                alertaErro.showAndWait()
            }

        } catch (e: Exception) {
            val alertaErro = javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR)
            alertaErro.title = "Erro"
            alertaErro.headerText = "Deu erro ao tentar abrir"
            alertaErro.contentText = e.message
            alertaErro.showAndWait()
        }
    }
}