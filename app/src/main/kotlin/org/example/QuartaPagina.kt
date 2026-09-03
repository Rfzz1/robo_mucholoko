package org.example

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.stage.Stage

class QuartaPagina {

    @FXML
    fun voltarParaMenu(event: ActionEvent) {
        try {
            // Verifica o identificador do idioma salvo na Sessão para escolher o menu correto
            val caminhoFxml = when (Sessao.idiomaEscolhido) {
                "en" -> "/menu/MenuIngles.fxml"
                "esp" -> "/menu/MenuEspanhol.fxml"
                else -> "/menu/Menu.fxml" // Padrão ou "pt"
            }

            // 1. Prepara o carregador com o arquivo FXML do menu correto
            val loader = FXMLLoader(javaClass.getResource(caminhoFxml))
            val novoRoot = loader.load<Parent>()
            
            // 2. Captura o palco (Stage) atual através do clique do botão
            val palcoAtual = (event.source as Node).scene.window as Stage
            
            // 3. Substitui o conteúdo da tela mantendo o robô no modo de tela cheia estável
            palcoAtual.scene.root = novoRoot
            palcoAtual.isFullScreen = true
            
            println("🔄 Retornando para o Menu correspondente ao idioma da Sessão: [${Sessao.idiomaEscolhido}]")
        } catch (e: Exception) {
            println("❌ Erro ao tentar retornar para o Menu correspondente: ${e.message}")
            e.printStackTrace()
        }
    }
}