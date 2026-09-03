package org.example

import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.stage.Stage
import java.io.File
import javafx.scene.Scene


class TerceiraPagina {

   @FXML
fun irParaLocalizarSalas(event: ActionEvent) {
       println("====================================")
       println("🟢 BOTÃO FIND ROOMS FOI CLICADO!")
       println("🟢 IDIOMA = [${Sessao.idiomaEscolhido}]")

       try {
           val caminhoFxml = when (Sessao.idiomaEscolhido) {
               "en" -> "/salas/Localizar_salas_G_ing.fxml"
               "esp" -> "/salas/Localizar_salas_G_esp.fxml"
               else -> "/salas/Localizar_salas_G_port.fxml"
           }

           println("🟢 CAMINHO ESCOLHIDO = $caminhoFxml")

           val recurso = javaClass.getResource(caminhoFxml)

           println("🟢 RECURSO = $recurso")

           if (recurso == null) {
               println("🔴 ERRO: O ARQUIVO NÃO FOI ENCONTRADO!")
               return
           }

           val loader = FXMLLoader(recurso)
           val novoRoot = loader.load<Parent>()

           println("🟢 FXML CARREGADO COM SUCESSO!")

           val palcoAtual = (event.source as Node).scene.window as Stage

           palcoAtual.scene.root = novoRoot
           palcoAtual.isFullScreen = true

           println("🟢 TELA ALTERADA COM SUCESSO!")

       } catch (e: Exception) {
           println("🔴 DEU ERRO AO ABRIR!")
           e.printStackTrace()
       }

       println("====================================")
   }


    @FXML
    fun iniciarJogo(event: ActionEvent) {
        try {
            // 1. Descobre o caminho relativo (agora que o Inno Setup vai instalar certo)
            val arquivoExe = File(System.getProperty("user.dir"), "jogo/WorkBot.exe")

            if (arquivoExe.exists()) {
                // 2. Minimiza a tela da barriga para o jogo poder assumir o monitor
                Sessao.palcoBarriga.isIconified = true

                // 3. Inicia o processo do jogo
                val processo = ProcessBuilder(arquivoExe.absolutePath)
                processo.directory(arquivoExe.parentFile)
                val procAtivo = processo.start()

                // 4. Cria uma thread em segundo plano para esperar o jogo fechar
                Thread {
                    // Isso trava essa thread invisível até o jogo ser fechado
                    procAtivo.waitFor()

                    // 5. Quando o jogo fechar, volta a exibir a barriga em tela cheia
                    Platform.runLater {
                        Sessao.palcoBarriga.isIconified = false
                        Sessao.palcoBarriga.isFullScreen = true
                        Sessao.palcoBarriga.requestFocus()
                    }
                }.start()

            } else {
                // Só exibe alerta se o jogo realmente não for encontrado após instalar
                val alertaErro = javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR)
                alertaErro.title = "Erro"
                alertaErro.headerText = "Jogo não encontrado"
                alertaErro.contentText = "Não foi possível encontrar o arquivo em:\n${arquivoExe.absolutePath}"
                alertaErro.showAndWait()
            }

        } catch (e: Exception) {
            val alertaErro = javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR)
            alertaErro.title = "Erro"
            alertaErro.headerText = "Deu erro ao tentar abrir"
            alertaErro.contentText = e.message
            alertaErro.showAndWait()
        }
    }

    @FXML
    fun abrirProgramacaoDiaria(event: ActionEvent? = null) {
        val caminho = when (Sessao.idiomaEscolhido) {
            "en" -> "/htmls/programacao_diaria/programacao_diaria_ingles.html"
            "esp" -> "/htmls/programacao_diaria/programacao_diaria_espanhol.html"
            else -> "/htmls/programacao_diaria/programacaodia.html" 
        }
        
        println("🔘 MENU: Botão Programação Diária clicado! Idioma: ${Sessao.idiomaEscolhido}")
        
        if (Sessao.controllerBarriga == null) {
            println("❌ ERRO CRÍTICO: O Menu não consegue achar a Barriga! A variável está null.")
        } else {
            Sessao.controllerBarriga?.carregarPaginaHtml(caminho)
        }
    }

    @FXML
