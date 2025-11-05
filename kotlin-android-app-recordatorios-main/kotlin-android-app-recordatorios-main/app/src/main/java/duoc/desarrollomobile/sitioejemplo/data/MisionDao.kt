package duoc.desarrollomobile.sitioejemplo.data


import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MisionDao {

    @Query("SELECT * FROM misiones ORDER BY id DESC")
    fun getAllMisiones(): Flow<List<Mision>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(mision: Mision)

    @Delete
    suspend fun delete(mision: Mision)

    @Query("UPDATE misiones SET completada = :estado WHERE id = :id")
    suspend fun updateEstado(id: Int, estado: Boolean)

    @Query("SELECT * FROM misiones WHERE favorita = 1 ORDER BY id DESC")
    fun getMisionesFavoritas(): Flow<List<Mision>>
}
