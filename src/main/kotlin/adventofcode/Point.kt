package adventofcode

sealed class Point

data class Point2D(val x: Int, val y: Int): Point()
data class Point3D(val x: Int, val y: Int, val z: Int): Point()
data class Point4D(val x: Int, val y: Int, val z: Int, val w: Int): Point()