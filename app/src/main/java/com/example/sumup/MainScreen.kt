package com.example.sumup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen(){
    var display by remember { mutableStateOf("0") }
    var operand1 by remember { mutableStateOf(0.0) }
    var operand2 by remember { mutableStateOf(0.0) }
    var operator by remember { mutableStateOf("") }
    var isNewOperand by remember { mutableStateOf(true) }

    fun appendToDisplay(value: String) {
        if (isNewOperand) {
            display = value
            isNewOperand = false
        } else {
            display = if (display == "0") value else display + value
        }
    }

    fun setOperator(op: String) {
        operand1 = display.toDouble()
        operator = op
        isNewOperand = true
    }

    fun calculate() {
        operand2 = display.toDouble()
        display = when (operator) {
            "+" -> (operand1 + operand2).toString()
            "-" -> (operand1 - operand2).toString()
            "*" -> (operand1 * operand2).toString()
            "/" -> if (operand2 != 0.0) (operand1 / operand2).toString() else "Error"
            else -> display
        }
        isNewOperand = true
    }

    fun clear() {
        display = "0"
        operand1 = 0.0
        operand2 = 0.0
        operator = ""
        isNewOperand = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF212121))
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = display,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            textAlign = TextAlign.End
        )

        val buttonColors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF424242),
            contentColor = Color.White
        )
        val operatorColors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFF9800),
            contentColor = Color.White
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CalculatorButton("7", buttonColors) { appendToDisplay("7") }
            CalculatorButton("8", buttonColors) { appendToDisplay("8") }
            CalculatorButton("9", buttonColors) { appendToDisplay("9") }
            CalculatorButton("/", operatorColors) { setOperator("/") }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CalculatorButton("4", buttonColors) { appendToDisplay("4") }
            CalculatorButton("5", buttonColors) { appendToDisplay("5") }
            CalculatorButton("6", buttonColors) { appendToDisplay("6") }
            CalculatorButton("*", operatorColors) { setOperator("*") }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CalculatorButton("1", buttonColors) { appendToDisplay("1") }
            CalculatorButton("2", buttonColors) { appendToDisplay("2") }
            CalculatorButton("3", buttonColors) { appendToDisplay("3") }
            CalculatorButton("-", operatorColors) { setOperator("-") }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CalculatorButton("0", buttonColors) { appendToDisplay("0") }
            CalculatorButton("C", operatorColors) { clear() }
            CalculatorButton("=", operatorColors) { calculate() }
            CalculatorButton("+", operatorColors) { setOperator("+") }
        }
    }
}

@Composable
fun CalculatorButton(
    text: String,
    colors: androidx.compose.material3.ButtonColors,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(80.dp)
            .padding(4.dp),
        colors = colors
    ) {
        Text(text = text, fontSize = 24.sp)
    }
}
