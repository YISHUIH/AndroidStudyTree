package com.example.study.activity.jetpack

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.study.BaseActivity
import com.example.study.R
import com.example.study.jetpack.model.Book
import kotlinx.android.synthetic.main.activity_paging.*

class PagingActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            var intent = Intent(context, PagingActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)

        rv.layoutManager = LinearLayoutManager(this)
        val callBack = object : DiffUtil.ItemCallback<Book?>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
        rv.adapter = RVAdapter(callBack)
    }


    class RVAdapter(diffCallback: DiffUtil.ItemCallback<Book?>) : PagedListAdapter<Book?, RVViewHolder?>(diffCallback) {

        override fun getItemCount(): Int {
            return 20
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_paging_layout,
                    parent,false
            )
            return RVViewHolder(view)
        }

        override fun onBindViewHolder(holder: RVViewHolder, position: Int) {
        }

    }

    class RVViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}
