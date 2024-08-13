package org.example.project.testdemo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        var textA by remember { mutableStateOf("") }
        var textB by remember { mutableStateOf("") }
        val keyboardController = LocalSoftwareKeyboardController.current
        val state = rememberScrollState()

        if (state.isScrollInProgress) {
            keyboardController?.hide()
        }

        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(state)
                .padding(32.dp)
                .clickable(
                    indication = null, // set null to disable the ripple effect
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = { keyboardController?.hide() }
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier.padding(vertical = 100.dp),
                text = "Clicking blank area or scrolling will hide keyboard."
            )

            Text("Multiple lines input with Done button to hide keyboard.")
            TextField(
                modifier = Modifier.fillMaxWidth().padding(bottom = 100.dp),
                value = textA,
                onValueChange = { textA = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                singleLine = false
            )

            Text("Multiple lines input with Return button to break lines.")
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = textB,
                onValueChange = { textB = it },
                singleLine = false
            )

            // Having this to make the screen scrollable
            Spacer(modifier = Modifier.size(500.dp))
        }
    }
}