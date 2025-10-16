package com.indemand.fotd.android.test

class PilotNavigationManager<ROUTE : S, ACTION : Any> {
    public var listener: PilotNavigationListener<ROUTE>? = null
}

public abstract class PilotNavigationListener<route : PilotNavigationRoute> {
    public abstract fun canNavigate(route: ROUTE): Boolean
    public abstract fun push(route: ROUTE)
    public abstract fun pop()
    public abstract fun popTo(route: ROUTE, inclusive: Boolean)
}