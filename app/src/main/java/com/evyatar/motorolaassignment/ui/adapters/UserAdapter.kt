package com.evyatar.motorolaassignment.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.evyatar.motorolaassignment.MainListener
import com.evyatar.motorolaassignment.R
import com.evyatar.motorolaassignment.model.usersmodel.Result
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper
import kotlinx.android.synthetic.main.user_item.view.*

class UserAdapter(
    private var userList: List<Result>,
    private var mainListener: MainListener
) : RecyclerView.Adapter<UsersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder(LayoutInflater.from(parent.context), parent)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
       holder.bind(userList[position], mainListener)
    }

    override fun getItemCount() = userList.size

}

class UsersViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.user_item, parent, false)) {
        private var mUserPhoto: ImageView? = null
        private var mUserName: TextView? = null
        private var mUserEmail: TextView? = null

    init {
        mUserPhoto = itemView.user_photo
        mUserName = itemView.user_name
        mUserEmail = itemView.user_email
    }

    fun bind(result: Result, mainListener: MainListener) {
        UrlImageViewHelper.setUrlDrawable(mUserPhoto, result.picture.large)
        mUserName?.text = "${result.name.first} ${result.name.last}"
        mUserEmail?.text = result.email
        itemView.setOnClickListener {
            mainListener.proceedToBDFragment(result)
        }
        itemView.setOnLongClickListener {
            mainListener.proceedToEmailApp(result.email)
            true
        }
    }
}