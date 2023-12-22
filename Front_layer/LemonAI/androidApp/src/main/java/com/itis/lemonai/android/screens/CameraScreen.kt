package com.itis.lemonai.android.screens


import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Matrix
import android.util.Base64
import android.util.Log
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.itis.lemonai.android.components.Primary
import com.itis.lemonai.android.components.Secondary
import java.util.concurrent.Executor
import compose.icons.FeatherIcons
import compose.icons.feathericons.Camera
import compose.icons.feathericons.Check
import compose.icons.feathericons.Send
import java.io.ByteArrayOutputStream


@Composable
fun CameraScreen(
    //viewModel: CameraViewModel = viewModel()
) {
    //val cameraState: CameraState by viewModel.state.collectAsStateWithLifecycle()

    CameraContent(
        //onPhotoCaptured = viewModel::storePhotoInGallery,
        //lastCapturedPhoto = cameraState.capturedImage
    )
}

@Composable
private fun CameraContent(
    //onPhotoCaptured: (Bitmap) -> Unit,
    //lastCapturedPhoto: Bitmap? = null
) {
    var lastCapturedPhoto: Bitmap? = null

    var isCaptured = remember { mutableStateOf(false) }

    var base64Image: String?

    var expanded by remember { mutableStateOf(false) }
    var selectedShop by remember { mutableStateOf("Магнит") }

    val itemsShops = listOf("Магнит", "Пятерочка", "Перекресток", "Дикси", "Ашан", "Лента", "Метро")

    val context: Context = LocalContext.current
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val cameraController: LifecycleCameraController =
        remember { LifecycleCameraController(context) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            ExtendedFloatingActionButton(
                modifier = Modifier
                    .padding(bottom = 70.dp)
                    .background(
                        brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                        shape = RoundedCornerShape(10.dp)
                    ),
                backgroundColor = androidx.compose.ui.graphics.Color.Transparent,
                text = { Text(text = "Сделать фото") },
                onClick = {
                    //capturePhoto(context, cameraController, onPhotoCaptured)
                    val mainExecutor: Executor = ContextCompat.getMainExecutor(context)
                    cameraController.takePicture(
                        mainExecutor,
                        object : ImageCapture.OnImageCapturedCallback() {
                            override fun onCaptureSuccess(image: ImageProxy) {
                                isCaptured.value = false
                                val correctedBitmap: Bitmap = image
                                    .toBitmap()
                                    .rotateBitmap(image.imageInfo.rotationDegrees)

                                isCaptured.value = true
                                lastCapturedPhoto = correctedBitmap
                                image.close()
                            }

                            override fun onError(exception: ImageCaptureException) {
                                Log.e("Error", "Capture error: ${exception.message}", exception)
                            }
                        }
                    )
                },
                icon = {
                    if (isCaptured.value) {
                        Icon(
                            imageVector = FeatherIcons.Check,
                            contentDescription = "Camera capture icon"
                        )
                    } else {
                        Icon(
                            imageVector = FeatherIcons.Camera,
                            contentDescription = "Camera capture icon"
                        )
                    }
                }

            )
        }
    ) { paddingValues: PaddingValues ->

        Box(modifier = Modifier.fillMaxSize()) {
            AndroidView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                factory = { context ->
                    PreviewView(context).apply {
                        layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                        setBackgroundColor(Color.BLACK.hashCode())
                        implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                        scaleType = PreviewView.ScaleType.FILL_START
                    }.also { previewView ->
                        previewView.controller = cameraController
                        cameraController.bindToLifecycle(lifecycleOwner)
                    }
                }
            )
            if (isCaptured.value) {
                ExtendedFloatingActionButton(
                    modifier = Modifier
                        .align(BottomStart)
                        .padding(bottom = 86.dp)
                        .padding(start = 10.dp)
                        .background(
                            brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                            shape = RoundedCornerShape(10.dp)
                        ),
                    backgroundColor = androidx.compose.ui.graphics.Color.Transparent,
                    text = { Text(text = "Отправить фото") },
                    onClick = {
                        expanded = true
                        base64Image = encodeImage(lastCapturedPhoto!!)
                    },
                    icon = {
                        Icon(
                            imageVector = FeatherIcons.Send,
                            contentDescription = null
                        )
                    }
                )
                LastPhotoPreview(
                    modifier = Modifier
                        .align(alignment = TopStart),
                    lastCapturedPhoto = lastCapturedPhoto!!
                )
            }

            if (expanded) {
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .padding(8.dp)
                        .background(
                            brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    itemsShops.forEachIndexed { index, shop ->
                        DropdownMenuItem(text = { androidx.compose.material3.Text(shop) },
                            onClick = {
                                selectedShop = shop
                                expanded = false
                            }
                        )
                        if (index < itemsShops.size - 1) {
                            Divider(modifier = Modifier.padding(horizontal = 8.dp),
                            color = androidx.compose.ui.graphics.Color.Black)
                        }
                    }
                }
            }

        }
    }
}


@Composable
private fun LastPhotoPreview(
    modifier: Modifier = Modifier,
    lastCapturedPhoto: Bitmap
) {

    val capturedPhoto: ImageBitmap =
        remember(lastCapturedPhoto.hashCode()) { lastCapturedPhoto.asImageBitmap() }

    Card(
        modifier = modifier
            .size(300.dp)
            .padding(16.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Image(
            bitmap = capturedPhoto,
            contentDescription = "Last captured photo",
            contentScale = androidx.compose.ui.layout.ContentScale.Crop
        )
    }
}

fun Bitmap.rotateBitmap(rotationDegrees: Int): Bitmap {
    val matrix = Matrix().apply {
        postRotate(-rotationDegrees.toFloat())
        postScale(-1f, -1f)
    }

    return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
}

fun encodeImage(bm: Bitmap): String? {
    val baos = ByteArrayOutputStream()
    bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
    val b = baos.toByteArray()
    return Base64.encodeToString(b, Base64.DEFAULT)
}

