import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.criminalintent.Crime
import java.util.*

@Dao
interface CrimeDao {

    @Query("SELECT * FROM crime")
//    fun getCrimes(): List<Crime>
    fun getCrimes(): LiveData<List<Crime>>

    @Query("SELECT * FROM crime WHERE id=(:id)")
//    fun getCrime(id: UUID): Crime?
    fun getCrime(id: UUID): LiveData<Crime?>

    // takes in a crime object, uses the ID stored in that crime to find associated row,
    // then updates the data in the row
    @Update
    fun updateCrime(crime: Crime)

    // parameter is the crime you want to add to database table
    @Insert
    fun addCrime(crime: Crime)

}