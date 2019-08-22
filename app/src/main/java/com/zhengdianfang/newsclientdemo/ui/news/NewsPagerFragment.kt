package com.zhengdianfang.newsclientdemo.ui.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.zhengdianfang.newsclientdemo.model.Category

class NewsPagerFragment: Fragment() {

    companion object {
        private const val ARG_CATEGORY = "arg_category"

        fun getInstance(category: Category): NewsPagerFragment {
            val fragment = NewsPagerFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARG_CATEGORY, category)
            fragment.arguments = bundle
            return fragment
        }
    }
}
