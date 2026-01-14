package com.example.retrofitglide

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imgAvatar = findViewById<ImageView>(R.id.imgAvatar)
        val tvName = findViewById<TextView>(R.id.tvName)
        val tvEmail = findViewById<TextView>(R.id.tvEmail)

        // Terima data dari Intent
        val name = intent.getStringExtra("name")
        val email = intent.getStringExtra("email")
        val avatar = intent.getStringExtra("avatar")

        tvName.text = name
        tvEmail.text = email

        // Load gambar avatar ukuran besar
        Glide.with(this)
            .load(avatar)
            .into(imgAvatar)
    }
}
