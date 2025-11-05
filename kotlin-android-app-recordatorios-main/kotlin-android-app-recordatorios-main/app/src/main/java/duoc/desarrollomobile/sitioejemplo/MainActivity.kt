package duoc.desarrollomobile.sitioejemplo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import duoc.desarrollomobile.sitioejemplo.data.AppDatabase
import duoc.desarrollomobile.sitioejemplo.data.MisionRepository
import duoc.desarrollomobile.sitioejemplo.ui.screens.MisionScreen
import duoc.desarrollomobile.sitioejemplo.ui.MisionViewModel
import duoc.desarrollomobile.sitioejemplo.ui.MisionViewModelFactory
import duoc.desarrollomobile.sitioejemplo.ui.theme.SitioEjemploTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(this)
        val repository = MisionRepository(database.misionDao())
        val factory = MisionViewModelFactory(repository)

        setContent {
            SitioEjemploTheme {
                val viewModel: MisionViewModel = viewModel(factory = factory)
                MisionScreen(viewModel = viewModel)
            }
        }
    }
}
