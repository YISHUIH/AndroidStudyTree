package com.example.study.activity.jetpack

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.study.BaseActivity
import com.example.study.R
import com.example.study.jetpack.paging.UserViewModel
import com.example.study.jetpack.room.User
import com.example.study.jetpack.room.UserController
import kotlinx.android.synthetic.main.activity_paging.*
import kotlinx.android.synthetic.main.item_paging_layout.view.*

class PagingActivity : BaseActivity(), View.OnClickListener {

    companion object {
        fun start(context: Context) {
            var intent = Intent(context, PagingActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val userViewModel by lazy {
        ViewModelProviders.of(this).get(UserViewModel::class.java)
    }

    lateinit var userController: UserController
    lateinit var userList: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)
        //删除操作
        userController = UserController.getUserControllerIns(applicationContext)
        UserController.userLiveData?.observe(this, Observer {
            userList = it
        })

        rv.layoutManager = LinearLayoutManager(this)
        val adapter = RVAdapter()
        rv.adapter = adapter
        //更新数据
        userViewModel.allUser.observe(this, Observer {
            adapter.submitList(it)
        })

        //点击事件
        insert.setOnClickListener(this)
        delete.setOnClickListener(this)
        query.setOnClickListener(this)
        update.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.insert -> userController.insertUser(User(et.text.toString()))
            R.id.delete -> {
                userController.delete(userList[0])

            }
            R.id.query -> UserController.userDataBase.getUserDao().getAllUser()
            R.id.update -> {
                var user = userList[0]
                user.name = et.text.toString()
                userController.upDate(user)
            }

        }
    }

    class RVAdapter : PagedListAdapter<User?, RVViewHolder?>(callBack) {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {

            return RVViewHolder(parent)
        }

        override fun onBindViewHolder(holder: RVViewHolder, position: Int) {
            var user = getItem(position)
            holder.bindTo(user)
        }

        companion object {
            val callBack = object : DiffUtil.ItemCallback<User?>() {
                override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                    return oldItem == newItem
                }
            }
        }
    }

    class RVViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_paging_layout, parent, false)) {
        var tvName = itemView.userName
        fun bindTo(user: User?) {
            tvName.text = user?.name
        }
    }
}
