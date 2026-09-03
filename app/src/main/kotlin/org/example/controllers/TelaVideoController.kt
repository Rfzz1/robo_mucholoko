package org.example.controllers

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import javafx.scene.media.MediaView
import org.example.utils.Navegador

class TelaVideoController {

    @FXML
    private lateinit var lblTitulo: Label

    @FXML
    private lateinit var mediaView: MediaView

    private var mediaPlayer: MediaPlayer? = null
    var caminhoDeVolta: String = ""

    fun tocarVideo(caminhoVideo: String, titulo: String) {
        // Muda o texto do título lá no topo da tela
        lblTitulo.text = titulo 
        
        // Carrega e toca o vídeo
        val urlVideo = javaClass.getResource(caminhoVideo)?.toExternalForm()

        if (urlVideo != null) {
            val media = Media(urlVideo)
            mediaPlayer = MediaPlayer(media)
            mediaView.mediaPlayer = mediaPlayer
            
            mediaPlayer?.play()
        } else {
            println("Erro: Vídeo não encontrado no caminho: $caminhoVideo")
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