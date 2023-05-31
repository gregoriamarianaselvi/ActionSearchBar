package com.example.actionsearchbarpraktikum

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * Menyisipkan menu ke activity
     *
     * @param menu
     * @return
     */

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        /*search bar*/
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.menu_search)?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = "Silahkan cari apa saja"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            /*ini adalah function yang dieksekusi ketika tombol search di keyboard diklik*/
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.i("TAG", "Text Submit ${query}")
                return true
            }

            /*function yang dieksekui ketika terjadi perubahan data di searchbar*/
            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText?.length!! > 2) {
                    Log.i("TAG", "Text Submit ${newText}")
                }
                return true
            }
        });

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_1 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, MenuFragment())
                    .addToBackStack(null)
                    .commit()
                return true
            }
            R.id.menu_2 -> {
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return true
        }
    }
}

