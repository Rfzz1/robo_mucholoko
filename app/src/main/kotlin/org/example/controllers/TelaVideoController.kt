package org.example.controllers

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import javafx.scene.media.MediaView
import org.example.utils.Navegador
import java.io.File

class TelaVideoController {

    @FXML
    private lateinit var lblTitulo: Label

    @FXML
    private lateinit var mediaView: MediaView

    private var mediaPlayer: MediaPlayer? = null
    var caminhoDeVolta: String = ""

    fun tocarVideo(caminhoVideo: File, titulo: String) {
        lblTitulo.text = titulo
        val urlVideo = caminhoVideo.toURI().toString()

        try {
            // 1. Limpa qualquer resquício de vídeo anterior para não encavalar memória
            limparMemoriaDoVideo()

            val media = Media(urlVideo)
            mediaPlayer = MediaPlayer(media)

            mediaPlayer?.setOnError {
                println("Motivo da falha no vídeo: ${mediaPlayer?.error?.message}")
            }

            // 2. O SEGREDO: Só coloca o vídeo na tela e dá Play quando ele estiver 100% carregado no buffer
            mediaPlayer?.setOnReady {
                mediaView.mediaPlayer = mediaPlayer
                mediaPlayer?.play()
            }

        } catch (e: Exception) {
            println("Erro ao tentar tocar o vídeo no caminho: ${caminhoVideo.absolutePath}")
            e.printStackTrace()
        }
    }

    @FXML
    fun voltarParaLocalizarSalas(event: ActionEvent) {
        // 3. Limpa o vídeo da memória RAM antes de trocar de tela
        limparMemoriaDoVideo()

        if (caminhoDeVolta.isNotEmpty()) {
            Navegador.trocarTela(event, caminhoDeVolta)
        } else {
            val sufixo = Navegador.obterSufixoIdioma(padrao = "", ing = "_ing", esp = "_esp")
            Navegador.trocarTela(event, "/menu/menu_rostoG$sufixo.fxml")
        }
    }

    // Função auxiliar para garantir que o JavaFX libere a memória do robô
    private fun limparMemoriaDoVideo() {
        if (mediaPlayer != null) {
            mediaPlayer?.stop()
            mediaPlayer?.dispose() // ISSO É FUNDAMENTAL para totens/robôs que rodam o dia todo
            mediaPlayer = null
            mediaView.mediaPlayer = null
        }
    }
}