import com.badlogic.gdx.backends.lwjgl.LwjglApplication

fun main() {
    with(Config) {
        title = "The hunt for THE CROWN..."
        width = 800
        height = 600
    }

    LwjglApplication(Game(), Config)
}