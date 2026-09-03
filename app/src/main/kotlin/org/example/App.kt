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

//dsasdsa

class App : Application() {

    override fun start(palcoPrincipal: Stage) {

        val monitores = Screen.getScreens()

        // ==========================================
        // TELA CENTRAL - BARRIGA COM O HTML
        // ==========================================

        val loaderCentral =
            FXMLLoader(javaClass.getResource("/menu/Barriga.fxml"))

        val rootCentral = loaderCentral.load<Parent>()

        palcoPrincipal.scene = Scene(rootCentral)

        // MODO TOTEM
        palcoPrincipal.initStyle(StageStyle.UNDECORATED)

        // Pega o monitor principal
        val boundsPrincipal = monitores[0].bounds

        palcoPrincipal.x = boundsPrincipal.minX
        palcoPrincipal.y = boundsPrincipal.minY
        palcoPrincipal.width = boundsPrincipal.width
        palcoPrincipal.height = boundsPrincipal.height

        // TELA CHEIA
        palcoPrincipal.isFullScreen = true
        palcoPrincipal.fullScreenExitHint = ""

        Sessao.palcoBarriga = palcoPrincipal

        palcoPrincipal.show()


        // ==========================================
        // TELA EXTERNA - ROSTO GRANDE DO ROBÔ
        // ==========================================

        val palcoExterno = Stage()

        // AGORA ABRE O ROSTO GRANDE
        val loaderExterno =
            FXMLLoader(javaClass.getResource("/rosto/Rosto_robo_GRANDE.fxml"))

        val rootExterno = loaderExterno.load<Parent>()

        palcoExterno.scene = Scene(rootExterno)

        // MODO TOTEM
        palcoExterno.initStyle(StageStyle.UNDECORATED)

        if (monitores.size > 1) {

            val boundsExterno = monitores[1].bounds

            palcoExterno.x = boundsExterno.minX
            palcoExterno.y = boundsExterno.minY
            palcoExterno.width = boundsExterno.width
            palcoExterno.height = boundsExterno.height

            // TELA CHEIA
            palcoExterno.isFullScreen = true
            palcoExterno.fullScreenExitHint = ""
        }

        palcoExterno.show()

        // Deixa a barriga com o foco
        palcoPrincipal.requestFocus()
    }
}

fun main() {
    try {
        System.setProperty("prism.order", "d3d,sw")
        Application.launch(App::class.java)
    } catch (e: Throwable) {
        // Se qualquer coisa der erro e fechar o programa, isso vai salvar o motivo exato!
        try {
            val desktop = File(System.getProperty("user.home"), "Desktop")
            val arquivoErro = File(desktop, "erro_fatal_robo.txt")

            val sw = StringWriter()
            e.printStackTrace(PrintWriter(sw))
            arquivoErro.writeText(sw.toString())
        } catch (ex: Exception) {
            // Se nem salvar o arquivo der, ignora para não travar mais ainda
        }

        // Joga o erro original para cima também
        e.printStackTrace()
    }
}