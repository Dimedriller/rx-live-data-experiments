package com.dimedriller.rxexperiments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.githubstalker.R
import com.dimedriller.rxexperiments.usersui.UsersFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportFragmentManager.backStackEntryCount == 0) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.root, UsersFragment())
                    .addToBackStack(null)
                    .commit()
        }
    }

}
