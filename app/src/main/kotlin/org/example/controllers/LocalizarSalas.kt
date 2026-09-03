package org.example.controllers

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import org.example.utils.Navegador
import org.example.model.Sessao // Importante para pegarmos o idioma!

class LocalizarSalas {

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

    // Recebe as 3 traduções e descobre qual usar na hora de tocar o vídeo
    private fun abrirVideoSala(event: ActionEvent, nomeBase: String, pt: String, en: String, esp: String) {
        val caminhoVideo = "/salas/videosLocalizaSalas/${nomeBase}.mp4" 
        
        val sufixo = Navegador.obterSufixoIdioma(padrao = "_port", ing = "_ing", esp = "_esp")
        val caminhoRetorno = "/salas/Localizar_salas_G$sufixo.fxml"
        
        // Define o título de acordo com o idioma
        val titulo = when (Sessao.idiomaEscolhido) {
            "en" -> en
            "esp" -> esp
            else -> pt
        }

        abrirVideo(event, caminhoVideo, caminhoRetorno, titulo)
    }

    @FXML
    fun voltarParaLocalizarSalas(event: ActionEvent) {
        val sufixo = Navegador.obterSufixoIdioma(padrao = "_port", ing = "_ing", esp = "_esp")
        Navegador.trocarTela(event, "/salas/Localizar_salas_G$sufixo.fxml")
    }

    // Traduzindo todos os botões!
    @FXML fun abrirSala1e2(event: ActionEvent) = abrirVideoSala(event, "sala1e2", "Salas 1 e 2", "Rooms 1 and 2", "Salas 1 y 2")
    @FXML fun abrirSala3e4(event: ActionEvent) = abrirVideoSala(event, "sala3e4", "Salas 3 e 4", "Rooms 3 and 4", "Salas 3 y 4")
    @FXML fun abrirDH(event: ActionEvent) = abrirVideoSala(event, "saladh", "Desenvolvimento Humano", "Human Development", "Desarrollo Humano")
    @FXML fun abrirAtf(event: ActionEvent) = abrirVideoSala(event, "atf", "ATF", "ATF", "ATF")
    
    @FXML fun abrirSala5(event: ActionEvent) = abrirVideoSala(event, "sala5", "Sala 5", "Room 5", "Sala 5")
    @FXML fun abrirSala6(event: ActionEvent) = abrirVideoSala(event, "sala6", "Sala 6", "Room 6", "Sala 6")
    @FXML fun abrirSala7(event: ActionEvent) = abrirVideoSala(event, "sala7", "Sala 7", "Room 7", "Sala 7")
    @FXML fun abrirSala8(event: ActionEvent) = abrirVideoSala(event, "sala8", "Sala 8", "Room 8", "Sala 8")
    @FXML fun abrirSala9(event: ActionEvent) = abrirVideoSala(event, "sala9", "Sala 9", "Room 9", "Sala 9")
    @FXML fun abrirSala10(event: ActionEvent) = abrirVideoSala(event, "sala10", "Sala 10", "Room 10", "Sala 10")
    
    @FXML fun abrirLabPolimeros(event: ActionEvent) = abrirVideoSala(event, "labPolimeros", "Laboratório de Polímeros", "Polymer Laboratory", "Laboratorio de Polímeros")
    @FXML fun abrirLabInformatica(event: ActionEvent) = abrirVideoSala(event, "labInformatica", "Laboratório de Informática", "Computer Laboratory", "Laboratorio de Informática")
    @FXML fun abrirLabRobotica(event: ActionEvent) = abrirVideoSala(event, "robotica", "Laboratório de Robótica", "Robotics Laboratory", "Laboratorio de Robótica")

    @FXML
    fun voltarParaMenu(event: ActionEvent) {
        val sufixo = Navegador.obterSufixoIdioma(padrao = "", ing = "_ing", esp = "_esp")
        Navegador.trocarTela(event, "/menu/menu_rostoG$sufixo.fxml")
    }
}