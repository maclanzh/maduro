    package com.maclanzh.venezuelan

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun WhiteboardScreen() {
    val paths = remember { mutableStateListOf<Path>() }
    var currentPath by remember { mutableStateOf<Path?>(null) }
    var tick by remember { mutableIntStateOf(0) }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .pointerInput(Unit) {
                awaitEachGesture {
                    val down = awaitFirstDown()
                    val path = Path().apply { moveTo(down.position.x, down.position.y) }
                    currentPath = path
                    var last = down.position

                    do {
                        val event = awaitPointerEvent()
                        event.changes.forEach { change ->
                            val p = change.position
                            val mid = Offset((last.x + p.x) / 2f, (last.y + p.y) / 2f)
                            path.quadraticBezierTo(last.x, last.y, mid.x, mid.y)
                            last = p
                            change.consume()
                        }
                        tick++
                    } while (event.changes.any { it.pressed })

                    paths.add(path)
                    currentPath = null
                }
            }
    ) {
        val style = Stroke(width = 8f, cap = StrokeCap.Round, join = StrokeJoin.Round)
        paths.forEach { drawPath(it, Color.Black, style = style) }
        currentPath?.let { drawPath(it, Color.Black, style = style) }
    }
}