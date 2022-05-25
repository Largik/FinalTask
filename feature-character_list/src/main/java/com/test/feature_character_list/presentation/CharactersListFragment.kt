package com.test.feature_character_list.presentation

import android.app.Activity
import android.app.Application
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.test.feature_character_list.databinding.CharacterListBinding
import javax.inject.Inject

class CharactersListFragment : Fragment() {

    @Inject
    lateinit var charactersListViewModel: CharactersListViewModel

    private lateinit var binding: CharacterListBinding

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<CharactersListViewModel>()
            .newDetailsComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CharacterListBinding.inflate(inflater, container, false)

//        charactersListViewModel.charactersList.observe(
//            viewLifecycleOwner
//        ) { characters ->
//            if(characters.isNotEmpty()) {
//                characters?.let { /*charactersListAdapter.submitList(characters)*/ }
//                hideProgressBar()
//            } else {
//                showError()
//                hideProgressBar()
//            }
//        }

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

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
        binding.characterList.visibility = View.VISIBLE
    }
    private fun showError() {
        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
    }
}