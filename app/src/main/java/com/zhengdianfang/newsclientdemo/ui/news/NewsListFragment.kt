package com.zhengdianfang.newsclientdemo.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhengdianfang.newsclientdemo.R
import com.zhengdianfang.newsclientdemo.model.Category
import com.zhengdianfang.newsclientdemo.model.News
import kotlinx.android.synthetic.main.fragment_news_list.*

class NewsListFragment: Fragment(), INewsContract.IView {

    companion object {
        private const val ARG_CATEGORY = "arg_category"

        fun getInstance(category: Category): NewsListFragment {
            val fragment = NewsListFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARG_CATEGORY, category)
            fragment.arguments = bundle
            return fragment
        }
    }

    private val category by lazy { arguments?.getParcelable<Category>(ARG_CATEGORY) }

    private val newsPresenter = NewsPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsPresenter.attach(this)
        initViews()
    }

    override fun onResume() {
        super.onResume()
        category?.let {
            newsPresenter.requestNewsList(it.id)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        newsPresenter.detach()
    }

    override fun showNewsList(newsList: List<News>) {
        (newsRecyclerView.adapter as NewsItemAdapter).updateNews(newsList)
        emptyView.visibility = View.GONE
    }

    override fun showEmptyView() {
        emptyView.visibility = View.VISIBLE
    }

    private fun initViews() {
        newsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = NewsItemAdapter()
        }
    }
}
