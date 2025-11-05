package duoc.desarrollomobile.sitioejemplo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "misiones")
data class Mision(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val objetivo: String,
    val planetaDestino: String,
    val prioridad: String,
    val fechaLanzamiento: String,
    val completada: Boolean = false,
    val favorita: Boolean = false
)
