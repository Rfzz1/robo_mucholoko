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
        // Muda o texto do título lá no topo da tela
        lblTitulo.text = titulo

        // Converte o arquivo físico (File) para o formato de URI (String) que o JavaFX exige
        val urlVideo = caminhoVideo.toURI().toString()

        try {
            val media = Media(urlVideo)
            mediaPlayer = MediaPlayer(media)

            // Escuta silenciosamente qualquer erro de leitura do vídeo e imprime no console
            mediaPlayer?.setOnError {
                println("Motivo da falha no vídeo: ${mediaPlayer?.error?.message}")
            }

            mediaView.mediaPlayer = mediaPlayer

            mediaPlayer?.play()


        } catch (e: Exception) {
            println("Erro ao tentar tocar o vídeo no caminho: ${caminhoVideo.absolutePath}")
            e.printStackTrace()
        }
    }

    @FXML
    fun voltarParaLocalizarSalas(event: ActionEvent) {
        mediaPlayer?.stop()

        if (caminhoDeVolta.isNotEmpty()) {
            Navegador.trocarTela(event, caminhoDeVolta)
        } else {
            val sufixo = Navegador.obterSufixoIdioma(padrao = "", ing = "_ing", esp = "_esp")
            Navegador.trocarTela(event, "/menu/menu_rostoG$sufixo.fxml")
        }
    }
}