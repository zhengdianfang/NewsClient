package com.zhengdianfang.newsclientdemo.model

data class News (
    val uniqueKey: String,
    val title: String,
    val date: String,
    val category: Int,
    val authorName: String,
    val url: String,
    val thumbnail1: String,
    val thumbnail2: String,
    val thumbnail3: String
)
