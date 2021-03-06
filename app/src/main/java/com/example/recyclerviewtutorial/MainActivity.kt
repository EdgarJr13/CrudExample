package com.example.recyclerviewtutorial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewtutorial.adapter.ListaAdapter
import com.example.recyclerviewtutorial.db.DatabaseHandler
import com.example.recyclerviewtutorial.model.Pessoa
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var nameList = ArrayList<String>()

    //Iniciando RecyclerView
    var listaAdapter: ListaAdapter? = null
    var linearLayoutManager: LinearLayoutManager? = null

    // Iniciando o SQLite
    var pessoaList = ArrayList<Pessoa>()
    var databaseHandler = DatabaseHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        btnInsert.setOnClickListener {
            val intent = Intent(this, NameActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onResume() {
        super.onResume()
        initView()
    }

    private fun initView() {
        pessoaList = databaseHandler.pessoas()
        listaAdapter = ListaAdapter(pessoaList, this, this::deleteAdapter)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = linearLayoutManager
        recyclerview.adapter = listaAdapter
    }

    private fun deleteAdapter(position: Int){
        pessoaList.removeAt(position)
        listaAdapter!!.notifyItemRemoved(position)
    }
}