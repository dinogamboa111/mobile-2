package duoc.desarrollomobile.sitioejemplo.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * TopAppBar principal de la aplicación con soporte Material 3
 * Incluye gradiente, iconos y acciones opcionales
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    navigationIcon: ImageVector? = null,
    navigationIconDescription: String? = null,
    onNavigationClick: (() -> Unit)? = null,
    actions: @Composable () -> Unit = {}
) {
    val gradientColors = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.primaryContainer
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(colors = gradientColors)
            )
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White
                )
            },
            navigationIcon = {
                if (navigationIcon != null && onNavigationClick != null) {
                    IconButton(onClick = onNavigationClick) {
                        Icon(
                            imageVector = navigationIcon,
                            contentDescription = navigationIconDescription,
                            tint = Color.White
                        )
                    }
                }
            },
            actions = { actions() },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Transparent,
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White,
                actionIconContentColor = Color.White
            ),
            windowInsets = WindowInsets.statusBars
        )
    }
}

/**
 * Variante del AppTopBar con botón de volver atrás
 * Ideal para pantallas secundarias
 */
@Composable
fun AppTopBarWithBack(
    title: String,
    onBackClick: () -> Unit,
    actions: @Composable () -> Unit = {}
) {
    AppTopBar(
        title = title,
        navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
        navigationIconDescription = "Volver",
        onNavigationClick = onBackClick,
        actions = actions
    )
}
