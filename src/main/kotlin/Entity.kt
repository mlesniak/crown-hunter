import com.badlogic.gdx.graphics.g2d.SpriteBatch

open class Entity {
    open fun update() {}
    open fun render(batch: SpriteBatch) {}
}