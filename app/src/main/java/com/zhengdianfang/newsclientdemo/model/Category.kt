package com.zhengdianfang.newsclientdemo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(
   val id: Long,
   val title: String,
   val order: Int
) : Parcelable
