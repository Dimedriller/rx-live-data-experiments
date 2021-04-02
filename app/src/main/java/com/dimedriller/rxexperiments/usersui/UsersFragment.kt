package com.dimedriller.rxexperiments.usersui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.githubstalker.R
import com.dimedriller.rxexperiments.App

class UsersFragment: Fragment() {
    private val viewModel: UsersViewModel by viewModels {
        val application = requireContext().applicationContext as App
        UsersViewModelFactory(application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.users_list, container, false)

        val usersView = root.findViewById<RecyclerView>(R.id.list)
        val usersAdapter = UsersAdapter(inflater.context)
        usersView.adapter = usersAdapter

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usersView = view.findViewById<RecyclerView>(R.id.list)
        val usersAdapter = usersView.adapter as UsersAdapter

        val refreshView = view.findViewById<SwipeRefreshLayout>(R.id.refresh)
        refreshView.setOnRefreshListener {
            viewModel.refreshUsers().observe(viewLifecycleOwner) {
                refreshView.isRefreshing = false
                usersAdapter.setItems(it)
            }
        }

        viewModel.getUsers().observe(viewLifecycleOwner) {
            usersAdapter.setItems(it)
        }
    }
}