import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.utils.ScreenUtils

class Game : ApplicationListener {
    private lateinit var camera: OrthographicCamera
    private lateinit var batch: SpriteBatch

    // Allows to show Box2D models without textures.
    private var debugEnabled = false
    private var debugRenderer: Box2DDebugRenderer? = null
    private var debugMatrix: Matrix4? = null

    override fun create() {
        camera = OrthographicCamera()
        camera.setToOrtho(false, Config.width.toFloat(), Config.height.toFloat())
        batch = SpriteBatch()

        debugMatrix = Matrix4(camera.combined)
        debugMatrix!!.scale(GameWorld.PIXEL_TO_METER, GameWorld.PIXEL_TO_METER, 1f)
        debugRenderer = Box2DDebugRenderer()

        // TODO(mlesniak) Initialize GameWorld
    }

    override fun render() {
        // TODO(mlesniak) Basic holder object for everything is a GameWorld.
        GameWorld.update()

        ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1f)
        camera.update()
        batch.projectionMatrix = camera.combined
        batch.begin()
        if (debugEnabled) {
            debugRenderer!!.render(GameWorld.world, debugMatrix);
        } else {
            GameWorld.render(batch)
        }
        batch.end()
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun resize(p0: Int, p1: Int) {
    }

    override fun dispose() {
    }
}
