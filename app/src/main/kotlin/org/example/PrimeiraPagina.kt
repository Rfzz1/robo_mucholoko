package org.example

import org.example.model.Sessao
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class PrimeiraPagina {
    @FXML
    fun AbrirPt(event: ActionEvent) {
        Sessao.idiomaEscolhido = "pt"
        TrocarTela("/TextoFalado.fxml", event)
    }
    @FXML
    fun AbrirEn(event: ActionEvent) {
        Sessao.idiomaEscolhido = "en"
        TrocarTela("/TextoFaladoIngles.fxml", event)
    }
    @FXML
    fun AbrirEsp(event: ActionEvent) {
        Sessao.idiomaEscolhido = "esp"
        TrocarTela("/TextoFaladoEspanhol.fxml", event)
    }

    private fun TrocarTela(caminhoFxml: String, event: ActionEvent) {
        val arquivo = javaClass.getResource(caminhoFxml)
        val rootNovo: Parent = FXMLLoader.load(arquivo)

        val cenaAtual = (event.source as Node).scene

        cenaAtual.root = rootNovo
    }
}