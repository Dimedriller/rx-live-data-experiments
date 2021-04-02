package com.dimedriller.rxexperiments.usersui

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.githubstalker.R
import com.dimedriller.service.serviceusers.models.User
import com.dimedriller.rxexperiments.utils.image.CircleTransformation

class UsersViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val avatarView = view.findViewById<ImageView>(R.id.avatar)
    private val nameView = view.findViewById<TextView>(R.id.name)

    @SuppressLint("SetTextI18n")
    fun bind(user: User, picasso: Picasso) {
        val name = user.name
        nameView.text = "${name.title} ${name.first} ${name.last}"

        val picture = user.picture
        val pictureUrl = picture.thumbnail
        if (pictureUrl.isEmpty()) {
            avatarView.setImageDrawable(ColorDrawable())
        } else {
            picasso.load(pictureUrl)
                    .transform(CircleTransformation())
                    .error(ColorDrawable()) // transparent
                    .into(avatarView)
        }
    }
}