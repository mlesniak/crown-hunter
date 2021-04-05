/**
 * A (rectangular) Sprite consists of an image and a physical body which is simulated
 * inside a GameWorld.
 **/
// open class Sprite : Entity {
//     private var sprite: Texture
//
//     private var position: Position
//     private var size: Size
//
//     // Note that x and y positions are at the center of the object and NOT in a corner.
//     // private var dimension: Rectangle
//     internal var body: Body
//     private var bodyDef: BodyDef
//
//     constructor(type: Type, texture: String, position: Position, size: Size) {
//         this.position = position
//         this.size = size
//
//         sprite = Texture(Gdx.files.internal(texture))
//
//         bodyDef = BodyDef()
//         bodyDef.type = type.value
//         bodyDef.position.set(position.x / GameWorld.PIXEL_TO_METER, position.y / GameWorld.PIXEL_TO_METER)
//
//         // TODO(mlesniak) This is bad. Do we really have a global world object?
//         body = GameWorld.world.createBody(bodyDef)
//
//         val shape = PolygonShape()
//         shape.setAsBox(
//             size.width / GameWorld.PIXEL_TO_METER / 2,
//             size.height / GameWorld.PIXEL_TO_METER / 2
//         )
//
//         // TODO(mlesniak) how to allow to set partial values here?
//         val bodyFixture = FixtureDef()
//         bodyFixture.shape = shape
//         bodyFixture.density = 1f
//         bodyFixture.restitution = 0f
//         body.createFixture(bodyFixture)
//         shape.dispose()
//     }
//
//     fun getBodyDefinition(): BodyDef {
//         return bodyDef
//     }
//
//     /**
//      * Allows to change the position and react to external events.
//      *
//      * Usually extended while instancing an object.
//      **/
//     override fun update() {
//         // TODO(mlesniak) Conversion functions using PIXEL_TO_METER
//         position.x = body.position.x * GameWorld.PIXEL_TO_METER
//         position.y = body.position.y * GameWorld.PIXEL_TO_METER
//     }
//
//     override fun render(batch: SpriteBatch) {
//         batch.draw(
//             sprite,
//             position.x - size.width / 2,
//             position.y - size.height / 2,
//             size.width,
//             size.height
//         )
//     }
// }