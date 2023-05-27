package com.example.memesshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.memesshare.databinding.ActivityMainBinding
import com.example.memesshare.viewmodel.MemeViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MemeViewModel

    private var currentImgUrl: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MemeViewModel::class.java)
        viewModel.getMeme()
        binding.nextBtn.setOnClickListener {
            viewModel.getMeme()
        }

        binding.shareBtn.setOnClickListener {
            shareImg()
        }
        observeChanges()

    }

    private fun shareImg() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "Checkout this meme $currentImgUrl")
        val chooser = Intent.createChooser(intent, "Share this meme using")
        startActivity(chooser)
    }

    private fun observeChanges() {
        viewModel.memeData.observe(this) {

            it?.let {
                // Glide.with()
                Glide.with(this).load(it.url).into(binding.img)
                currentImgUrl = it.url
            }

        }

        viewModel.loader.observe(this) {
            it?.let {
                if (it)
                    binding.progressBar.visibility = View.VISIBLE
                else
                    binding.progressBar.visibility = View.GONE
            }

            viewModel.error.observe(this) {
                it?.let {
                    binding.erorTv.visibility = View.GONE
                    binding.erorTv.text = it
                }
            }

        }
    }
}