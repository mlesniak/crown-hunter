import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.EdgeShape
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.utils.ScreenUtils

object GameWorld {
    // Instead of having 1 pixel equals to 1meter we have 100pixel equals to 1meter.
    // When converting to Box2d, divide, when converting from, multiply.
    val PIXEL_TO_METER = 100f
    val world: World = World(Vector2(0f, -98f), true)
}

class Game : ApplicationListener {
    private lateinit var camera: OrthographicCamera
    private lateinit var batch: SpriteBatch
    private lateinit var entites: List<Entity>

    var debugRenderer: Box2DDebugRenderer? = null
    var debugMatrix: Matrix4? = null

    override fun create() {
        camera = OrthographicCamera()
        camera.setToOrtho(false, 800f, 600f)
        batch = SpriteBatch()

        entites = mutableListOf(
            object: Sprite("assets/sprite.png", Vector2(400f, 100f), BodyDef.BodyType.DynamicBody) {
                override fun update() {
                    dimension.setPosition(body.position.x * GameWorld.PIXEL_TO_METER, body.position.y * GameWorld.PIXEL_TO_METER)
                    val velocity = body.linearVelocity
                    var pressed = false
                    if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                        val y = body.position.y * GameWorld.PIXEL_TO_METER
                        if (y < 19) {
                            velocity.y += 20f
                            pressed = true
                        }
                    }
                    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                        velocity.x += 1f
                        pressed = true
                    }
                    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                        velocity.x -= 1f
                        pressed = true
                    }
                    if (pressed) {
                        body.linearVelocity = velocity
                    }

                    if (body.position.x * GameWorld.PIXEL_TO_METER < 0) {
                        body.setTransform((Gdx.graphics.width.toFloat() / GameWorld.PIXEL_TO_METER), body.position.y, 0f)
                    }
                    if (body.position.x * GameWorld.PIXEL_TO_METER > Gdx.graphics.width) {
                        body.setTransform(0f, body.position.y, 0f)
                    }
                }
            },

            Sprite("assets/sprite2.png", Vector2(200f, 100f), BodyDef.BodyType.StaticBody)
        )

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
        GameWorld.world.step(Gdx.graphics.deltaTime, 6, 2)
        entites.forEach { it.update() }

        ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1f)
        camera.update()
        batch.projectionMatrix = camera.combined
        batch.begin()
        // debugRenderer!!.render(world, debugMatrix);
        entites.forEach { it.render(batch) }
        batch.end()

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit()
        }
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
