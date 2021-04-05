import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World

/**
 * A GameWorld holds all objects which a part of the world.
 **/
object GameWorld {
    // Instead of having 1 pixel equals to 1meter we have 100pixel equals to 1meter.
    // When converting to Box2d, divide, when converting from, multiply.
    val PIXEL_TO_METER = 100f
    val world: World = World(Vector2(0f, -98f), true)

    private var entites: MutableList<Sprite> = mutableListOf()

    init {
        // entites = mutableListOf(
        //     object : Sprite(Type.Dynamic, "assets/sprite.png", Position(400f, 100f)) {
        //         override fun update() {
        //             super.update()
        //             val velocity = body.linearVelocity
        //             var pressed = false
        //             if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
        //                 val y = body.position.y * GameWorld.PIXEL_TO_METER
        //                 if (velocity.y < 0.001) {
        //                     velocity.y += 20f
        //                     pressed = true
        //                 }
        //             }
        //             if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
        //                 velocity.x += 1f
        //                 pressed = true
        //             }
        //             if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
        //                 velocity.x -= 1f
        //                 pressed = true
        //             }
        //             if (pressed) {
        //                 body.linearVelocity = velocity
        //             }
        //
        //             if (body.position.x * GameWorld.PIXEL_TO_METER < 0) {
        //                 body.setTransform((Gdx.graphics.width.toFloat() / GameWorld.PIXEL_TO_METER), body.position.y, 0f)
        //             }
        //             if (body.position.x * GameWorld.PIXEL_TO_METER > Gdx.graphics.width) {
        //                 body.setTransform(0f, body.position.y, 0f)
        //             }
        //
        //              if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
        //                  Gdx.app.exit()
        //              }
        //         }
        //     },
        //
        //     Sprite(Sprite.Type.Static, "assets/sprite2.png", Vector2(200f, 100f)),
        //     Sprite(Sprite.Type.Static, "assets/sprite2.png", Vector2(400f, 200f)),
        //     Sprite(Sprite.Type.Static, "assets/sprite2.png", Vector2(600f, 300f)),
        // )
    }

    fun addEntity(entity: Sprite) {
        entites.add(entity)
    }

    fun render(batch: SpriteBatch) {
        entites.forEach { entity ->
            batch.draw(
                entity.sprite,
                entity.dimension.x - entity.dimension.width / 2,
                entity.dimension.y - entity.dimension.height / 2,
                entity.dimension.width,
                entity.dimension.height)
        }
    }

    fun update() {
        world.step(Gdx.graphics.deltaTime, 6, 2)
        entites.forEach { entity ->
            entity.dimension.setPosition(entity.body.position.x * PIXEL_TO_METER, entity.body.position.y * PIXEL_TO_METER)
            entity.update()
        }
    }
}