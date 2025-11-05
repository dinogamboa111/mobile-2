package duoc.desarrollomobile.sitioejemplo.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Clase principal de la base de datos Room para la app espacial
 */
@Database(
    entities = [Mision::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    // DAO principal
    abstract fun misionDao(): MisionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Obtiene o crea la base de datos usando el patrón Singleton.
         * Evita múltiples instancias en memoria.
         */
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "misiones_database" // nuevo nombre de archivo SQLite
                )
                    .fallbackToDestructiveMigration() // recrea si hay cambios de versión
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}

