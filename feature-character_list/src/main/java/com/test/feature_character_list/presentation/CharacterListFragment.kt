package com.test.feature_character_list.presentation

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.test.feature_character_list.databinding.CharacterListBinding

class CharacterListFragment : Fragment() {
    private lateinit var binding: CharacterListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.characterList.setOnClickListener {
            val navOptions = NavOptions.Builder()
                .setEnterAnim(com.test.utils.R.anim.slide_in_left)
                .setExitAnim(com.test.utils.R.anim.slide_out_left)
                .setPopEnterAnim(com.test.utils.R.anim.slide_in_right)
                .setPopExitAnim(com.test.utils.R.anim.slide_out_right)
                .build()
            findNavController().navigate(Uri.parse("jetpack://detail/Man"), navOptions)
        }
    }
}