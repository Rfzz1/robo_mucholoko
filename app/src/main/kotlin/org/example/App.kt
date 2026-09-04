package org.example

import java.io.File
import java.io.StringWriter
import java.io.PrintWriter
import org.example.model.Sessao
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Screen
import javafx.stage.Stage
import javafx.stage.StageStyle

class App : Application() {

    override fun start(palcoPrincipal: Stage) {

        val monitores = Screen.getScreens()
        
        // O Monitor 1 (Principal) do sistema
        val telaMonitor1: Screen = Screen.getPrimary() 
        
        // O Monitor 2 (Secundário). Se só tiver 1 monitor, ele usa o mesmo para evitar erros.
        val telaMonitor2: Screen = if (monitores.size > 1) {
            monitores.first { it != Screen.getPrimary() }
        } else {
            Screen.getPrimary()
        }

        // ==========================================
        // ESCOLHA AQUI QUEM VAI PARA QUAL MONITOR
        // ==========================================
        val telaBarriga: Screen = telaMonitor1 // Barriga no Monitor 1
        val telaRosto: Screen   = telaMonitor2 // Rosto no Monitor 2

        // ==========================================
        // TELA CENTRAL - BARRIGA COM O HTML (1920x1080)
        // ==========================================

        val loaderCentral = FXMLLoader(javaClass.getResource("/menu/Barriga.fxml"))
        val rootCentral = loaderCentral.load<Parent>()

        palcoPrincipal.scene = Scene(rootCentral)
        palcoPrincipal.initStyle(StageStyle.UNDECORATED)

        // Posiciona a barriga fisicamente no monitor correto ANTES de dar tela cheia
        val limitesBarriga = telaBarriga.visualBounds
        palcoPrincipal.x = limitesBarriga.minX
        palcoPrincipal.y = limitesBarriga.minY
        palcoPrincipal.width = limitesBarriga.width
        palcoPrincipal.height = limitesBarriga.height

        palcoPrincipal.show()
        palcoPrincipal.isFullScreen = true
        palcoPrincipal.fullScreenExitHint = ""

        Sessao.palcoBarriga = palcoPrincipal

        // ==========================================
        // TELA EXTERNA - ROSTO DO ROBÔ (1366x768)
        // ==========================================

        val palcoExterno = Stage()
        val loaderExterno = FXMLLoader(javaClass.getResource("/rosto/Rosto_robo_GRANDE.fxml"))
        val rootExterno = loaderExterno.load<Parent>()

        palcoExterno.scene = Scene(rootExterno)
        palcoExterno.initStyle(StageStyle.UNDECORATED)

        // Posiciona o rosto fisicamente no monitor correto ANTES de dar tela cheia
        val limitesRosto = telaRosto.visualBounds
        palcoExterno.x = limitesRosto.minX
        palcoExterno.y = limitesRosto.minY
        palcoExterno.width = limitesRosto.width
        palcoExterno.height = limitesRosto.height

        palcoExterno.show()

        // Garante que a tela cheia só aplique se houver mais de um monitor,
        // ou se estiver forçando as duas no mesmo para testes.
        palcoExterno.isFullScreen = true
        palcoExterno.fullScreenExitHint = ""

        // Deixa a barriga com o foco para os toques funcionarem imediatamente
        palcoPrincipal.requestFocus()
    }
}

fun main() {
    try {
        System.setProperty("prism.order", "d3d,sw")
        Application.launch(App::class.java)
    } catch (e: Throwable) {
        try {
            val desktop = File(System.getProperty("user.home"), "Desktop")
            val arquivoErro = File(desktop, "erro_fatal_robo.txt")

            val sw = StringWriter()
            e.printStackTrace(PrintWriter(sw))
            arquivoErro.writeText(sw.toString())
        } catch (ex: Exception) {
            // Se nem salvar o arquivo der, ignora para não travar mais ainda
        }
        e.printStackTrace()
    }
}