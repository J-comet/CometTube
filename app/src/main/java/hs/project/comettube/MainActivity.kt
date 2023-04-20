package hs.project.comettube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import hs.project.comettube.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private lateinit var videoAdapter: VideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        videoAdapter = VideoAdapter()

        with(binding.rvVideo) {
            layoutManager = LinearLayoutManager(context)
            adapter = videoAdapter
        }

        val videoList = readData("video.json", VideoList::class.java) ?: VideoList(emptyList())
        videoAdapter.submitList(videoList.videos.toList())
    }
}