import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration

fun main() {
    val config = LwjglApplicationConfiguration()
    config.title = "The hunt for THE CROWN..."
    config.width = Config.width
    config.height = Config.height
    LwjglApplication(Game(), config)
 }