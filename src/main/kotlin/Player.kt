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

        dimension.x = 800f / 2
        dimension.y = 100f
        dimension.width = 32f
        dimension.height = 32f
    }

    override fun render(batch: SpriteBatch) {
        batch.draw(sprite, dimension.x, dimension.y);
    }

    override fun update() {
    }

    fun dispose() {
        sprite.dispose()
    }
}