package duoc.desarrollomobile.sitioejemplo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import duoc.desarrollomobile.sitioejemplo.data.MisionRepository

class MisionViewModelFactory(private val repository: MisionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MisionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MisionViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
