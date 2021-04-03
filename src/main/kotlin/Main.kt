import GameDemo.GameDemo
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration

fun main() {
    val config = LwjglApplicationConfiguration()
    config.title = "Drop"
    config.width = 800
    config.height = 600
    LwjglApplication(GameDemo(), config)
 }