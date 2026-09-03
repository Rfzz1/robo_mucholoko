package org.example

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class Senai {

    // =====================================================
    // ABRIR UMA TELA
    // =====================================================

    private fun abrirTela(event: ActionEvent, caminho: String) {

        try {

            println("📂 Tentando abrir: $caminho")

            val recurso = javaClass.getResource(caminho)

            if (recurso == null) {
                println("❌ FXML não encontrado: $caminho")
                return
            }

            println("✅ FXML encontrado!")

            val loader = FXMLLoader(recurso)

            val root: Parent = loader.load()

            println("✅ FXML carregado!")

            val stage = (event.source as Node)
                .scene
                .window as Stage

            stage.scene = Scene(root)

            stage.show()

            println("✅ Tela aberta com sucesso!")

        } catch (e: Exception) {

            println("❌ Erro ao abrir FXML:")
            e.printStackTrace()
        }
    }


    // =====================================================
    // DESENVOLVIMENTO DE SISTEMAS
    // =====================================================

    @FXML
    fun abrirDesenvolvimentoSistemas(event: ActionEvent) {

        val caminho = when (Sessao.idiomaEscolhido) {

            "pt" -> "/senai/videosSenai/Ti_G.fxml"

            "en" -> "/senai/videosSenai/Tu_G_ing.fxml"

            "esp" -> "/senai/videosSenai/Ti_G_esp.fxml"

            else -> {
                println("❌ Idioma inválido: ${Sessao.idiomaEscolhido}")
                return
            }
        }

        println("🌎 Idioma: ${Sessao.idiomaEscolhido}")
        println("📂 Abrindo: $caminho")

        abrirTela(event, caminho)
    }


    // =====================================================
    // POLÍMEROS
    // =====================================================

    @FXML
    fun abrirPolimeros(event: ActionEvent) {

        val caminho = when (Sessao.idiomaEscolhido) {

            "pt" -> "/senai/videosSenai/poli_G.fxml"

            "en" -> "/senai/videosSenai/poli_ing_G.fxml"

            "esp" -> "/senai/videosSenai/poli_esp_G.fxml"

            else -> {
                println("❌ Idioma inválido: ${Sessao.idiomaEscolhido}")
                return
            }
        }

        println("🌎 Idioma: ${Sessao.idiomaEscolhido}")
        println("📂 Abrindo: $caminho")

        abrirTela(event, caminho)
    }


    // =====================================================
    // ROBÓTICA / MECATRÔNICA
    // =====================================================

    @FXML
    fun abrirMecatronica(event: ActionEvent) {

        val caminho = when (Sessao.idiomaEscolhido) {

            "pt" -> "/senai/videosSenai/robotica_G.fxml"

            "en" -> "/senai/videosSenai/robotica_ing_G.fxml"

            "esp" -> "/senai/videosSenai/robotica_esp_G.fxml"

            else -> {
                println("❌ Idioma inválido: ${Sessao.idiomaEscolhido}")
                return
            }
        }

        println("🌎 Idioma: ${Sessao.idiomaEscolhido}")
        println("📂 Abrindo: $caminho")

        abrirTela(event, caminho)
    }


    // =====================================================
    // VOLTAR PARA O MENU PRINCIPAL
    // =====================================================

    @FXML
    fun voltar(event: ActionEvent) {

        val caminho = when (Sessao.idiomaEscolhido) {

            "pt" -> "/menu/menu_rostoG.fxml"

            "en" -> "/menu/menu_rostoG_ing.fxml"

            "esp" -> "/menu/menu_rostoG_esp.fxml"

            else -> {
                println("❌ Idioma inválido: ${Sessao.idiomaEscolhido}")
                return
            }
        }

        println("🔙 Voltando para: $caminho")

        abrirTela(event, caminho)
    }


    // =====================================================
    // VOLTAR PARA O MENU DO SENAI
    // =====================================================

@FXML
fun voltarSenai(event: ActionEvent) {

    val caminho = when (Sessao.idiomaEscolhido) {

        "pt" -> "/senai/SENAI_G_PORT.fxml"

        "en" -> "/senai/SENAI_G_ing.fxml"

        "esp" -> "/senai/SENAI_G_esp.fxml"

        else -> {
            println("❌ Idioma inválido: ${Sessao.idiomaEscolhido}")
            return
        }
    }

    println("🔙 Voltando para SENAI: $caminho")

    abrirTela(event, caminho)
}
}