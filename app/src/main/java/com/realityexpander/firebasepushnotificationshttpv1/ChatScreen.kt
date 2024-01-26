@file:OptIn(ExperimentalMaterial3Api::class)

package com.realityexpander.firebasepushnotificationshttpv1

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.messaging.messaging
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.tasks.await

@Composable
fun ChatScreen(
    messageText: String,
    onMessageChanged: (String) -> Unit,
    onSendMessage: () -> Unit,
    onBroadcastMessage: () -> Unit,
    state: ChatState,
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Show token
        Text(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            text = "Local Token: ${state.localToken.take(15) + "..."}",
            textAlign = TextAlign.Start
        )
        Spacer(Modifier.height(8.dp))

        // Show message text field
        OutlinedTextField(
            value = messageText,
            onValueChange = onMessageChanged,
            placeholder = {
                Text("Enter a message")
            },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
        ) {
            if(state.remoteToken.isNotBlank()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onSendMessage()
                        }
                    ,
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onSendMessage
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "Send"
                        )
                    }
                   Text("Send to 1 User: ${state.remoteToken.take(15) + "..."}")
                }
                Spacer(Modifier.width(16.dp))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onBroadcastMessage()
                    }
                ,
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBroadcastMessage
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Broadcast"
                    )
                }
                Text("Broadcast to Topic: chat")
            }
        }
        Divider(Modifier.height(2.dp))
        Spacer(Modifier.height(8.dp))

        // show messages
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Messages:",
            textAlign = TextAlign.Start
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            content = {
            items(state.messages.size) { index ->
                Text(state.messages[index])
            }
        })
    }
}
