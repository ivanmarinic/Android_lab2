package hr.tvz.android.listamarinic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        val actionBar = supportActionBar

        actionBar!!.title = "Third ACtivity"

        actionBar.setDisplayHomeAsUpEnabled(true)
    }
}