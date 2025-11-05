package duoc.desarrollomobile.sitioejemplo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import duoc.desarrollomobile.sitioejemplo.data.Mision
import duoc.desarrollomobile.sitioejemplo.data.MisionRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel que conecta la capa de datos (Room) con la UI (Compose).
 */
class MisionViewModel(private val repository: MisionRepository) : ViewModel() {

    // Lista de misiones observables como StateFlow
    val misiones = repository.todasLasMisiones
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun agregarMision(mision: Mision) {
        viewModelScope.launch {
            repository.agregarMision(mision)
        }
    }

    fun eliminarMision(mision: Mision) {
        viewModelScope.launch {
            repository.eliminarMision(mision)
        }
    }

    fun actualizarEstado(id: Int, estado: Boolean) {
        viewModelScope.launch {
            repository.actualizarEstado(id, estado)
        }
    }
}
