package id.ac.ubaya.todoapp160419030.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import id.ac.ubaya.todoapp160419030.model.Todo
import id.ac.ubaya.todoapp160419030.model.TodoDatabase
import id.ac.ubaya.todoapp160419030.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailTodoViewModel(application: Application)
    : AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val todoLD = MutableLiveData<Todo>()

    fun addTodo(list: List<Todo>) {
        launch {
            val db = buildDb(getApplication())

//            val db = Room.databaseBuilder(
//                getApplication(), TodoDatabase::class.java,
//                "newtododb"
//            ).build()
            db.todoDao().insertAll(*list.toTypedArray())
        }
    }
    fun fetch(uuid:Int) {
        launch {
            val db = buildDb(getApplication())
            todoLD.value =  db.todoDao().selectTodo(uuid)
        }
    }
    fun update(title:String, notes:String, priority:Int, uuid:Int) {
        launch {
            val db = buildDb(getApplication())
            db.todoDao().update(title, notes, priority, uuid)
        }
    }
    fun update_done(is_done:Int, uuid:Int){
        launch {
            val db= buildDb(getApplication())
            db.todoDao().update(is_done, uuid)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}



