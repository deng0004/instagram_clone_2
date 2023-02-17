package com.example.instagram_clone.auth


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.instagram_clone.DestinationScreen
import com.example.instagram_clone.IgViewModel
import com.example.instagram_clone.main.navigateTo
import com.example.instagram_clone.R
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp
import com.example.instagram_clone.main.CheckSignedIn
import com.example.instagram_clone.main.CommonProgressSpinner

@Composable
fun LoginScreen(navController: NavController, vm: IgViewModel) {

    CheckSignedIn(vm = vm, navController = navController)

    val focus = LocalFocusManager.current

    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .verticalScroll(
                rememberScrollState()
            ),
        horizontalAlignment = Alignment.CenterHorizontally
        ){


            var emailState by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                mutableStateOf(TextFieldValue())
            }
            var passState by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                mutableStateOf(TextFieldValue())
            }

            Image(
                painter = painterResource(id = R.drawable.ig_logo ),
                contentDescription = null,
                modifier = Modifier
                    .width(250.dp)
                    .padding(top = 16.dp)
                    .padding(8.dp)
            )

            Text(text = "Login",
                modifier = Modifier.padding(8.dp),
                fontSize = 30.sp,
                fontFamily = FontFamily.Serif
            )


            OutlinedTextField(
                value = emailState,
                onValueChange = { emailState = it },
                modifier = Modifier.padding(8.dp),
                label = { Text("Email") }
            )

            OutlinedTextField(
                value = passState,
                onValueChange = { passState = it },
                modifier = Modifier.padding(8.dp),
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation()
            )

            Button(
                onClick = {
                    focus.clearFocus(force = true)
                    vm.onLogin(emailState.text, passState.text)
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "LOGIN")
            }

            Text(text = "New here? Go to signup ->",
                color = Color.Blue,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        navigateTo(navController, DestinationScreen.Signup)
                    })
        }
        val isLoading = vm.inProgress.value
        if (isLoading) {
            CommonProgressSpinner()
        }
    }
}


