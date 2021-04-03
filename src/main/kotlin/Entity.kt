import com.badlogic.gdx.graphics.g2d.SpriteBatch

interface Entity {
    fun update()
    fun render(batch: SpriteBatch)
}