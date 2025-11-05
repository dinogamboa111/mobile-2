package duoc.desarrollomobile.sitioejemplo.data

import kotlinx.coroutines.flow.Flow

/**
 * Repositorio que actúa como intermediario entre el DAO (base de datos)
 * y el ViewModel (lógica de UI).
 */
class MisionRepository(private val misionDao: MisionDao) {

    // Observa todas las misiones en tiempo real (Flow)
    val todasLasMisiones: Flow<List<Mision>> = misionDao.getAllMisiones()

    // Observa solo las misiones favoritas
    val misionesFavoritas: Flow<List<Mision>> = misionDao.getMisionesFavoritas()

    // Inserta o actualiza una misión
    suspend fun agregarMision(mision: Mision) {
        misionDao.insert(mision)
    }

    // Elimina una misión
    suspend fun eliminarMision(mision: Mision) {
        misionDao.delete(mision)
    }

    // Cambia el estado de completada
    suspend fun actualizarEstado(id: Int, estado: Boolean) {
        misionDao.updateEstado(id, estado)
    }
}
