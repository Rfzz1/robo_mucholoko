package org.example.utils

import javafx.event.ActionEvent
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.stage.Stage
import org.example.model.Sessao

object Navegador {

    // Troca de tela genérica
    fun trocarTela(event: ActionEvent, caminhoFxml: String) {
        try {
            println("🔄 Navegador: Carregando FXML -> $caminhoFxml")
            val recurso = javaClass.getResource(caminhoFxml)
            
            if (recurso == null) {
                println("❌ ERRO: FXML não encontrado em $caminhoFxml")
                return
            }

            val root = FXMLLoader.load<Parent>(recurso)
            val palco = (event.source as Node).scene.window as Stage
            
            palco.scene.root = root
            palco.isFullScreen = true
            palco.show()
            
        } catch (e: Exception) {
            println("❌ ERRO CRÍTICO ao tentar trocar de tela para $caminhoFxml: ${e.message}")
            e.printStackTrace()
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