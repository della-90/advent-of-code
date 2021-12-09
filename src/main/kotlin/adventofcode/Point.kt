package adventofcode

sealed class Point<T> {
    abstract operator fun plus(other: T): T
}

data class Point2D(val x: Int, val y: Int): Point<Point2D>() {
    override fun plus(other: Point2D) = Point2D(this.x + other.x, this.y + other.y)
}
data class Point3D(val x: Int, val y: Int, val z: Int): Point<Point3D>() {
    override fun plus(other: Point3D) = Point3D(this.x + other.x, this.y + other.y, this.z + other.z)
}
data class Point4D(val x: Int, val y: Int, val z: Int, val w: Int): Point<Point4D>() {
    override fun plus(other: Point4D) = Point4D(this.x + other.x, this.y + other.y, this.z + other.z, this.w + other.w)
}