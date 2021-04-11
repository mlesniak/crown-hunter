
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import entity.Dog
import entity.Floor
import entity.Platform
import entity.Player
import entity.Player2

fun main() {
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
        Platform(world, Position(150f, 100f))
        Dog(world)
        Player(world)
        Player2(world)
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
