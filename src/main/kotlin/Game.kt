
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils

class Game : ApplicationAdapter() {
    private lateinit var camera: OrthographicCamera
    private lateinit var batch: SpriteBatch

    override fun create() {
        camera = OrthographicCamera()
        camera.setToOrtho(false, Config.width.toFloat(), Config.height.toFloat())
        batch = SpriteBatch()

        val world = GameWorld.world
        Level.initialize(world)
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
