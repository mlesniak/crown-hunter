package entity

import Config
import GameWorld
import Position
import Size
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.World

class Dog : Entity {
    private var font: BitmapFont
    private val imageWidth =256
    private val imageHeight = 256

    private var tick = 0
    private var tickDirection = 1
    private val maxTick: Int
    private var tickCounter: Int = 0
    private val sprites: Array<TextureRegion>

    private val position: Position
    private val size: Size
    private val body: Body

    constructor(world: World) {
        position = Position(Config.width.toFloat() - 80f, 30f)
        size = Size(40f, 40f)

        // Define the body position and size.
        val bodyDef = BodyDef()
        bodyDef.type = BodyDef.BodyType.StaticBody
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
        bodyFixture.friction = 0.9f
        body.createFixture(bodyFixture)
        shape.dispose()

        // Load animation phases
        val spriteSheet = Texture(Gdx.files.internal("assets/suki.png"))
        maxTick = spriteSheet.width / imageWidth
        sprites = Array(maxTick) { index ->
            TextureRegion(spriteSheet, index * imageWidth, 0, imageWidth, imageHeight)
        }

        font = BitmapFont()
        font.color = Color.WHITE
    }

    override fun render(batch: SpriteBatch) {
        batch.draw(
            sprites[tick],
            position.x - size.width / 2,
            position.y - size.height / 2,
            size.width,
            size.height
        )

        // Add debug information.
        // val ys = "%3.2f".format(body.linearVelocity.y)
        // font.draw(batch, "${ys}", 10f, Config.height - 20f)
    }

    override fun update() {
        position.x = body.position.x * GameWorld.PIXEL_TO_METER
        position.y = body.position.y * GameWorld.PIXEL_TO_METER

        handleInput()
        handleControl()

        // Animation support.
        tickCounter++
        if (tickCounter % 5 == 0) {
            tick += tickDirection
            if (tick == maxTick) {
                tick--
                tickDirection = -1
            }
            if (tick == -1) {
                tick = 0
                tickDirection = 1
            }
        }
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
    }

    private fun handleControl() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit()
        }
    }
}