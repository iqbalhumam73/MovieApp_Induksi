package com.mandiriinduksi.swiftmovie.data.dao
import androidx.room.Dao
import androidx.room.Insert
import com.mandiriinduksi.swiftmovie.data.entities.data.User

@Dao
interface UserDao {

    @Insert
    suspend fun addUser (user: User)


}