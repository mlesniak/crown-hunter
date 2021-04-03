import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle

class Player : Entity {
    var sprite: Texture
    var dimension: Rectangle

    init {
        sprite = Texture(Gdx.files.internal("assets/sprite.png"))
        dimension = Rectangle()
        dimension.x = 800f / 2 - 64 / 2
        dimension.y = 100f
        dimension.width = 32f
        dimension.height = 32f
    }

    override fun render(batch: SpriteBatch) {
        batch.draw(sprite, dimension.x, dimension.y);
    }

    override fun update() {
        // if (Gdx.input.isTouched) {
        //     val touchPos = Vector3()
        //     touchPos.set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)
        //     camera.unproject(touchPos)
        //     bucket.x = touchPos.x - 64 / 2
        // }
    }

    fun dispose() {
        // When do we call this?
        sprite.dispose()
    }
}