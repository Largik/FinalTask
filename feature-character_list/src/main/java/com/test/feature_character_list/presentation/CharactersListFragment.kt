package com.test.feature_character_list.presentation

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.test.feature_character_list.R
import com.test.feature_character_list.databinding.CharacterListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersListFragment : Fragment() {

    private lateinit var binding: CharacterListBinding
    private val viewModel: CharactersListViewModel by viewModels()

    private val pagingAdapter: CharactersPagingAdapter by lazy {
        CharactersPagingAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CharacterListBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.character_List_action_bar)

        return binding.root
    }

    @OptIn(FlowPreview::class)
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.appbar_menu, menu)

        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView

        lifecycleScope.launch {
            searchView.getQueryTextChangeStateFlow()
                .debounce(SEARCH_TIMEOUT)
                .distinctUntilChanged()
                .collectLatest { query ->
                    lifecycleScope.launch {
                        viewModel.getCharactersFlow(query)
                            .collectLatest(pagingAdapter::submitData)
                    }
                }
        }
        bindPagingAdapter()
    }

    private fun bindPagingAdapter() {
        pagingAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.ALLOW

        val header = CharactersListLoadStateAdapter { pagingAdapter.retry() }
        bindLoadingStates(header)

        binding.characterList.adapter = pagingAdapter.withLoadStateHeaderAndFooter(
            header = header,
            footer = CharactersListLoadStateAdapter { pagingAdapter.retry() }
        )
    }

    private fun bindLoadingStates(header: CharactersListLoadStateAdapter) {
        lifecycleScope.launch {
            pagingAdapter.loadStateFlow.collectLatest { loadState ->
                header.loadState = loadState.mediator
                    ?.refresh
                    ?.takeIf { it !is LoadState.NotLoading && pagingAdapter.itemCount > 0 }
                    ?: loadState.prepend

                binding.characterList.isVisible =
                    loadState.source.refresh is LoadState.NotLoading || loadState.mediator?.refresh is LoadState.NotLoading

                binding.progressBar.isVisible =
                    loadState.mediator?.refresh is LoadState.Loading

                val initialRefreshFailed =
                    loadState.mediator?.refresh is LoadState.Error && pagingAdapter.itemCount == 0

                val refreshFailed =
                    loadState.mediator?.refresh is LoadState.Error || loadState.source.refresh is LoadState.Error

                if (initialRefreshFailed || refreshFailed) {
                    binding.characterList.scrollToPosition(TOP_POSITION)
                }
            }
        }
    }

    private companion object {
        const val TOP_POSITION = 0
        const val SEARCH_TIMEOUT = 500L
    }
}