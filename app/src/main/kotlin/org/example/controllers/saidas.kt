package org.example.controllers

import org.example.model.Sessao
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class saidas {

    private fun abrirVideo(event: ActionEvent, caminhoVideo: String, caminhoRetorno: String, tituloDaTela: String) {
        try {
            val loader = FXMLLoader(javaClass.getResource("/videos/TelaVideo.fxml"))
            val root: Parent = loader.load()

            val controladorVideo = loader.getController<TelaVideoController>()
            controladorVideo.caminhoDeVolta = caminhoRetorno
            
            controladorVideo.tocarVideo(caminhoVideo, tituloDaTela)

            val stage = (event.source as Node).scene.window as Stage
            stage.scene = Scene(root)
            stage.show()
        } catch (e: Exception) {
            println("❌ Erro ao abrir vídeo: $caminhoVideo")
            e.printStackTrace()
        }
    }

    private fun obterCaminhoVideoSaida(nomeBase: String): String {
        return "/saidasdeemergencia/videosSaidas/${nomeBase}.mp4"
    }

    private fun obterTelaDeVolta(): String {
        val sufixo = when (Sessao.idiomaEscolhido) {
            "pt" -> "port"
            "en" -> "ing"
            "esp" -> "esp"
            else -> "port"
        }
        return "/saidasdeemergencia/saidas_${sufixo}_G.fxml" 
    }

    // Função auxiliar para os títulos
    private fun obterTitulo(pt: String, en: String, esp: String): String {
        return when (Sessao.idiomaEscolhido) {
            "en" -> en
            "esp" -> esp
            else -> pt
        }
    }

    @FXML
    fun abriSaidaproximodoelevador(event: ActionEvent) {
        val video = obterCaminhoVideoSaida("saida_proximo_elevador")
        val titulo = obterTitulo("Saída: Próximo ao Elevador", "Exit: Near the Elevator", "Salida: Cerca del Ascensor")
        abrirVideo(event, video, obterTelaDeVolta(), titulo)
    }

    @FXML
    fun abrirSaidaBemestar(event: ActionEvent) {
        val video = obterCaminhoVideoSaida("saida_bem_estar")
        val titulo = obterTitulo("Saída: Bem-Estar", "Exit: Well-being", "Salida: Bienestar")
        abrirVideo(event, video, obterTelaDeVolta(), titulo)
    }

    @FXML
    fun abrirSaidasegundoandar(event: ActionEvent) {
        val video = obterCaminhoVideoSaida("saida_segundo_andar")
        val titulo = obterTitulo("Saída: Segundo Andar", "Exit: Second Floor", "Salida: Segundo Piso")
        abrirVideo(event, video, obterTelaDeVolta(), titulo)
    }

    @FXML
    fun abrirsaidaauditorio(event: ActionEvent) {
        val video = obterCaminhoVideoSaida("saida_auditorio")
        val titulo = obterTitulo("Saída: Auditório", "Exit: Auditorium", "Salida: Auditorio")
        abrirVideo(event, video, obterTelaDeVolta(), titulo)
    }

    @FXML
    fun abrirVoltar(event: ActionEvent) {
        val caminho = when (Sessao.idiomaEscolhido) {
            "pt" -> "/menu/menu_rostoG.fxml"
            "en" -> "/menu/menu_rostoG_ing.fxml"
            "esp" -> "/menu/menu_rostoG_esp.fxml"
            else -> return
        }

        try {
            val loader = FXMLLoader(javaClass.getResource(caminho))
            val root: Parent = loader.load()
            val stage = (event.source as Node).scene.window as Stage
            stage.scene = Scene(root)
            stage.isFullScreen = true
            stage.show()
        } catch (e: Exception) {
            println("❌ Erro ao voltar para o menu:")
            e.printStackTrace()
        }
    }
}