fun abrirBemEstar(event: ActionEvent) {
    try {
        val caminho = when (Sessao.idiomaEscolhido) {
            "en" -> "/htmls/bem_estar/bem_estar_ingles.html"
            "esp" -> "/htmls/bem_estar/bem_estar_espanhol.html"
            else -> "/htmls/bem_estar/bem_estar.html"
        }

        println("====================================")
        println("🔘 BOTÃO BEM-ESTAR FOI CLICADO!")
        println("🌐 IDIOMA = [${Sessao.idiomaEscolhido}]")
        println("📂 CAMINHO = $caminho")

        val recurso = javaClass.getResource(caminho)

        if (recurso == null) {
            println("❌ HTML NÃO ENCONTRADO!")
            println("❌ Verifique se o arquivo está em:")
            println("   app/src/main/resources${caminho}")
            return
        }

        println("✅ HTML ENCONTRADO: $recurso")

        if (Sessao.controllerBarriga == null) {
            println("❌ controllerBarriga está NULL!")
            return
        }

        println("🔄 Carregando HTML na Barriga...")

        Sessao.controllerBarriga?.carregarPaginaHtml(caminho)

        println("✅ BEM-ESTAR ABERTO NA BARRIGA!")
        println("====================================")

    } catch (e: Exception) {
        println("❌ ERRO AO ABRIR BEM-ESTAR!")
        e.printStackTrace()
    }
}

    @FXML
    fun abrirCeit(event: ActionEvent) {
        val caminho = when (Sessao.idiomaEscolhido) {
            "en" -> "/htmls/robo/ceitingles.html"
            "esp" -> "/htmls/robo/ceitespanhol.html"
            else -> "/htmls/robo/ceit.html"
        }
        println("🔘 MENU: Botão CEIT clicado! Carregando: $caminho")
        Sessao.controllerBarriga?.carregarPaginaHtml(caminho)
    }

    @FXML
    fun abrirPet(event: ActionEvent) {
        val caminho = when (Sessao.idiomaEscolhido) {
            "en" -> "/htmls/robo/petingles.html"
            "esp" -> "/htmls/robo/petespanhol.html"
            else -> "/htmls/robo/pet.html"
        }
        println("🔘 MENU: Botão PET clicado! Carregando: $caminho")
        Sessao.controllerBarriga?.carregarPaginaHtml(caminho)
    }

    @FXML
    fun abrirAtf(event: ActionEvent) {
        val caminho = when (Sessao.idiomaEscolhido) {
            "en" -> "/htmls/robo/atfemingles.html"
            "esp" -> "/htmls/robo/atfemespanhol.html"
            else -> "/htmls/robo/atf.html"
        }
        println("🔘 MENU: Botão ATF clicado! Carregando: $caminho")
        Sessao.controllerBarriga?.carregarPaginaHtml(caminho)
    }

@FXML
fun abrirAcessibilidade(event: ActionEvent) {

    val caminho = when (Sessao.idiomaEscolhido) {
        "en" -> "/htmls/robo/acessibilidadeemingles.html"
        "esp" -> "/htmls/robo/acessibilidadeemespanhol.html"
        else -> "/htmls/robo/acessibilidade.html"
    }

    println("🔘 MENU: Botão ACESSIBILIDADE clicado!")
    println("🌐 Idioma: ${Sessao.idiomaEscolhido}")
    println("📂 Caminho escolhido: $caminho")

    if (Sessao.controllerBarriga != null) {
        println("✅ Controller da barriga encontrado!")
        Sessao.controllerBarriga?.carregarPaginaHtml(caminho)
    } else {
        println("❌ ERRO: controllerBarriga está NULL!")
    }
}
    @FXML
