package hs.project.comettube

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hs.project.comettube.databinding.ItemVideoBinding

class VideoAdapter : ListAdapter<VideoItem, VideoAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<VideoItem?>() {
        override fun areItemsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean {
           return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean {
            return oldItem == newItem
        }
    }
) {


    inner class ViewHolder(private val binding: ItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: VideoItem) {
            binding.tvTitle.text = item.title
            binding.tvSubtitle.text = binding.root.context.getString(R.string.video_sub_title, item.channelName, item.viewCount, item.dateText)

            Glide.with(binding.ivThumb)
                .load(item.videoThumb)
                .centerCrop()
                .into(binding.ivThumb)

            Glide.with(binding.ivChannelLogo)
                .load(item.channelThumb)
                .circleCrop()
                .into(binding.ivChannelLogo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}