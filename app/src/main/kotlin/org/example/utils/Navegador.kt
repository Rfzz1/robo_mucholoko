package org.example.utils

import javafx.event.ActionEvent
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.stage.Stage
import org.example.model.Sessao
import javafx.scene.control.Alert

object Navegador {

    fun trocarTela(event: ActionEvent, caminhoFxml: String) {
        try {
            val recurso = javaClass.getResource(caminhoFxml)
            
            if (recurso == null) {
                // MOSTRAR ALERTA NA TELA SE O FXML NÃO FOR ENCONTRADO
                val alerta = Alert(Alert.AlertType.ERROR)
                alerta.title = "Erro de Navegação"
                alerta.headerText = "Arquivo FXML não encontrado!"
                alerta.contentText = "O sistema tentou abrir:\n$caminhoFxml\nMas o arquivo não existe no executável."
                alerta.showAndWait()
                return
            }

            val root = FXMLLoader.load<Parent>(recurso)
            val palco = (event.source as Node).scene.window as Stage
            
            palco.scene.root = root
            palco.isFullScreen = true
            palco.show()
            
        } catch (e: Exception) {
            // MOSTRAR ALERTA SE O FXML TIVER ERROS INTERNOS (EX: IMAGEM FALTANDO)
            val alerta = Alert(Alert.AlertType.ERROR)
            alerta.title = "Erro ao carregar FXML"
            alerta.headerText = "O FXML foi encontrado, mas algo dentro dele quebrou."
            alerta.contentText = e.toString() + "\nCausa: " + e.cause?.message
            alerta.showAndWait()
        }
    }

    // Resolve o sufixo do idioma automaticamente para FXMLs
    fun obterSufixoIdioma(padrao: String = "", ing: String = "_ing", esp: String = "_esp"): String {
        return when (Sessao.idiomaEscolhido) {
            "en" -> ing
            "esp" -> esp
            else -> padrao
        }
    }
}