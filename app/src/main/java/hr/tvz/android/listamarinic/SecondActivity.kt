package hr.tvz.android.listamarinic

import android.annotation.SuppressLint
import android.content.*
import android.net.ConnectivityManager
import android.net.Uri
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_DATA = "EXTRA_DATA"
    }
    lateinit var wifiSwitch: Switch
    lateinit var wifiManager: WifiManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val name = intent.getStringExtra("name")
        tv_name.text = name

        val price = intent.getDoubleExtra("price", 0.0)
        tv_price.text = price.toString()

        val supply = intent.getIntExtra("supply", 0)
        //tv_supply.text = supply.toString()

        /*val image = intent.getIntExtra("image", 0)
        tv_image.text = image.toString()
*/
        button_chrome.setOnClickListener{
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.coingecko.com/en")
            startActivity(openURL)
        }

        wifiSwitch = findViewById(R.id.wifiSwitch)
        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        wifiSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                wifiManager.isWifiEnabled = true
                wifiSwitch.text = "WiFi is ON"
            } else {
                wifiManager.isWifiEnabled = false
                wifiSwitch.text = "WiFi is OFF"
            }
        }

        iv_image.setOnClickListener{

            iv_image.animate().apply{
                duration = 1000
                rotationXBy(360f)
            }.start()
        }

        button_pic.setOnClickListener{
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        val intentFilter = IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION)
        registerReceiver(wifiStateReceiver, intentFilter)
    }
    override fun onStop() {
        super.onStop()
        unregisterReceiver(wifiStateReceiver)
    }
    private val wifiStateReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
                WifiManager.WIFI_STATE_UNKNOWN)) {
                WifiManager.WIFI_STATE_ENABLED -> {
                    wifiSwitch.isChecked = true
                    wifiSwitch.text = "WiFi is ON"
                    Toast.makeText(this@SecondActivity, "Wifi is On", Toast.LENGTH_SHORT).show()
                }
                WifiManager.WIFI_STATE_DISABLED -> {
                    wifiSwitch.isChecked = false
                    wifiSwitch.text = "WiFi is OFF"
                    Toast.makeText(this@SecondActivity, "Wifi is Off", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.main, menu)

        val broadcastPress = menu?.findItem(R.id.broadcast)

        broadcastPress?.setOnMenuItemClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Do you want to show broadcast?")
            builder.setPositiveButton("Yes", { dialogInterface: DialogInterface, i: Int ->
                Intent().also { intent ->
                    Toast.makeText(this, "asd", Toast.LENGTH_SHORT).show()
                    intent.setAction(Intent.ACTION_BATTERY_OKAY)
                    sendBroadcast(intent)
                }

            })
            builder.setNegativeButton("No", { dialogInterface: DialogInterface, i: Int -> })
            builder.show()
            return@setOnMenuItemClickListener true
        }

        return true
    }
}