package entity

import Config
import GameWorld
import Position
import Size
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.World

class Floor : Entity {
    private var sprite: Texture

    private val position: Position
    private val size: Size

    constructor(world: World) {
        position = Position(Config.width.toFloat() / 2, 10f)
        size = Size(Config.width.toFloat() * 2, 5f)

        // Define the body position and size.
        val bodyDef = BodyDef()
        bodyDef.type = BodyDef.BodyType.StaticBody
        bodyDef.position.set(position.x / GameWorld.PIXEL_TO_METER, position.y / GameWorld.PIXEL_TO_METER)
        val body = world.createBody(bodyDef)
        body.userData = this

        // Define the shape of the body, relative to the center of mass in BodyDef.
        val shape = PolygonShape()
        shape.setAsBox(
            size.width / GameWorld.PIXEL_TO_METER / 2,
            size.height / GameWorld.PIXEL_TO_METER / 2
        )

        // Define physical properties.
        val bodyFixture = FixtureDef()
        bodyFixture.shape = shape
        bodyFixture.density = 1f
        bodyFixture.restitution = 0f
        body.createFixture(bodyFixture)
        shape.dispose()

        sprite = Texture(Gdx.files.internal("assets/floor.png"))
    }

    override fun render(batch: SpriteBatch) {
        batch.draw(
            sprite,
            position.x - size.width / 2,
            position.y - size.height / 2,
            size.width,
            size.height
        )
    }
}