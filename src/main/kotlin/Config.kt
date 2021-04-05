import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration

object Config : LwjglApplicationConfiguration() {
    init {
        // Development.
        width = 800
        height = 504
        title = "The hunt for THE CROWN..."
        fullscreen = false

        // Production.
        // width = 1650
        // height = 1050
        // title = "The hunt for THE CROWN..."
        // fullscreen = true
    }
}