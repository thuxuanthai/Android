     package com.example.lesson6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class MainActivity : AppCompatActivity() {
    val articleSource = arrayListOf<ArticleInfo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recycle)

        val requestQueue = Volley.newRequestQueue(this)
        val url = "https://fontkeyboard.org/wsv/?json_name=articles"
        val request = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener {
                response ->try {
            val jsonArray = response.getJSONArray("articles")
            for (i in 0 until jsonArray.length()) {
                val article = jsonArray.getJSONObject(i)
                val id = article.getInt("article_id")
                val title = article.getString("article_title")
                val img = article.getString("article_image")
                val desc = article.getString("article_description")
                val articleInfo = ArticleInfo(id,title,img,desc)
                articleSource.add(articleInfo)
            }
            val articleAdapter = ArticleAdapter(this,articleSource)
            recyclerView.layoutManager = GridLayoutManager(this,2)
            recyclerView.adapter = articleAdapter

            articleAdapter.onItemClick = { article ->
//                Toast.makeText(this,article.title,Toast.LENGTH_LONG).show()
                val detailtIntent = Intent(this,DetailActivity::class.java)
                detailtIntent.putExtra("title",article.title)
                detailtIntent.putExtra("desc",article.desc)
                detailtIntent.putExtra("imgURL",article.imgURL)
                startActivity(detailtIntent)
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }
        }, Response.ErrorListener { error -> error.printStackTrace() })
        requestQueue?.add(request)
    }
}