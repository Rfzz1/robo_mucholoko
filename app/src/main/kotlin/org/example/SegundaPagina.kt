package org.example

import org.example.model.Sessao
import javafx.animation.PauseTransition
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.layout.Pane
import javafx.stage.Stage
import javafx.util.Duration

class SegundaPagina {

    @FXML
    lateinit var painelPrincipal: Pane

    @FXML
    fun initialize() {

        println("🟢 SegundaPagina iniciou")
        println("🌎 Idioma escolhido: ${Sessao.idiomaEscolhido}")

        val cronometro = PauseTransition(Duration.seconds(10.0))

        cronometro.setOnFinished {
            println("⏰ 10 segundos terminaram")
            avancarParaProximaTela()
        }

        cronometro.play()
    }

    private fun avancarParaProximaTela() {

        try {

            println("➡️ Tentando abrir o menu")

            val caminhoMenu = when (Sessao.idiomaEscolhido) {
                "en" -> "/menu/menu_rostoG_ing.fxml"
                "esp" -> "/menu/menu_rostoG_esp.fxml"
                else -> "/menu/menu_rostoG.fxml"
            }

            println("🌎 Idioma: ${Sessao.idiomaEscolhido}")
            println("📂 Abrindo: $caminhoMenu")

            val arquivo = javaClass.getResource(caminhoMenu)

            if (arquivo == null) {
                println("❌ NÃO ENCONTROU O FXML: $caminhoMenu")
                return
            }

            println("✅ FXML encontrado!")

            val loader = FXMLLoader(arquivo)
            val novoRoot: Parent = loader.load()

            println("✅ FXML carregado!")

            val stage = painelPrincipal.scene.window as Stage

            stage.scene.root = novoRoot
            stage.isFullScreen = true
            stage.show()

            println("🎉 MENU ABERTO!")

        } catch (e: Exception) {

            println("❌ ERRO AO ABRIR O MENU:")
            e.printStackTrace()
        }
    }
}