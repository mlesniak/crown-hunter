package GameDemo

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.math.Vector3

class GameDemo : ApplicationListener {
    private lateinit var sprite: Texture

    private lateinit var camera: OrthographicCamera
    private lateinit var batch: SpriteBatch

    private lateinit var bucket: Rectangle

    override fun create() {
        sprite = Texture(Gdx.files.internal("assets/sprite.png"))

        camera = OrthographicCamera()
        camera.setToOrtho(false, 800f, 600f)

        batch = SpriteBatch()

        bucket = Rectangle()
        bucket.x = 800f / 2 - 64 / 2
        bucket.y = 20f
        bucket.width = 32f
        bucket.height = 32f
    }

    override fun resize(p0: Int, p1: Int) {
    }

    override fun render() {
        ScreenUtils.clear(0f, 0f, 0.2f, 1f)
        camera.update()
        batch.projectionMatrix = camera.combined;
        batch.begin();
        batch.draw(sprite, bucket.x, bucket.y);
        batch.end();

        if (Gdx.input.isTouched) {
            val touchPos = Vector3()
            touchPos.set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)
            camera.unproject(touchPos)
            bucket.x = touchPos.x - 64 / 2
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit()
        }
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun dispose() {
        sprite.dispose()
        batch.dispose()
    }
}
