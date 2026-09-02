package org.example

import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import javafx.scene.media.MediaView
import javafx.stage.Stage

class SaidaVideoController {

    @FXML
    private lateinit var videoView: MediaView

    @FXML
    private lateinit var caminhoVideo: javafx.scene.control.Label

    private var mediaPlayer: MediaPlayer? = null


    // =====================================================
    // INICIAR VÍDEO
    // =====================================================

    @FXML
    fun initialize() {

        val nomeVideo = caminhoVideo.text.trim()

        println("🎬 Vídeo solicitado: $nomeVideo")

        // Primeiro procura nos vídeos do SENAI
        var caminhoVideoRecurso =
            "/senai/videosSenai/mp4/$nomeVideo"

        var url = javaClass.getResource(caminhoVideoRecurso)

        // Se não encontrou, procura nas saídas de emergência
        if (url == null) {

            caminhoVideoRecurso =
                "/saidasdeemergencia/videos/mp4/$nomeVideo"

            url = javaClass.getResource(caminhoVideoRecurso)
        }

        if (url == null) {

            println("❌ VÍDEO NÃO ENCONTRADO!")

            println("Nome: $nomeVideo")
            println("Tentado:")
            println("/senai/videosSenai/mp4/$nomeVideo")
            println("/saidasdeemergencia/videos/mp4/$nomeVideo")

            return
        }

        println("✅ Vídeo encontrado:")
        println(url)

        try {

            val media = Media(url.toExternalForm())

            val player = MediaPlayer(media)

            mediaPlayer = player

            videoView.mediaPlayer = player

            player.setOnReady {

                println("✅ Vídeo pronto!")

                Platform.runLater {

                    videoView.isVisible = true
                    videoView.toFront()

                    player.play()

                    println("▶ Vídeo iniciado!")
                }
            }

            player.setOnError {

                println("❌ ERRO NO PLAYER:")
                println(player.error?.message)
            }

        } catch (e: Exception) {

            println("❌ Erro ao carregar vídeo:")
            e.printStackTrace()
        }
    }


    // =====================================================
    // VOLTAR PARA SENAI
    // =====================================================

    @FXML
    fun voltarSenai(event: ActionEvent) {

        // Para o vídeo antes de trocar de tela
        mediaPlayer?.stop()
        mediaPlayer?.dispose()
        mediaPlayer = null

        val caminho = when (Sessao.idiomaEscolhido) {

            "pt" -> "/senai/SENAI_G_PORT.fxml"

            "en" -> "/senai/SENAI_G_ing.fxml"

            "esp" -> "/senai/SENAI_G_esp.fxml"

            else -> {

                println("❌ Idioma inválido: ${Sessao.idiomaEscolhido}")

                return
            }
        }

        println("🔙 Voltando para SENAI:")
        println(caminho)

        try {

            val recurso = javaClass.getResource(caminho)

            if (recurso == null) {

                println("❌ FXML do SENAI não encontrado:")
                println(caminho)

                return
            }

            val root = FXMLLoader.load<Parent>(recurso)

            val stage =
                (event.source as Node).scene.window as Stage

            stage.scene.root = root

            println("✅ Voltou para SENAI!")

        } catch (e: Exception) {

            println("❌ Erro ao voltar para SENAI:")

            e.printStackTrace()
        }
    }


    // =====================================================
    // VOLTAR PARA SAÍDAS DE EMERGÊNCIA
    // =====================================================

@FXML
fun abrirVoltaremergencia(event: ActionEvent) {

    mediaPlayer?.stop()
    mediaPlayer?.dispose()
    mediaPlayer = null

    val caminho = when (Sessao.idiomaEscolhido) {

        "pt" -> "/saidasdeemergencia/saidas_port_G.fxml"

        "en" -> "/saidasdeemergencia/saidas_ing_G.fxml"

        "esp" -> "/saidasdeemergencia/saidas_esp_G.fxml"

        else -> {
            println("❌ Idioma inválido: ${Sessao.idiomaEscolhido}")
            return
        }
    }

    println("🔙 Voltando para saídas: $caminho")

    val recurso = javaClass.getResource(caminho)

    if (recurso == null) {
        println("❌ FXML não encontrado: $caminho")
        return
    }

    val root = FXMLLoader.load<Parent>(recurso)

    val stage = (event.source as Node)
        .scene
        .window as Stage

    stage.scene.root = root
}
}