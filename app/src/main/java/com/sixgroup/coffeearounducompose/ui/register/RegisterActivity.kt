package com.sixgroup.coffeearounducompose.ui.register

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.sixgroup.coffeearounducompose.model.RegisterUserModel
import com.sixgroup.coffeearounducompose.ui.dialog.DialogPickImage
import com.sixgroup.coffeearounducompose.ui.theme.Accent
import com.sixgroup.coffeearounducompose.ui.theme.Background
import com.sixgroup.coffeearounducompose.ui.theme.CoffeeAroundUComposeTheme
import com.sixgroup.coffeearounducompose.ui.theme.DarkBrown
import com.sixgroup.coffeearounducompose.ui.theme.MontSerrat
import com.sixgroup.coffeearounducompose.utils.Constants
import com.sixgroup.coffeearounducompose.utils.Constants.CAMERA_REQUEST_CODE
import com.sixgroup.coffeearounducompose.utils.Constants.IMAGE_REQUEST_CODE
import com.sixgroup.coffeearounducompose.utils.CustomView
import es.dmoral.toasty.Toasty
import java.io.File
import java.util.Locale


private var photoPath = ""

class RegisterActivity : ComponentActivity() {
    private var path: Uri? = null
    private var file: File? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoffeeAroundUComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RegisterView(context = LocalContext.current, this)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && data != null && data.data != null) {
            path = data.data!!
            val bitmap = getImageBitmap(contentResolver, path!!)
            setContent {
                CoffeeAroundUComposeTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        RegisterView(
                            context = LocalContext.current,
                            this,
                            path = path.toString()
                        )
                    }
                }
            }
        } else if (requestCode == CAMERA_REQUEST_CODE) {
            file = File(photoPath)
            val result = BitmapFactory.decodeFile(file!!.path)
            setContent {
                CoffeeAroundUComposeTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        RegisterView(
                            context = LocalContext.current,
                            this,
                            path = photoPath
                        )
                    }
                }
            }
        }
    }

    private fun getImageBitmap(contentResolver: ContentResolver, path: Uri): Bitmap {
        @Suppress("DEPRECATION") return ImageDecoder.decodeBitmap(
            ImageDecoder.createSource(
                contentResolver,
                path
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterView(context: Context, activity: ComponentActivity, path: String? = null) {
    Scaffold(
        containerColor = Background,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Background),
                title = {
                    Text(
                        text = "Register",
                        color = DarkBrown,
                        fontFamily = MontSerrat,
                        fontWeight = FontWeight.Medium,
                        fontSize = 17.sp
                    )
                }
            )
        }
    ) {
        RegisterFields(
            context = context,
            modifier = Modifier.padding(it),
            activity = activity,
            path = path
        )
    }
}

private fun getImageIntent(): Intent {
    val intent = Intent()
    intent.apply {
        type = Constants.IMAGE_TYPE
        action = Intent.ACTION_GET_CONTENT
    }
    return Intent.createChooser(intent, "Pilih gambar yang ingin digunakan")
}

private val timeStamp: String = SimpleDateFormat(
    "hh:mm",
    Locale.US
).format(System.currentTimeMillis())

