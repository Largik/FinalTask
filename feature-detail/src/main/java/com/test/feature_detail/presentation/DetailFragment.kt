package com.test.feature_detail.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.test.feature_detail.databinding.DetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: DetailBinding
    private val viewModel: CharacterDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailBinding.inflate(inflater, container, false)

        bindCharacterVM()

        return binding.root
    }

    private fun bindCharacterVM() {
        viewModel.getCharacter(arguments?.getString(CHARACTER_ID_KEY)?.toInt() ?: 0)
            .observe(viewLifecycleOwner) {

                binding.characterName.text = it.name
                (activity as AppCompatActivity).supportActionBar?.title = it.name

                binding.characterGender.text = it.gender
                binding.characterStatus.text = it.status
                binding.characterSpecies.text = it.species
                binding.characterCreated.text = it.createDate


                Glide.with(binding.characterImage.context)
                    .load(it.image)
                    .into(binding.characterImage)
            }
    }

    private companion object {
        const val CHARACTER_ID_KEY = "characterId"
    }
}