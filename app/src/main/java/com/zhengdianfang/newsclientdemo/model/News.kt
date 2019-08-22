package com.zhengdianfang.newsclientdemo.model

import com.fasterxml.jackson.annotation.JsonProperty

data class News (
    @JsonProperty("uniquekey")
    val uniqueKey: String,
    val title: String,
    val date: String,
    val category: Long,
    @JsonProperty("author_name")
    val authorName: String,
    val url: String,
    @JsonProperty("thumbnail_pic_s")
    val thumbnail1: String?,
    @JsonProperty("thumbnail_pic_s02")
    val thumbnail2: String?,
    @JsonProperty("thumbnail_pic_s03")
    val thumbnail3: String?
)
