import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.PolygonShape

/**
 * A (rectangular) Sprite consists of an image and a physical body which is simulated.
 **/
open class Sprite : Entity {
    private var sprite: Texture

    var dimension: Rectangle
    var body: Body

    constructor(texture: String, position: Vector2, type: BodyDef.BodyType) {
        sprite = Texture(Gdx.files.internal(texture))

        dimension = Rectangle()
        dimension.x = position.x
        dimension.y = position.y
        dimension.width = sprite.width.toFloat()
        dimension.height = sprite.height.toFloat()

        val bodyDef = BodyDef()
        bodyDef.type = type
        bodyDef.position.set(dimension.x / GameWorld.PIXEL_TO_METER, dimension.y / GameWorld.PIXEL_TO_METER)
        body = GameWorld.world.createBody(bodyDef)

        val shape = PolygonShape()
        shape.setAsBox(
            dimension.getWidth()/ GameWorld.PIXEL_TO_METER / 2 ,
            dimension.getHeight()/ GameWorld.PIXEL_TO_METER / 2
        )
        val bodyFixture = FixtureDef()
        bodyFixture.shape = shape
        bodyFixture.density = 1f
        bodyFixture.restitution = 0f
        body.createFixture(bodyFixture)
        shape.dispose()
    }

    override fun render(batch: SpriteBatch) {
        batch.draw(sprite, dimension.x - dimension.width/2, dimension.y - dimension.height/2, dimension.width, dimension.height)
    }

    override fun update() {

    }
}