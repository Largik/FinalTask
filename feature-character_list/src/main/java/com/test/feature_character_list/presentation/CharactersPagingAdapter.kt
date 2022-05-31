package com.test.feature_character_list.presentation

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.core_db.domain.CharacterModel
import com.test.feature_character_list.databinding.CharacterItemBinding
import com.test.feature_character_list.databinding.CharacterListBinding

class CharactersPagingAdapter :
    PagingDataAdapter<CharacterModel, CharactersPagingAdapter.ViewHolder>(CharacterDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = getItem(position)
        character?.let {
            holder.bind(character)
            holder.itemView.setOnClickListener {
                holder.navigateToCharacter(character, holder.itemView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CharacterItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder(private val binding: CharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun navigateToCharacter(
            character: CharacterModel,
            view: View
        ) {
            val navOptions =
                NavOptions.Builder()
                    .setEnterAnim(com.test.utils.R.anim.slide_in_left)
                    .setExitAnim(com.test.utils.R.anim.slide_out_left)
                    .setPopEnterAnim(com.test.utils.R.anim.slide_in_right)
                    .setPopExitAnim(com.test.utils.R.anim.slide_out_right)
                    .build()

            val uri = Uri.parse("${DETAILS_DEEPLINK}${character.id}")
            view.findNavController().navigate(uri, navOptions)
        }

        fun bind(character: CharacterModel) {
            binding.characterName.text = character.name

            Glide.with(binding.characterImage.context)
                .load(character.image)
                .circleCrop()
                .into(binding.characterImage)
        }
    }


    class CharacterDiffCallback :
        DiffUtil.ItemCallback<CharacterModel>() {

        override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem == newItem
        }
    }

    private companion object {
        const val DETAILS_DEEPLINK = "android-app://detail/"
    }
}
