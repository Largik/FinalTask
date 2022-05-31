package com.test.feature_character_list.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.feature_character_list.databinding.CharacterListBinding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CharactersListLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<CharactersListLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: CharactersListLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): CharactersListLoadStateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CharacterListBinding.inflate(inflater, parent, false)
        return CharactersListLoadStateViewHolder(binding, retry)
    }
}

class CharactersListLoadStateViewHolder(
    private val binding: CharacterListBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        retry.invoke()
    }

    fun bind(loadState: LoadState) = with(binding) {
        if (loadState is LoadState.Error) {
            Toast.makeText(itemView.context, loadState.error.localizedMessage, Toast.LENGTH_SHORT)
                .show()
        }
        progressBar.isVisible = loadState is LoadState.Loading
    }
}

fun SearchView.getQueryTextChangeStateFlow(): StateFlow<String> {
    val searchQuery = MutableStateFlow("")

    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            this@getQueryTextChangeStateFlow.clearFocus()
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            searchQuery.value = newText ?: ""
            return true
        }
    })
    return searchQuery
}