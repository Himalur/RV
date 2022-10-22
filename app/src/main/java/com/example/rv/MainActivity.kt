package com.example.rv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable
import com.example.rv.RecyclerAdapter

class MainActivity : AppCompatActivity() {
    private val list = mutableListOf<String>()
    private lateinit var adapter: RecyclerAdapter
    lateinit var state: State
    class State(
        var EditTextMem: String,
        var ListMem = mutableListOf<String>(),
    ): Serializable
    val STATE_KEY = "STATE"

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val editText = findViewById<EditText>(R.id.editText)

        // сохраняем текущие значения в state
        state.EditTextMem = editText.text
        state.ListMem = list

            outState.putSerializable(STATE_KEY, state)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editText: EditText = findViewById<EditText>(R.id.editText)

        adapter = RecyclerAdapter(list) {
            // адаптеру передали обработчик удаления элемента
            list.removeAt(it)
            adapter.notifyItemRemoved(it)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val buttonAdd = findViewById<Button>(R.id.button)
        buttonAdd.setOnClickListener {
            if(editText.text.toString().isNotEmpty()){
                list.add(editText.text.toString())
                editText.setText("")
                adapter.notifyItemInserted(list.lastIndex)
            }
        }
        val buttonAdd2 = findViewById<Button>(R.id.button2)
        buttonAdd2.setOnClickListener {
            if(editText.text.toString().isNotEmpty()){
                list.add(editText.text.toString())
                editText.setText("")
                adapter.notifyItemInserted(list.lastIndex)
            }
        }

    }
}
