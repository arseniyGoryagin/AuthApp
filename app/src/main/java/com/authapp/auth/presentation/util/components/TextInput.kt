package com.authapp.auth.presentation.util.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun TextInput(
                name : String,
                value : String,
                leadingIcon: @Composable() (() -> Unit)? = null,
                trailingIcon: @Composable() (() -> Unit)? = null,
                onValueChange : (String) -> Unit,
                showText : Boolean = true,
                enabled : Boolean = true,
                error : Boolean = false,
                modifier: Modifier = Modifier){

    Column(
        modifier = modifier
        
    ) {
        Text(
            text = name,
            color = Color.Gray)
        
        Spacer(modifier = Modifier.height(15.dp))
        
        TextField(
            isError = error,
            enabled = enabled,
            visualTransformation = if(showText){VisualTransformation.None}else{PasswordVisualTransformation()},
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            value = value,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.colors(

                disabledTextColor = Color.Black,
                disabledContainerColor = Color.LightGray,
                disabledPrefixColor = Color.LightGray,
                disabledSuffixColor = Color.LightGray,
                disabledIndicatorColor = Color.LightGray,

                errorTextColor = Color.Black,
                errorCursorColor = Color.Black,
                errorContainerColor = Color.White,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Gray,
                unfocusedLabelColor = Color.Gray,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth())
    }

}