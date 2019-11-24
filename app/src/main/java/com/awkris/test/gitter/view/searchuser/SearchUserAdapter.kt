package com.awkris.test.gitter.view.searchuser

import android.content.Context
import android.content.Intent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.awkris.test.gitter.data.model.User

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.awkris.test.gitter.R
import com.awkris.test.gitter.view.webview.WebviewActivity
import com.squareup.picasso.Picasso

class SearchUserAdapter(
    private val mSourceDataList:List<User>,
    private val mContext:Context
) : RecyclerView.Adapter<SearchUserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup:ViewGroup, i:Int): ViewHolder {
        val mLayoutInflater:LayoutInflater = LayoutInflater.from(mContext)
        val itemView = mLayoutInflater.inflate(R.layout.item_user_card, viewGroup, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i:Int) {
        viewHolder.bind(mSourceDataList[i])
    }

    override fun getItemCount():Int {
        return mSourceDataList.size
    }

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        private var userAvatar:ImageView = itemView.findViewById(R.id.img_avatar)
        private var userLogin:TextView = itemView.findViewById(R.id.txt_userid)
        private var container:ConstraintLayout = itemView.findViewById(R.id.user_container)

        fun bind(item:User) {
             Picasso.get().load(item.avatarUrl).into(userAvatar)
             userLogin.text = item.login
             container.setOnClickListener {
                 val intent = Intent(mContext, WebviewActivity::class.java)
                 intent.putExtra("url", item.htmlUrl)
                 mContext.startActivity(intent)
        }
         }
    }
}
