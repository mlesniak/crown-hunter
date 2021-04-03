import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle

class Player : Entity {
    private var sprite: Texture
    private var bucket: Rectangle

    init {
        sprite = Texture(Gdx.files.internal("assets/sprite.png"))
        bucket = Rectangle()
        bucket.x = 800f / 2 - 64 / 2
        bucket.y = 20f
        bucket.width = 32f
        bucket.height = 32f
    }

    override fun render(batch: SpriteBatch) {
        batch.draw(sprite, bucket.x, bucket.y);
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