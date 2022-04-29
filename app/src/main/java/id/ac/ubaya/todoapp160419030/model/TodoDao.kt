package id.ac.ubaya.todoapp160419030.model

import androidx.room.*
import java.util.*

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg todo:Todo)

    @Query("SELECT * FROM todo ORDER BY priority DESC")
    suspend fun selectAllTodo(): List<Todo>

    @Query("SELECT * FROM todo WHERE uuid= :id")
    suspend fun selectTodo(id:Int): Todo

    @Query("UPDATE todo SET title=:title, notes=:notes, priority=:priority WHERE uuid = :id")
    suspend fun update(title:String, notes:String, priority:Int, id:Int)

    @Query("UPDATE todo SET is_done=:is_done WHERE uuid=:id")
    suspend fun update(is_done:Int, id: Int)
    @Delete
    suspend fun deleteTodo(todo:Todo)
}