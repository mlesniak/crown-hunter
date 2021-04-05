package entity

import Config
import GameWorld
import Position
import Size
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.World

class Player : Entity {
    private var sprite: Texture

    private val position: Position
    private val size: Size
    private val body: Body

    constructor(world: World) {
        position = Position(Config.width.toFloat() / 2, 100f)
        size = Size(80f, 80f)

        // Define the body position and size.
        val bodyDef = BodyDef()
        bodyDef.type = BodyDef.BodyType.DynamicBody
        bodyDef.position.set(position.x / GameWorld.PIXEL_TO_METER, position.y / GameWorld.PIXEL_TO_METER)
        body = world.createBody(bodyDef)
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
        bodyFixture.friction = 0.8f
        body.createFixture(bodyFixture)
        shape.dispose()

        sprite = Texture(Gdx.files.internal("assets/player.png"))
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

    override fun update() {
        position.x = body.position.x * GameWorld.PIXEL_TO_METER
        position.y = body.position.y * GameWorld.PIXEL_TO_METER

        handleInput()
    }

    private fun handleInput() {
        val velocity = body.linearVelocity
        var pressed = false
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            val y = body.position.y * GameWorld.PIXEL_TO_METER
            if (velocity.y < 0.001) {
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

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit()
        }
    }
}