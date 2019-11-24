package com.awkris.test.gitter.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.awkris.test.gitter.R
import com.awkris.test.gitter.view.searchuser.SearchUserFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, SearchUserFragment.newInstance())
            .commit()
    }
}