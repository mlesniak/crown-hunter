import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World

/**
 * A GameWorld holds all objects which a part of the world.
 **/
object GameWorld {
    // Instead of having 1 pixel equals to 1mwe have 100pixel equals to 1m.
    // When converting to Box2d, divide, when converting from, multiply.
    const val PIXEL_TO_METER = 100f

    val world: World = World(Vector2(0f, -98f), true)
    private var entities: MutableList<Entity> = mutableListOf()

    fun addEntity(entity: Entity) {
        world.createBody(entity.getBodyDefinition())
        entities.add(entity)
    }

    fun render(batch: SpriteBatch) {
        entities.forEach { entity ->
            entity.render(batch)
        }
    }

    fun update() {
        world.step(Gdx.graphics.deltaTime, 6, 2)
        entities.forEach { entity ->
            entity.update()
        }
    }
}