package org.example

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.stage.Stage

class LocalizarSalas {

    private fun abrirVideo(event: ActionEvent, caminhoFxml: String) {
        try {
            val recurso = javaClass.getResource(caminhoFxml)

            if (recurso == null) {
                println("❌ FXML não encontrado: $caminhoFxml")
                return
            }

            val loader = FXMLLoader(recurso)
            val novoRoot = loader.load<Parent>()

            val palcoAtual = (event.source as Node).scene.window as Stage

            palcoAtual.scene.root = novoRoot
            palcoAtual.isFullScreen = true

            println("🎥 Abrindo: $caminhoFxml")

        } catch (e: Exception) {
            println("❌ Erro ao abrir: ${e.message}")
            e.printStackTrace()
        }
    }


    @FXML
    fun voltarParaLocalizarSalas(event: ActionEvent) {
        try {
            val caminhoFxml = when (Sessao.idiomaEscolhido) {
                "en" -> "/salas/Localizar_salas_G_ing.fxml"
                "esp" -> "/salas/Localizar_salas_G_esp.fxml"
                else -> "/salas/Localizar_salas_G_port.fxml"
            }

            val loader = FXMLLoader(javaClass.getResource(caminhoFxml))
            val novoRoot = loader.load<Parent>()

            val stage = (event.source as Node).scene.window as Stage

            stage.scene.root = novoRoot
            stage.isFullScreen = true

            println("⬅️ Voltando para Localizar Salas")
            println("🌐 Idioma: ${Sessao.idiomaEscolhido}")
            println("📂 FXML: $caminhoFxml")

        } catch (e: Exception) {
            println("❌ Erro ao voltar para localizar salas: ${e.message}")
            e.printStackTrace()
        }
    }


    @FXML
    fun abrirSala1e2(event: ActionEvent) {

        val caminho = when (Sessao.idiomaEscolhido) {
            "en" -> "/salas/videos/sala_1_2_ing_G.fxml"
            "esp" -> "/salas/videos/sala_1_2_esp_G.fxml"
            else -> "/salas/videos/sala_1_2_G.fxml"
        }

        abrirVideo(event, caminho)
    }


    @FXML
    fun abrirSala3e4(event: ActionEvent) {

        val caminho = when (Sessao.idiomaEscolhido) {
            "en" -> "/salas/videos/sala_3_4_ing_G.fxml"
            "esp" -> "/salas/videos/sala_3_4_esp_G.fxml"
            else -> "/salas/videos/sala_3_4_G.fxml"
        }

        abrirVideo(event, caminho)
    }


    @FXML
    fun abrirDH(event: ActionEvent) {

        val caminho = when (Sessao.idiomaEscolhido) {
            "en" -> "/salas/videos/dh_ing_G.fxml"
            "esp" -> "/salas/videos/dh_esp_G.fxml"
            else -> "/salas/videos/dh_G.fxml"
        }

        abrirVideo(event, caminho)
    }


    @FXML
    fun abrirAtf(event: ActionEvent) {

        val caminho = when (Sessao.idiomaEscolhido) {
            "en" -> "/salas/videos/atf_ing_G.fxml"
            "esp" -> "/salas/videos/atf_esp_G.fxml"
            else -> "/salas/videos/atf_G.fxml"
        }

        abrirVideo(event, caminho)
    }


    @FXML
    fun abrirSala5(event: ActionEvent) {

        val caminho = when (Sessao.idiomaEscolhido) {
            "en" -> "/salas/videos/sala_5_ing_G.fxml"
            "esp" -> "/salas/videos/sala_5_esp_G.fxml"
            else -> "/salas/videos/sala_5_G.fxml"
        }

        abrirVideo(event, caminho)
    }


    @FXML
    fun abrirSala6(event: ActionEvent) {

        val caminho = when (Sessao.idiomaEscolhido) {
            "en" -> "/salas/videos/sala_6_ing_G.fxml"
            "esp" -> "/salas/videos/sala_6_ing_G.fxml"
            else -> "/salas/videos/sala_6_G.fxml"
        }

        abrirVideo(event, caminho)
    }


