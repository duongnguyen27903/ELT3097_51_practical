package com.example.myapplication.practical

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import java.text.NumberFormat

@Preview
@Composable
fun CalculateTip(){

    var amountInput by remember { mutableStateOf("") }
    val amount = amountInput.toDoubleOrNull() ?: 0.0

    var roundupTip by remember { mutableStateOf(false) }

    var tipPercentInput by remember { mutableStateOf("") }
    val tipPercent = tipPercentInput.toDoubleOrNull() ?: 0.0

    val tip = calculateTip(amount,tipPercent,roundupTip)
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(1f)
            .fillMaxWidth(1f)
            .background(color = Color.White)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(R.string.calculate_tip),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            label = {Text(stringResource(R.string.bill_amount))},
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
            singleLine = true,
            value = amountInput,
            onValueChange = { amountInput = it },
            modifier = Modifier.fillMaxWidth(1f)
                .height(60.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            label = {Text("Tip Percentage")},
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
            singleLine = true,
            value = tipPercentInput,
            onValueChange = { tipPercentInput = it },
            modifier = Modifier.fillMaxWidth(1f)
                .height(60.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth(1f)
        ) {
            Text(
                text = "Round up tip?"
            )
            Switch(
                checked = roundupTip,
                onCheckedChange = { roundupTip = !roundupTip }
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource( R.string.tip_amount, tip,"$0.00" ),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
            modifier = Modifier.fillMaxWidth(1f),

        )
    }
}

private fun calculateTip(amount: Double, tipPercent: Double = 15.0, roundupTip : Boolean = false): String {
    var tip = tipPercent / 100 * amount
    if (roundupTip) {
        tip = kotlin.math.ceil(tip)
    }
    return NumberFormat.getCurrencyInstance().format(tip)
}