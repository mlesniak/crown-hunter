import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.utils.Array

/**
 * A GameWorld holds all objects which a part of the world.
 **/
object GameWorld {
    // Instead of having 1 pixel equals to 1mwe have 100pixel equals to 1m.
    // When converting to Box2d, divide, when converting from, multiply.
    const val PIXEL_TO_METER = 100f

    val world: World = World(Vector2(0f, -98f), true)

    private fun iterateOverBodies(func: (Entity) -> Unit) {
        var bodies = Array<Body>()
        world.getBodies(bodies)
        bodies.asIterable().forEach {
            val entity = it.userData
            if (entity is Entity) {
                func(entity)
            }
        }
    }

    fun render(batch: SpriteBatch) {
        iterateOverBodies { it.render(batch) }
    }

    fun update() {
        world.step(Gdx.graphics.deltaTime, 6, 2)
        iterateOverBodies { it.update() }
    }
}