package org.example

import javafx.application.Platform
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.web.WebView
import java.net.URL
import java.util.ResourceBundle

class Barriga : Initializable {

    @FXML
    lateinit var navegadorSite: WebView 

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        println("🚀 A FUNÇÃO INITIALIZE DA BARRIGA COMEÇOU A RODAR!")
        
        // 1. Dizemos para a Sessão global quem é a barriga ativa do momento
        Sessao.controllerBarriga = this
        
        // 2. Carregamos a página inicial usando a nova função abaixo
        carregarPaginaHtml("/telarobo/index.html")
    }

    // ================================================================
    // Função para receber o caminho do HTML e atualizar a tela
    // ================================================================
    fun carregarPaginaHtml(caminhoInterno: String) {
        println("⚙️ BARRIGA: Recebi o pedido para carregar -> $caminhoInterno")
        try {
            val urlLocal = javaClass.getResource(caminhoInterno)
            
            if (urlLocal != null) {
                val urlExterna = urlLocal.toExternalForm()
                println("🌐 BARRIGA: Arquivo encontrado com sucesso! Renderizando: $urlExterna")
                
                // MÁGICA DE TELA: Força a interface gráfica do JavaFX a atualizar sem travar
                Platform.runLater {
                    navegadorSite.engine.load(urlExterna)
                }
            } else {
                println("❌ ERRO BARRIGA: O arquivo não foi encontrado na pasta resources!")
                println("O Java procurou exatamente por: $caminhoInterno")
                println("DICA: Verifique se o arquivo termina em .php ou .html e se o nome está idêntico.")
            }
        } catch (e: Exception) {
            println("❌ Erro fatal ao tentar carregar o HTML: ${e.message}")
            e.printStackTrace()
        }
    }
}