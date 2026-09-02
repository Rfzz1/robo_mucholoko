package org.example

import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.Label
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import javafx.scene.media.MediaView
import javafx.stage.Stage

class VideoSala {

    @FXML
    private lateinit var videoView: MediaView

    @FXML
    private lateinit var caminhoVideo: Label

    private var mediaPlayer: MediaPlayer? = null

    @FXML
    fun initialize() {
        val nomeVideo = caminhoVideo.text.trim()
        
        val caminhoRelativo = "/salas/videos/mp4/$nomeVideo"
        val url = javaClass.getResource(caminhoRelativo)

        if (url == null) {
            println("Vídeo não encontrado no caminho: $caminhoRelativo")
            return
        }

        try {
            val media = Media(url.toExternalForm())
            val player = MediaPlayer(media)
            this.mediaPlayer = player

            videoView.mediaPlayer = player

            player.setOnReady {
                Platform.runLater {
                    videoView.isVisible = true
                    videoView.toFront()
                    player.play()
                }
            }

            player.setOnError {
                println("Erro no player: ${player.error?.message}")
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @FXML
    fun voltarParaLocalizarSalas(event: ActionEvent) {
        // Interrompe a reprodução e libera a memória antes de trocar de tela
        mediaPlayer?.stop()
        mediaPlayer?.dispose()

        try {
            val caminhoFxml = when (Sessao.idiomaEscolhido) {
                "en" -> "/salas/Localizar_salas_G_ing.fxml"
                "esp" -> "/salas/Localizar_salas_G_esp.fxml"
                else -> "/salas/Localizar_salas_G_port.fxml"
            }

            val loader = FXMLLoader(javaClass.getResource(caminhoFxml))
            val novoRoot = loader.load<Parent>()

            val palcoAtual = (event.source as Node).scene.window as Stage

            palcoAtual.scene.root = novoRoot
            palcoAtual.isFullScreen = true

            println("Voltando para Localizar Salas")

        } catch (e: Exception) {
            println("Erro ao voltar para Localizar Salas: ${e.message}")
            e.printStackTrace()
        }
    }
}