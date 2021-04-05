
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import entity.Floor
import entity.Platform
import entity.Player

fun main() {
    with(Config) {
        title = "The hunt for THE CROWN..."
        width = 800
        height = 600
    }

    LwjglApplication(Game(), Config)
}

class Game : ApplicationAdapter() {
    private lateinit var camera: OrthographicCamera
    private lateinit var batch: SpriteBatch

    override fun create() {
        camera = OrthographicCamera()
        camera.setToOrtho(false, Config.width.toFloat(), Config.height.toFloat())
        batch = SpriteBatch()

        // Setup world and add all objects.
        val world = GameWorld.world
        Floor(world)
        Platform(world, Position(150f, 150f))
        Platform(world, Position(250f, 250f))
        Platform(world, Position(650f, 250f))
        Player(world)
    }

    override fun render() {
        GameWorld.update()

        ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1f)
        camera.update()
        batch.projectionMatrix = camera.combined
        batch.begin()
        GameWorld.render(batch)
        batch.end()
    }
}
