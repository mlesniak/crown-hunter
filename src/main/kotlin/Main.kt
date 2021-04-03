import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration

fun main() {
    val config = LwjglApplicationConfiguration()
    config.title = "The hunt for THE CROWN..."
    config.width = 800
    config.height = 600
    LwjglApplication(Game(), config)
 }