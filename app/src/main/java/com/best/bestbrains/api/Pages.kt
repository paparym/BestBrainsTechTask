package com.best.bestbrains.api

data class Pages(
    val `data`: MutableList<User>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
)