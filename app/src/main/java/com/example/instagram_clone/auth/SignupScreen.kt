package com.example.instagram_clone.auth


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.instagram_clone.IgViewModel
import com.example.instagram_clone.R
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.instagram_clone.DestinationScreen
import com.example.instagram_clone.main.CheckSignedIn
import com.example.instagram_clone.main.CommonProgressSpinner
import com.example.instagram_clone.main.navigateTo


@Composable
fun SignupScreen(navController: NavController, vm: IgViewModel){

    CheckSignedIn(vm=vm, navController=navController)

    val focus = LocalFocusManager.current

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(
                    rememberScrollState()
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var usernameState by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                mutableStateOf(TextFieldValue())
            }
            var emailState by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                mutableStateOf(TextFieldValue())
            }
            var passState by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                mutableStateOf(TextFieldValue())
            }

//            val emailState = rememberSaveable{mutableSetOf(TextFieldValue())}
//            val passState = rememberSaveable {mutableSetOf(TextFieldValue())}


            Image(
                painter = painterResource(id = R.drawable.ig_logo),
                contentDescription = null,
                modifier = Modifier
                    .width(250.dp)
                    .padding(top = 16.dp)
                    .padding(8.dp)
            )
            Text(
                text = "Signup",
                modifier = Modifier.padding(8.dp),
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif
            )
//            OutlinedTextField(value = usernameState.value, onValueChange = {usernameState = it}, modifier = Modifier.padding(8.dp), label = {Text(text="username")})

            OutlinedTextField(
                value = usernameState,
                onValueChange = { usernameState = it },
                modifier = Modifier.padding(8.dp),
                label = { Text("Username") }
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
                    vm.onSignup(
                        usernameState.text,
                        emailState.text,
                        passState.text
                    )
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "SIGN UP")
            }
            Text(text = "Already a user? Go to login ->",
                color = Color.Blue,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        navigateTo(navController, DestinationScreen.Login)
                    })

        }

        val isLoading = vm.inProgress.value
        if (isLoading) {
            CommonProgressSpinner()
        }
    }

}