private fun createCustomTempFile(context: Context): File {
    val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile(timeStamp, ".jpg", storageDir)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterFields(
    context: Context,
    modifier: Modifier = Modifier,
    activity: ComponentActivity,
    path: String?
) {
    val openDialog = remember {
        mutableStateOf(false)
    }
    val useCamera = remember {
        mutableStateOf(false)
    }
    val useStorage = remember {
        mutableStateOf(false)
    }
    val vm = ViewModelProvider(activity)[RegisterViewModel::class.java]
    Box {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            val textFieldColors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                textColor = DarkBrown,
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedLeadingIconColor = Accent,
                focusedTrailingIconColor = Accent,
                focusedLabelColor = Accent,
                unfocusedTrailingIconColor = DarkBrown,
                unfocusedLeadingIconColor = DarkBrown,
                unfocusedLabelColor = DarkBrown
            )
            var loginInput by remember {
                mutableStateOf(TextFieldValue(""))
            }
            var nameInput by remember {
                mutableStateOf(TextFieldValue(""))
            }
            var phoneInput by remember {
                mutableStateOf(TextFieldValue(""))
            }
            var passwordInput by remember {
                mutableStateOf(TextFieldValue(""))
            }
            var isPasswordVisible by remember {
                mutableStateOf(false)
            }
            var addressInput by remember {
                mutableStateOf(TextFieldValue(""))
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Box {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(DarkBrown)
                    ) {
                        if (!path.isNullOrEmpty()) {
                            AsyncImage(
                                model = ImageRequest.Builder(context = context)
                                    .data(path)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "Profile Picture",
                                alignment = Alignment.Center,
                                contentScale = ContentScale.Crop,
                                colorFilter = ColorFilter.tint(Color.White),
                                modifier = Modifier
                                    .width(120.dp)
                                    .height(120.dp)
                                    .clip(CircleShape)
                                    .clickable {
                                        openDialog.value = true
                                    }
                            )
                        } else {
                            Image(
                                imageVector = Icons.Filled.Person,
                                contentDescription = "Profile Picture",
                                alignment = Alignment.Center,
                                contentScale = ContentScale.Crop,
                                colorFilter = ColorFilter.tint(Color.White),
                                modifier = Modifier
                                    .width(120.dp)
                                    .height(120.dp)
                                    .clip(CircleShape)
                                    .clickable {
                                        openDialog.value = true
                                    }
                            )
                        }
                        Log.d("GAMBAR", "RegisterFields: $photoPath")
                    }
                    Box(
                        modifier = Modifier
                            .offset(x = 82.dp, y = 82.dp)
                            .clip(CircleShape)
                            .background(Accent)
                            .clickable {
                                openDialog.value = true
                            }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "Edit Profile Picture",
                            modifier = Modifier.padding(7.dp),
                            tint = Color.White
                        )
                    }
                }
            }
            // Email Input
            RegisterField(
                value = loginInput,
                onValueChange = { loginInput = it },
                label = "E-Mail",
                type = KeyboardType.Email,
                modifier = Modifier.padding(top = 10.dp)
            )
            // Name Input=
            RegisterField(
                value = nameInput,
                onValueChange = { nameInput = it },
                label = "Name",
                type = KeyboardType.Text
            )
            // Phone Number
            RegisterField(
                value = phoneInput,
                onValueChange = { phoneInput = it },
                label = "Phone",
                type = KeyboardType.Phone
            )
            // Password Field
            TextField(
                value = passwordInput,
                onValueChange = { passwordInput = it },
                label = { Text(text = "Password") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                textStyle = TextStyle(
                    fontFamily = MontSerrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                ),
                trailingIcon = {
                    val image =
                        if (isPasswordVisible) Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(imageVector = image, contentDescription = null)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 12.dp)
                    .height(60.dp)
                    .clip(CircleShape),
                colors = textFieldColors
            )
            // Address Field
            RegisterField(
                value = addressInput,
                onValueChange = { addressInput = it },
                label = "Full Address",
                type = KeyboardType.Text
            )
            val response = vm.response.collectAsState()
            val isRegistering = remember {
                mutableStateOf(false)
            }
            Button(
                onClick = {
                    isRegistering.value = true
                    if (addressInput.text.isNotEmpty() && nameInput.text.isNotEmpty() && passwordInput.text.isNotEmpty() && phoneInput.text.isNotEmpty() && loginInput.text.isNotEmpty()) {
                        vm.register(
                            RegisterUserModel(
                                name = nameInput.text,
                                password = passwordInput.text,
                                email = loginInput.text,
                                address = addressInput.text,
                                phone_number = phoneInput.text
                            )
                        )
                        if (response.value != null) {
                            if (response.value!!.success) {
                                Toasty.success(context, "Register berhasil").show()
                                activity.finish()
                            }
                        } else {
                            Toasty.warning(context, "Terjadi kesalahan, coba register kembali").show()
                        }
                        isRegistering.value = false
                    } else {
                        Toasty.warning(context, "Isi semua field yang ada").show()
                        isRegistering.value = false
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Accent),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 54.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)
                    .height(60.dp),
                content = {
                    if (!isRegistering.value)
                        CustomView.ButtonText(text = "REGISTER")
                    else
                        CircularProgressIndicator(color = Accent, modifier = Modifier.padding(10.dp))
                },
                enabled = !isRegistering.value
            )
        }
        if (openDialog.value) {
            DialogPickImage().GetDialog(
                openDialog = openDialog,
                useCamera = useCamera,
                useStorage = useStorage
            )
        }
        if (useCamera.value) {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.resolveActivity(activity.packageManager)
            createCustomTempFile(activity.application).also {
                val photoURI: Uri = FileProvider.getUriForFile(
                    context,
                    "com.sixgroup.coffeearounducompose.provider",
                    it
                )
                photoPath = it.absolutePath
                Log.d("PATH", "RegisterFields: $photoPath")
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                activity.startActivityForResult(intent, CAMERA_REQUEST_CODE)
            }
        } else if (useStorage.value) {
            activity.startActivityForResult(getImageIntent(), IMAGE_REQUEST_CODE)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    type: KeyboardType
) {
    val textFieldColors = TextFieldDefaults.textFieldColors(
        containerColor = Color.White,
        textColor = DarkBrown,
        disabledIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        focusedLeadingIconColor = Accent,
        focusedTrailingIconColor = Accent,
        focusedLabelColor = Accent,
        unfocusedTrailingIconColor = DarkBrown,
        unfocusedLeadingIconColor = DarkBrown,
        unfocusedLabelColor = DarkBrown
    )
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(keyboardType = type),
        textStyle = TextStyle(
            fontFamily = MontSerrat,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
            .height(60.dp)
            .clip(CircleShape),
        colors = textFieldColors
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    CoffeeAroundUComposeTheme {
//        RegisterView(context = LocalContext.current)
    }
}