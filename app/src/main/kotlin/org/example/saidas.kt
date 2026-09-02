package org.example

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.stage.Stage

class saidas {

    private fun abrirTela(event: ActionEvent, caminhoFxml: String) {

        try {

            println("🚨 Abrindo saída: $caminhoFxml")

            val arquivo = javaClass.getResource(caminhoFxml)

            if (arquivo == null) {
                println("❌ FXML não encontrado: $caminhoFxml")
                return
            }

            val loader = FXMLLoader(arquivo)
            val novoRoot: Parent = loader.load()

            val stage = (event.source as Node).scene.window as Stage

            stage.scene.root = novoRoot
            stage.isFullScreen = true
            stage.show()

            println("✅ Tela aberta com sucesso!")

        } catch (e: Exception) {
            println("❌ Erro ao abrir a tela:")
            e.printStackTrace()
        }
    }


    // =====================================================
    // SAÍDA PRÓXIMA AO ELEVADOR
    // =====================================================

    @FXML
    fun abriSaidaproximodoelevador(event: ActionEvent) {

        var caminho = ""

        when (Sessao.idiomaEscolhido) {

            "pt" -> caminho =
                "/saidasdeemergencia/videos/saida_proximo_elevador_port.fxml"

            "en" -> caminho =
                "/saidasdeemergencia/videos/saida_proximo_elevador_ing.fxml"

            "esp" -> caminho =
                "/saidasdeemergencia/videos/saida_proximo_elevador_esp.fxml"
        }

        if (caminho.isEmpty()) {
            println("❌ Idioma não reconhecido: ${Sessao.idiomaEscolhido}")
            return
        }

        abrirTela(event, caminho)
    }


    // =====================================================
    // SAÍDA BEM-ESTAR
    // =====================================================

    @FXML
    fun abrirSaidaBemestar(event: ActionEvent) {

        var caminho = ""

        when (Sessao.idiomaEscolhido) {

            "pt" -> caminho =
                "/saidasdeemergencia/videos/saida_bem_estar_port.fxml"

            "en" -> caminho =
                "/saidasdeemergencia/videos/saida_bem_estar_ing.fxml"

            "esp" -> caminho =
                "/saidasdeemergencia/videos/saida_bem_estar_esp.fxml"
        }

        if (caminho.isEmpty()) {
            println("❌ Idioma não reconhecido: ${Sessao.idiomaEscolhido}")
            return
        }

        abrirTela(event, caminho)
    }


    // =====================================================
    // SAÍDA SEGUNDO ANDAR
    // =====================================================

    @FXML
    fun abrirSaidasegundoandar(event: ActionEvent) {

        var caminho = ""

        when (Sessao.idiomaEscolhido) {

            "pt" -> caminho =
                "/saidasdeemergencia/videos/saida_segundo_andar_port.fxml"

            "en" -> caminho =
                "/saidasdeemergencia/videos/saida_segundo_andar_ing.fxml"

            "esp" -> caminho =
                "/saidasdeemergencia/videos/saida_segundo_andar_esp.fxml"
        }

        if (caminho.isEmpty()) {
            println("❌ Idioma não reconhecido: ${Sessao.idiomaEscolhido}")
            return
        }

        abrirTela(event, caminho)
    }


    // =====================================================
    // SAÍDA AUDITÓRIO
    // =====================================================

@FXML
fun abrirsaidaauditorio(event: ActionEvent) {

    var caminho = ""

    when (Sessao.idiomaEscolhido) {

        "pt" -> caminho =
            "/saidasdeemergencia/videos/saida_auditorio_port.fxml"

        "en" -> caminho =
            "/saidasdeemergencia/videos/saida_auditorio_ing.fxml"

        "esp" -> caminho =
            "/saidasdeemergencia/videos/saida_auditorio_esp.fxml"
    }

    if (caminho.isEmpty()) {
        println("❌ Idioma não reconhecido: ${Sessao.idiomaEscolhido}")
        return
    }

    abrirTela(event, caminho)
}


    // =====================================================
    // VOLTAR PARA O MENU
    // =====================================================
@FXML
fun abrirVoltar(event: ActionEvent) {
    try {

        var caminho = ""

        when (Sessao.idiomaEscolhido) {

            "pt" -> caminho =
                "/menu/menu_rostoG.fxml"

            "en" -> caminho =
                "/menu/menu_rostoG_ing.fxml"

            "esp" -> caminho =
                "/menu/menu_rostoG_esp.fxml"
        }

        if (caminho.isEmpty()) {
            println("❌ Idioma não reconhecido: ${Sessao.idiomaEscolhido}")
            return
        }

        println("🔙 Voltando para o menu: $caminho")

        val arquivo = javaClass.getResource(caminho)

        if (arquivo == null) {
            println("❌ Menu não encontrado: $caminho")
            return
        }

        val loader = FXMLLoader(arquivo)
        val novoRoot: Parent = loader.load()

        val stage = (event.source as Node).scene.window as Stage

        stage.scene.root = novoRoot
        stage.isFullScreen = true
        stage.show()

        println("✅ Voltou para o menu!")

    } catch (e: Exception) {
        println("❌ Erro ao voltar:")
        e.printStackTrace()
    }
}


    // =====================================================
    // VOLTAR PARA SAÍDAS DE EMERGÊNCIA
    // =====================================================

    
}