import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.PolygonShape

/**
 * A (rectangular) Sprite consists of an image and a physical body which is simulated.
 **/
open class Sprite {
    enum class Type(val value: BodyDef.BodyType) {
        Static(BodyDef.BodyType.StaticBody),
        Dynamic(BodyDef.BodyType.DynamicBody),
    }

    var sprite: Texture
    // Note that x and y positions are at the center of the object and NOT in a corner.
    var dimension: Rectangle
    var body: Body

    constructor(type: Type, texture: String, position: Position) {
        sprite = Texture(Gdx.files.internal(texture))

        dimension = Rectangle()
        dimension.x = position.x
        dimension.y = position.y
        dimension.width = sprite.width.toFloat()
        dimension.height = sprite.height.toFloat()

        val bodyDef = BodyDef()
        bodyDef.type = type.value
        bodyDef.position.set(dimension.x / GameWorld.PIXEL_TO_METER, dimension.y / GameWorld.PIXEL_TO_METER)
        // TODO(mlesniak) This is bad. Do we really have a global world object?
        body = GameWorld.world.createBody(bodyDef)

        val shape = PolygonShape()
        shape.setAsBox(
            dimension.getWidth() / GameWorld.PIXEL_TO_METER / 2,
            dimension.getHeight() / GameWorld.PIXEL_TO_METER / 2
        )

        // TODO(mlesniak) how to allow to set partial values here?
        val bodyFixture = FixtureDef()
        bodyFixture.shape = shape
        bodyFixture.density = 1f
        bodyFixture.restitution = 0f
        body.createFixture(bodyFixture)
        shape.dispose()
    }

    /**
     * Allows to change the position and react to external events.
     *
     * Usually extended while instancing an object.
     **/
    open fun update() {

    }
}