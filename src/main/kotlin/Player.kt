import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.World

class Player : Entity {
    var sprite: Texture
    var dimension: Rectangle

    private lateinit var body: Body

    constructor(world: World) {
        sprite = Texture(Gdx.files.internal("assets/sprite.png"))

        dimension = Rectangle()

        dimension.x = 800f / 2
        dimension.y = 100f
        dimension.width = 32f
        dimension.height = 32f

        val bodyDef = BodyDef()
        bodyDef.type = BodyDef.BodyType.DynamicBody
        bodyDef.position.set(dimension.x / Game.PIXEL_TO_METER, dimension.y / Game.PIXEL_TO_METER)
        body = world.createBody(bodyDef)

        val shape = PolygonShape()
        shape.setAsBox(
            dimension.getWidth()/ Game.PIXEL_TO_METER / 2 ,
            dimension.getHeight()/ Game.PIXEL_TO_METER / 2
        )
        val bodyFixture = FixtureDef()
        bodyFixture.shape = shape
        bodyFixture.density = 1f
        bodyFixture.restitution = 0f
        bodyFixture.friction = 1f
        body.createFixture(bodyFixture)
        shape.dispose()
    }

    override fun render(batch: SpriteBatch) {
        batch.draw(sprite, dimension.x - dimension.width/2, dimension.y - dimension.height/2)
    }

    override fun update() {
        dimension.setPosition(body.position.x * Game.PIXEL_TO_METER, body.position.y * Game.PIXEL_TO_METER)

        val veloc = body.linearVelocity
        var pressed = false
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            val y = body.position.y * Game.PIXEL_TO_METER
            if (y < 19) {
                veloc.y += 20f
                pressed = true
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            veloc.x += 1f
            pressed = true
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            veloc.x -= 1f
            pressed = true
        }
        if (pressed) {
            body.linearVelocity = veloc
        }

        if (body.position.x * Game.PIXEL_TO_METER < 0) {
            body.setTransform((Gdx.graphics.width.toFloat() / Game.PIXEL_TO_METER), body.position.y, 0f)
        }
        if (body.position.x * Game.PIXEL_TO_METER > Gdx.graphics.width) {
            body.setTransform(0f, body.position.y, 0f)
        }
    }

    fun dispose() {
        sprite.dispose()
    }
}