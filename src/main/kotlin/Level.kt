import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input

/**
 * Contains everything which is in a level.
 **/
class Level {
    init {
        GameWorld.addEntity(object : Entity(
            Type.Dynamic,
            "assets/sprite.png",
            Position(400f, 100f),
            Size(32f, 32f)
        ) {
            override fun update() {
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
        })

        GameWorld.addEntity(Entity(
            Entity.Type.Static,
            "assets/sprite.png",
            Position(400f, 100f),
            Size(8000f, 2f)
        ))
    }
}