import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.utils.ScreenUtils

// import com.badlogic.gdx.physics.box2d.*;

class Game : ApplicationListener {
    private lateinit var camera: OrthographicCamera
    private lateinit var batch: SpriteBatch
    private lateinit var entites: List<Entity>

    private lateinit var world: World
    private lateinit var body: Body
    private lateinit var player: Player


    override fun create() {
        camera = OrthographicCamera()
        camera.setToOrtho(false, 800f, 600f)
        batch = SpriteBatch()

        player = Player()
        entites = mutableListOf(
            player
        )

        world = World(Vector2(0f, -98f), true)
        val bodyDef = BodyDef()
        bodyDef.type = BodyDef.BodyType.DynamicBody
        bodyDef.position.set(player.dimension.x, player.dimension.y)
        body = world.createBody(bodyDef)

        val shape = PolygonShape()
        shape.setAsBox(player.dimension.getWidth() / 2, player.dimension.getHeight() / 2)
        val fixtureDef = FixtureDef()
        fixtureDef.shape = shape
        fixtureDef.density = 1f
        val fixture = body.createFixture(fixtureDef)
        shape.dispose()
    }

    override fun render() {
        world.step(Gdx.graphics.deltaTime, 6, 2);
        player.dimension.setPosition(body.position.x, body.position.y);

        ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1f)
        camera.update()
        batch.projectionMatrix = camera.combined
        batch.begin()
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
        batch.dispose()
    }
}