fun abrirSenai(event: ActionEvent) {
    try {
        val caminho = when (Sessao.idiomaEscolhido) {
            "en" -> "/senai/SENAI_G_ing.fxml"
            "esp" -> "/senai/SENAI_G_esp.fxml"
            else -> "/senai/SENAI_G_PORT.fxml"
        }

        println("BOTÃO SENAI FOI CLICADO!")
        println("IDIOMA = ${Sessao.idiomaEscolhido}")
        println("CAMINHO = $caminho")

        val recurso = javaClass.getResource(caminho)

        if (recurso == null) {
            println("ERRO: FXML não encontrado!")
            return
        }

        val loader = FXMLLoader(recurso)
        val root = loader.load<Parent>()

        val stage = (event.source as Node).scene.window as Stage

        // Cria a Scene com o tamanho correto
        val scene = Scene(root, 1366.0, 768.0)

        stage.scene = scene

        // Mantém a janela em tela cheia
        stage.isFullScreen = true
        stage.centerOnScreen()
        stage.show()

        println("SENAI ABERTO COM SUCESSO!")

    } catch (e: Exception) {
        println("ERRO AO ABRIR SENAI!")
        e.printStackTrace()
    }
}
@FXML
fun abrirSaida(event: ActionEvent) {
    try {

        val caminho = when (Sessao.idiomaEscolhido) {
            "pt" -> "/saidasdeemergencia/saidas_port_G.fxml"
            "en" -> "/saidasdeemergencia/saidas_ing_G.fxml"
            "esp" -> "/saidasdeemergencia/saidas_esp_G.fxml"
            else -> "/saidasdeemergencia/saidas_port_G.fxml"
        }

        println("🚨 ABRINDO SAÍDAS DE EMERGÊNCIA: $caminho")

        val arquivo = javaClass.getResource(caminho)

        if (arquivo == null) {
            println("❌ FXML NÃO ENCONTRADO: $caminho")
            return
        }

        val loader = FXMLLoader(arquivo)
        val novoRoot: Parent = loader.load()

        val stage = (event.source as Node).scene.window as Stage

        stage.scene.root = novoRoot
        stage.isFullScreen = true
        stage.show()

        println("✅ SAÍDAS DE EMERGÊNCIA ABERTA!")

    } catch (e: Exception) {
        println("❌ ERRO AO ABRIR SAÍDAS DE EMERGÊNCIA:")
        e.printStackTrace()
    }
}
@FXML
fun abrirEduca(event: ActionEvent) {
    try {
        val caminho = when (Sessao.idiomaEscolhido) {
            "en" -> "/htmls/educa/educaing/index.html"
            "esp" -> "/htmls/educa/educaesp/index.html"
            else -> "/htmls/educa/educaport/index.html"
        }

        println("====================================")
        println("🟢 BOTÃO EDUCA+ FOI CLICADO!")
        println("🌐 IDIOMA = [${Sessao.idiomaEscolhido}]")
        println("📂 CAMINHO = $caminho")

        val recurso = javaClass.getResource(caminho)

        if (recurso == null) {
            println("❌ EDUCA+: HTML NÃO ENCONTRADO!")
            println("Procurado em: $caminho")
            return
        }

        println("✅ HTML ENCONTRADO: $recurso")
        println("🌐 Carregando HTML na Barriga...")

        Sessao.controllerBarriga?.carregarPaginaHtml(caminho)

        println("✅ EDUCA+ ABERTO NA BARRIGA!")
        println("====================================")

    } catch (e: Exception) {
        println("❌ ERRO AO ABRIR EDUCA+: ${e.message}")
        e.printStackTrace()
    }
}

@FXML
fun voltarParaRosto(event: ActionEvent) {
    try {
        println("🔙 FINALIZAR ATENDIMENTO: Voltando para o rosto...")

        val recurso = javaClass.getResource("/rosto/Rosto_robo_GRANDE.fxml")

        if (recurso == null) {
            println("❌ RostoIniciar.fxml não encontrado!")
            return
        }

        val loader = FXMLLoader(recurso)
        val novoRoot = loader.load<Parent>()

        val palcoAtual = (event.source as Node).scene.window as Stage

        palcoAtual.scene.root = novoRoot
        palcoAtual.isFullScreen = true
        palcoAtual.show()

        // Volta a Barriga para o HTML inicial
        Sessao.controllerBarriga?.carregarPaginaHtml("/telarobo/index.html")

        println("✅ Voltou para o rosto!")

    } catch (e: Exception) {
        println("❌ Erro ao voltar para o rosto:")
        e.printStackTrace()
    }
}

}
