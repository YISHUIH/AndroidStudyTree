package com.example.study.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.study.BaseActivity
import com.example.study.R
import com.example.study.api.RequestRepositorySearch
import com.example.study.databinding.ItemRepositroyListLayoutBinding
import com.example.study.entity.RepositorySearchBean
import com.example.study.jetpack.paging.RepositorySearchViewModel
import kotlinx.android.synthetic.main.activity_retrofit.*

class RetrofitActivity : BaseActivity(), View.OnClickListener {
    companion object {
        fun start(context: Context) {
            var intent = Intent(context, RetrofitActivity::class.java)
            context.startActivity(intent)
        }
    }


    private lateinit var api: RequestRepositorySearch
    val viewModel by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        ViewModelProviders.of(this).get(RepositorySearchViewModel::class.java)
    }

    val adapter = RVAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
        request.setOnClickListener(this)

        rv.layoutManager = LinearLayoutManager(this)

        rv.adapter = adapter
        viewModel.livaData.observe(this, Observer {
            adapter.submitList(it)
        })

    }

    override fun onClick(v: View?) {
        //刷新
        when (v?.id) {
            R.id.request -> {
                val content = et_search.text.toString()
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show()
                    return
                }
                adapter.submitList(null)
                viewModel.livaData = viewModel.s(content)
                //新的liveData
                viewModel.livaData.observe(this, Observer {
                    adapter.submitList(it)
                })
            }
        }
    }

    class RVAdapter : PagedListAdapter<RepositorySearchBean.ItemsBean?, RVViewHolder?>(callBack) {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {
            return RVViewHolder(parent)
        }

        override fun onBindViewHolder(holder: RVViewHolder, position: Int) {
            var user = getItem(position)
            holder.bindTo(user)
        }

        companion object {
            val callBack = object : DiffUtil.ItemCallback<RepositorySearchBean.ItemsBean?>() {
                override fun areItemsTheSame(oldItem: RepositorySearchBean.ItemsBean, newItem: RepositorySearchBean.ItemsBean): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: RepositorySearchBean.ItemsBean, newItem: RepositorySearchBean.ItemsBean): Boolean {
                    return oldItem == newItem
                }
            }
        }
    }

    class RVViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_repositroy_list_layout, parent, false)) {

        fun bindTo(user: RepositorySearchBean.ItemsBean?) {
            var dataBinding = DataBindingUtil.bind<ItemRepositroyListLayoutBinding>(itemView)
            dataBinding?.itemBean = user
        }
    }
}