    @FXML
    fun abrirSala7(event: ActionEvent) {

        val caminho = when (Sessao.idiomaEscolhido) {
            "en" -> "/salas/videos/sala_7_ing_G.fxml"
            "esp" -> "/salas/videos/sala_7_esp_G.fxml"
            else -> "/salas/videos/sala_7_G.fxml"
        }

        abrirVideo(event, caminho)
    }


    @FXML
    fun abrirSala8(event: ActionEvent) {

        val caminho = when (Sessao.idiomaEscolhido) {
            "en" -> "/salas/videos/sala8_reuniao_ing_G.fxml"
            "esp" -> "/salas/videos/sala8_reuniao_esp_G.fxml"
            else -> "/salas/videos/sala8_reuniao_G.fxml"
        }

        abrirVideo(event, caminho)
    }


    @FXML
    fun abrirSala9(event: ActionEvent) {

        val caminho = when (Sessao.idiomaEscolhido) {
            "en" -> "/salas/videos/sala_9_ing_G.fxml"
            "esp" -> "/salas/videos/sala_9_esp_G.fxml"
            else -> "/salas/videos/sala_9_G.fxml"
        }

        abrirVideo(event, caminho)
    }


    @FXML
    fun abrirSala10(event: ActionEvent) {

        val caminho = when (Sessao.idiomaEscolhido) {
            "en" -> "/salas/videos/sala_10_reuniao_ing_G.fxml"
            "esp" -> "/salas/videos/sala_10_reuniao_esp_G.fxml"
            else -> "/salas/videos/sala_10_reuniao_G.fxml"
        }

        abrirVideo(event, caminho)
    }


    @FXML
    fun abrirLabPolimeros(event: ActionEvent) {

        val caminho = when (Sessao.idiomaEscolhido) {
            "en" -> "/salas/videos/lab_polimeros_ing_G.fxml"
            "esp" -> "/salas/videos/lab_polimeros_esp_G.fxml"
            else -> "/salas/videos/lab_polimeros_G.fxml"
        }

        abrirVideo(event, caminho)
    }


    @FXML
    fun abrirLabInformatica(event: ActionEvent) {

        val caminho = when (Sessao.idiomaEscolhido) {
            "en" -> "/salas/videos/lab_informatica_ing_G.fxml"
            "esp" -> "/salas/videos/lab_informatica_esp_G.fxml"
            else -> "/salas/videos/lab_informatica_G.fxml"
        }

        abrirVideo(event, caminho)
    }


    @FXML
    fun abrirLabRobotica(event: ActionEvent) {

        val caminho = when (Sessao.idiomaEscolhido) {
            "en" -> "/salas/videos/lab_robotica_ing_G.fxml"
            "esp" -> "/salas/videos/lab_robotica_esp_G.fxml"
            else -> "/salas/videos/lab_robotica_G.fxml"
        }

        abrirVideo(event, caminho)
    }


    @FXML
    fun voltarParaMenu(event: ActionEvent) {
        try {

            val caminhoFxml = when (Sessao.idiomaEscolhido) {
                "en" -> "/menu/menu_rostoG_ing.fxml"
                "esp" -> "/menu/menu_rostoG_esp.fxml"
                else -> "/menu/menu_rostoG.fxml"
            }

            val loader = FXMLLoader(javaClass.getResource(caminhoFxml))
            val novoRoot = loader.load<Parent>()

            val palcoAtual = (event.source as Node).scene.window as Stage

            palcoAtual.scene.root = novoRoot
            palcoAtual.isFullScreen = true

            println("⬅️ Voltando para o menu")
            println("🌐 Idioma: ${Sessao.idiomaEscolhido}")
            println("📂 FXML: $caminhoFxml")

        } catch (e: Exception) {
            println("❌ Erro ao voltar para o menu: ${e.message}")
            e.printStackTrace()
        }
    }
}