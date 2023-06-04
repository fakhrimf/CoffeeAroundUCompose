package com.sixgroup.coffeearounducompose.utils

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sixgroup.coffeearounducompose.ui.theme.Accent
import com.sixgroup.coffeearounducompose.ui.theme.DarkBrown
import com.sixgroup.coffeearounducompose.ui.theme.Greenish
import com.sixgroup.coffeearounducompose.ui.theme.MontSerrat

object CustomView {
    @Composable
    fun Title(text: String, modifier: Modifier = Modifier) {
        Text(
            text = text,
            fontFamily = MontSerrat,
            fontWeight = FontWeight.Black,
            color = DarkBrown,
            fontSize = 24.sp,
            lineHeight = 34.sp,
            modifier = modifier
        )
    }

    @Composable
    fun Subtitle(text: String, modifier: Modifier = Modifier) {
        Text(
            text = text,
            fontFamily = MontSerrat,
            fontWeight = FontWeight.Bold,
            color = Greenish,
            fontSize = 18.sp,
            lineHeight = 24.sp,
            modifier = modifier,
        )
    }

    @Composable
    fun InfoText(text: String, modifier: Modifier = Modifier) {
        Text(
            text = text,
            fontFamily = MontSerrat,
            fontWeight = FontWeight.Medium,
            color = Greenish,
            fontSize = 14.sp,
            lineHeight = 24.sp,
            modifier = modifier,
        )
    }


    @Composable
    fun TextClick(text: String, modifier: Modifier = Modifier) {
        Text(
            text = text,
            fontFamily = MontSerrat,
            fontWeight = FontWeight.Bold,
            color = Accent,
            fontSize = 14.sp,
            modifier = modifier,
        )
    }

    @Composable
    fun ButtonText(text: String, modifier: Modifier = Modifier) {
        Text(
            text = text,
            fontFamily = MontSerrat,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            fontSize = 18.sp,
            lineHeight = 18.sp,
            modifier = modifier,
        )
    }
}