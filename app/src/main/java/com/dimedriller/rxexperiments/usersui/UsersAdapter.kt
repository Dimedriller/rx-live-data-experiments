package com.dimedriller.rxexperiments.usersui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.githubstalker.R
import com.dimedriller.rxexperiments.App
import com.dimedriller.service.serviceusers.models.User
import com.dimedriller.servicecore.ApplicationComponent

class UsersAdapter(context: Context): RecyclerView.Adapter<UsersViewHolder>() {
    private val picasso: Picasso
    private var items: List<User> = emptyList()

    init {
        val application = context.applicationContext as App
        val applicationComponent = application.componentProvider.get(ApplicationComponent::class.java)
        picasso = applicationComponent.picasso()
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.user_item, parent, false)
        return UsersViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = items[position]
        holder.bind(user, picasso)
    }

    fun setItems(items: List<User>) {
        this.items = items
        notifyDataSetChanged()
    }


}