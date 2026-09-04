package org.example.controllers

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import javafx.scene.layout.StackPane
import org.example.utils.Navegador
import uk.co.caprica.vlcj.factory.MediaPlayerFactory
import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery
import uk.co.caprica.vlcj.javafx.videosurface.ImageViewVideoSurface
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer
import java.io.File

class TelaVideoController {

    @FXML
    private lateinit var lblTitulo: Label

    @FXML
    private lateinit var painelVideo: StackPane 

    var caminhoDeVolta: String = ""

    private var mediaPlayerFactory: MediaPlayerFactory? = null
    private var mediaPlayerVLC: EmbeddedMediaPlayer? = null
    private lateinit var videoImageView: ImageView

    @FXML
    fun initialize() {
        // 1. Força a busca das DLLs do VLC no Windows
        val vlcEncontrado = NativeDiscovery().discover()
        println("🔍 VLC Nativo localizado no sistema? $vlcEncontrado")

        if (!vlcEncontrado) {
            println("❌ ERRO CRÍTICO: O VLC Media Player 64-bits não foi encontrado no Windows!")
            return
        }

        mediaPlayerFactory = MediaPlayerFactory()
        mediaPlayerVLC = mediaPlayerFactory?.mediaPlayers()?.newEmbeddedMediaPlayer()
        
        videoImageView = ImageView()
        
        videoImageView.fitWidthProperty().bind(painelVideo.widthProperty())
        videoImageView.fitHeightProperty().bind(painelVideo.heightProperty())
        videoImageView.isPreserveRatio = true
        
        painelVideo.children.add(videoImageView)

        mediaPlayerVLC?.videoSurface()?.set(ImageViewVideoSurface(videoImageView))
    }

    fun tocarVideo(caminhoVideo: File, titulo: String) {
        lblTitulo.text = titulo

        println("📂 Arquivo existe no disco? ${caminhoVideo.exists()}")
        println("📍 Caminho do vídeo: ${caminhoVideo.absolutePath}")

        if (!caminhoVideo.exists()) {
            println("❌ ERRO: Arquivo de vídeo não existe no caminho informado!")
            return
        }

        try {
            mediaPlayerVLC?.media()?.play(caminhoVideo.absolutePath)
        } catch (e: Exception) {
            println("Erro ao tentar tocar o vídeo no VLC: ${caminhoVideo.absolutePath}")
            e.printStackTrace()
        }
    }

    @FXML
    fun voltarParaLocalizarSalas(event: ActionEvent) {
        limparMemoriaDoVideo()

        if (caminhoDeVolta.isNotEmpty()) {
            Navegador.trocarTela(event, caminhoDeVolta)
        } else {
            val sufixo = Navegador.obterSufixoIdioma(padrao = "", ing = "_ing", esp = "_esp")
            Navegador.trocarTela(event, "/menu/menu_rostoG$sufixo.fxml")
        }
    }

    private fun limparMemoriaDoVideo() {
        mediaPlayerVLC?.controls()?.stop()
        mediaPlayerVLC?.release()
        mediaPlayerFactory?.release()
        mediaPlayerVLC = null
        mediaPlayerFactory = null
    }
}