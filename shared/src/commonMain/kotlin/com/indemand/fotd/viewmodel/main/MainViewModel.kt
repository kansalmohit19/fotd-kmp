package com.indemand.fotd.viewmodel.main

import com.indemand.fotd.BaseViewModel
import com.indemand.fotd.domain.model.BottomNavItem
import com.indemand.fotd.domain.uistate.MainUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel() : BaseViewModel() {

    private val _mainUIFlow: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState.Idle)
    val mainUIFlow: StateFlow<MainUiState> get() = _mainUIFlow

    private val _tabsListFlow: MutableStateFlow<List<BottomNavItem>> = MutableStateFlow(emptyList())
    val tabsListFlow: StateFlow<List<BottomNavItem>> get() = _tabsListFlow

    init {
        getListOfTabs()
    }

    private fun getListOfTabs() {
        scope.launch {
            _tabsListFlow.value = listOf(BottomNavItem("List") {
                _mainUIFlow.value = MainUiState.ShowFactListView
            }, BottomNavItem("Home", isSelected = true) {
                _mainUIFlow.value = MainUiState.ShowHomeView
            }, BottomNavItem("More") {
                _mainUIFlow.value = MainUiState.ShowMoreView
            })
        }
    }

    fun onTabSelected(item: BottomNavItem) {
        _tabsListFlow.value = _tabsListFlow.value.map {
            it.copy(isSelected = it.label == item.label)
        }
    }
}