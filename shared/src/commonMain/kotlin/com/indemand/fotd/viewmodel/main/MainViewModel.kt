package com.indemand.fotd.viewmodel.main

import com.indemand.fotd.BaseViewModel
import com.indemand.fotd.domain.model.BottomNavItem
import com.indemand.fotd.domain.model.BottomNavUiItem
import com.indemand.fotd.domain.uistate.MainUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel() : BaseViewModel() {

    private val _mainUIFlow: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState.Idle)
    val mainUIFlow: StateFlow<MainUiState> get() = _mainUIFlow

    private val _tabsListFlow: MutableStateFlow<List<BottomNavUiItem>> =
        MutableStateFlow(emptyList())
    val tabsListFlow: StateFlow<List<BottomNavUiItem>> get() = _tabsListFlow

    init {
        getListOfTabs()
    }

    private fun getListOfTabs() {
        scope.launch {
            _tabsListFlow.value = listOf(
                BottomNavUiItem(BottomNavItem.Blog(onClick = {
                _mainUIFlow.value = MainUiState.ShowBlogView
            }), isSelected = false), BottomNavUiItem(BottomNavItem.Home(onClick = {
                _mainUIFlow.value = MainUiState.ShowHomeView
            }), isSelected = true), BottomNavUiItem(BottomNavItem.More(onClick = {
                _mainUIFlow.value = MainUiState.ShowMoreView
            }), isSelected = false)
            )
        }
    }

    fun onTabSelected(navItem: BottomNavItem) {
        _tabsListFlow.value = _tabsListFlow.value.map {
            it.copy(isSelected = it.navItem == navItem)
        }
    }
}