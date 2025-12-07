package com.indemand.fotd.facts.main

import com.indemand.fotd.BaseViewModel
import com.indemand.fotd.BottomNavItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel() : BaseViewModel() {

    private val _tabsListFlow: MutableStateFlow<List<BottomNavItem>> = MutableStateFlow(emptyList())
    val tabsListFlow: StateFlow<List<BottomNavItem>> get() = _tabsListFlow

    init {
        getListOfTabs()
    }

    private fun getListOfTabs() {
        scope.launch {
            _tabsListFlow.value = BottomNavItem.items
        }
    }
}