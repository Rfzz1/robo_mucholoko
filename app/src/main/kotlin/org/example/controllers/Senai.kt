package org.example.controllers

import org.example.model.Sessao
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class Senai {

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

    private fun obterCaminhoVideoSenai(nomeBase: String): String {
        val sufixo = when (Sessao.idiomaEscolhido) {
            "pt" -> "port"
            "en" -> "eng"
            "esp" -> "esp"
            else -> "port"
        }
        return "/senai/videosSenai/${nomeBase}_${sufixo}.mp4" 
    }

    private fun obterTelaRetornoSenai(): String {
        return when (Sessao.idiomaEscolhido) {
            "pt" -> "/senai/SENAI_G_PORT.fxml"
            "en" -> "/senai/SENAI_G_ing.fxml"
            "esp" -> "/senai/SENAI_G_esp.fxml"
            else -> "/senai/SENAI_G_PORT.fxml"
        }
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
    fun abrirDesenvolvimentoSistemas(event: ActionEvent) {
        val video = obterCaminhoVideoSenai("ti")
        val titulo = obterTitulo("Desenvolvimento de Sistemas", "Systems Development", "Desarrollo de Sistemas")
        abrirVideo(event, video, obterTelaRetornoSenai(), titulo)
    }

    @FXML
    fun abrirPolimeros(event: ActionEvent) {
        val video = obterCaminhoVideoSenai("pol")
        val titulo = obterTitulo("Polímeros", "Polymers", "Polímeros")
        abrirVideo(event, video, obterTelaRetornoSenai(), titulo)
    }

    @FXML
    fun abrirMecatronica(event: ActionEvent) {
        val video = obterCaminhoVideoSenai("rob")
        val titulo = obterTitulo("Mecatrônica", "Mechatronics", "Mecatrónica")
        abrirVideo(event, video, obterTelaRetornoSenai(), titulo)
    }

    private fun abrirTela(event: ActionEvent, caminho: String) {
        try {
            val loader = FXMLLoader(javaClass.getResource(caminho))
            val root: Parent = loader.load()
            val stage = (event.source as Node).scene.window as Stage
            stage.scene = Scene(root)
            stage.isFullScreen = true
            stage.show()
        } catch (e: Exception) {
            println("❌ Erro ao abrir FXML: $caminho")
            e.printStackTrace()
        }
    }

    @FXML
    fun voltar(event: ActionEvent) {
        val caminho = when (Sessao.idiomaEscolhido) {
            "pt" -> "/menu/menu_rostoG.fxml"
            "en" -> "/menu/menu_rostoG_ing.fxml"
            "esp" -> "/menu/menu_rostoG_esp.fxml"
            else -> return
        }
        abrirTela(event, caminho)
    }

    @FXML
    fun voltarSenai(event: ActionEvent) {
        abrirTela(event, obterTelaRetornoSenai())
    }
}