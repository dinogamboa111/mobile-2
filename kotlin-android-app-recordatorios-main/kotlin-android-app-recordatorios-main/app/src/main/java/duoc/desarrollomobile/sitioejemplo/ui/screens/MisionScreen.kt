package duoc.desarrollomobile.sitioejemplo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import duoc.desarrollomobile.sitioejemplo.data.Mision
import duoc.desarrollomobile.sitioejemplo.ui.MisionViewModel
import duoc.desarrollomobile.sitioejemplo.ui.components.AppTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MisionScreen(viewModel: MisionViewModel) {
    val misiones by viewModel.misiones.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            AppTopBar(title = "Misiones Espaciales 🚀")
        }
    ) { innerPadding ->
        if (misiones.isEmpty()) {
            // Mensaje si no hay misiones
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No hay misiones disponibles",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(8.dp)
            ) {
                items(misiones) { mision ->
                    MisionItem(
                        mision = mision,
                        onCompletar = {
                            viewModel.actualizarEstado(mision.id, !mision.completada)
                        },
                        onFavorito = {
                            val actualizada = mision.copy(favorita = !mision.favorita)
                            viewModel.agregarMision(actualizada)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MisionItem(
    mision: Mision,
    onCompletar: () -> Unit,
    onFavorito: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (mision.completada)
                MaterialTheme.colorScheme.secondaryContainer
            else
                MaterialTheme.colorScheme.surface
        )
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = mision.nombre,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                IconButton(onClick = onFavorito) {
                    Icon(
                        imageVector = if (mision.favorita) Icons.Filled.Star else Icons.Outlined.StarOutline,
                        contentDescription = "Favorito",
                        tint = if (mision.favorita)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(text = "🎯 Objetivo: ${mision.objetivo}")
            Text(text = "🪐 Destino: ${mision.planetaDestino}")
            Text(text = "⚙️ Prioridad: ${mision.prioridad}")
            Text(text = "🚀 Lanzamiento: ${mision.fechaLanzamiento}")

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = onCompletar) {
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = "Completada",
                        tint = if (mision.completada)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}
