import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.EdgeShape
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.utils.ScreenUtils

class Game : ApplicationListener {
    private lateinit var camera: OrthographicCamera
    private lateinit var batch: SpriteBatch

    var debugRenderer: Box2DDebugRenderer? = null
    var debugMatrix: Matrix4? = null

    override fun create() {
        camera = OrthographicCamera()
        camera.setToOrtho(false, 800f, 600f) // TODO(mlesniak) config object with screen width and height
        batch = SpriteBatch()

        // Add floor
        val floor = BodyDef()
        floor.type = BodyDef.BodyType.StaticBody
        floor.position.set(400f / GameWorld.PIXEL_TO_METER, 0f / GameWorld.PIXEL_TO_METER)
        val floorBody = GameWorld.world.createBody(floor)
        val floorShape = EdgeShape()
        floorShape.set(-10000f, 0f, 10000f, 0f)
        val floorFixture = FixtureDef()
        floorFixture.shape = floorShape
        floorBody.createFixture(floorFixture)
        floorShape.dispose()

        debugMatrix = Matrix4(camera.combined)
        debugMatrix!!.scale(GameWorld.PIXEL_TO_METER, GameWorld.PIXEL_TO_METER, 1f)
        debugRenderer = Box2DDebugRenderer()
    }

    override fun render() {
        // TODO(mlesniak) Basic holder object for everything is a GameWorld.
        GameWorld.update()

        ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1f)
        camera.update()
        batch.projectionMatrix = camera.combined
        batch.begin()
        // debugRenderer!!.render(world, debugMatrix);
        GameWorld.render(batch)
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
