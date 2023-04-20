package hs.project.comettube

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerControlView
import com.google.android.exoplayer2.ui.PlayerView
import hs.project.comettube.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private lateinit var videoAdapter: VideoAdapter

    private var player: ExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        videoAdapter = VideoAdapter { videoItem ->
            binding.motionLayout.setTransition(R.id.collapse, R.id.expand)
            binding.motionLayout.transitionToEnd()
            play(videoItem)
        }

        binding.motionLayout.jumpToState(R.id.collapse)

        with(binding.rvVideoList) {
            layoutManager = LinearLayoutManager(context)
            adapter = videoAdapter
        }

        val videoList = readData("video.json", VideoList::class.java) ?: VideoList(emptyList())
        videoAdapter.submitList(videoList.videos.toList())
    }

    override fun onStart() {
        super.onStart()
        if (player == null) {
            initExoPlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        if (player == null) {
            initExoPlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        player?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun initExoPlayer() {
        player = ExoPlayer.Builder(this@MainActivity).build()
            .also {
                binding.playerView.player = it
            }
    }

    private fun play(item: VideoItem) {
        player?.setMediaItem(MediaItem.fromUri(Uri.parse(item.videoUrl)))
        player?.prepare()
        player?.play()
    }
}